package com.dwi.saas.tenant.biz.strategy.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.utils.CollHelper;
import com.dwi.saas.common.constant.BizConstant;
import com.dwi.saas.tenant.biz.dao.InitDatabaseMapper;
import com.dwi.saas.tenant.biz.service.DataSourceService;
import com.dwi.saas.tenant.biz.service.DatasourceConfigService;
import com.dwi.saas.tenant.biz.service.InitDsService;
import com.dwi.saas.tenant.biz.service.TenantDatasourceConfigService;
import com.dwi.saas.tenant.biz.strategy.InitSystemStrategy;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;
import com.dwi.saas.tenant.domain.entity.TenantDatasourceConfig;
import com.dwi.saas.tenant.domain.enumeration.TenantConnectTypeEnum;
import com.dwi.saas.tenant.domain.enumeration.TenantStatusEnum;
//import com.dwi.saas.tenant.dto.TenantConnectDTO;
//import com.dwi.saas.tenant.entity.DatasourceConfig;
//import com.dwi.saas.tenant.entity.TenantDatasourceConfig;
//import com.dwi.saas.tenant.enumeration.TenantConnectTypeEnum;
//import com.dwi.saas.tenant.enumeration.TenantStatusEnum;
import com.google.common.collect.Sets;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 初始化系统
 * <p>
 * 初始化规则：
 * saas-authority-server/src/main/resources/sql 路径存放8个sql文件 (每个库对应一个文件)
 * saas_base.sql            # 基础库：权限、消息，短信，邮件，文件等
 * data_saas_base.sql       # 基础库数据： 如初始用户，初始角色，初始菜单
 *
 * @author dwi
 * @date 2019/10/25
 */
@Service("DATASOURCE")
@Slf4j
@RequiredArgsConstructor
public class DatasourceInitSystemStrategy implements InitSystemStrategy {

    private final DatasourceConfigService datasourceConfigService;
    private final TenantDatasourceConfigService tenantDatasourceConfigService;
    private final InitDatabaseMapper initDbMapper;
    private final DataSourceService dataSourceService;
    private final InitDsService initDsService;
    @Value("${spring.application.name:saas-authority-server}")
    private String applicationName;

    /**
     * 启动项目时，调用初始化数据源
     *
     * @return
     */
    @DS("master")
    public boolean initDataSource() {
        // LOCAL 类型的数据源初始化
        List<String> list = initDbMapper.selectTenantCodeList(TenantStatusEnum.NORMAL.name(), TenantConnectTypeEnum.LOCAL.name());
        list.forEach((tenant) -> {
            dataSourceService.addLocalDynamicRoutingDataSource(tenant);
        });

        // REMOTE 类型的数据源初始化
        List<DatasourceConfig> dcList = datasourceConfigService.listByApplication(applicationName, TenantStatusEnum.NORMAL.name(), TenantConnectTypeEnum.REMOTE.name());
        dcList.forEach(dc -> {
            // 权限服务
            DataSourceProperty dataSourceProperty = new DataSourceProperty();
            BeanUtils.copyProperties(dc, dataSourceProperty);
            dataSourceService.addDynamicRoutingDataSource(dataSourceProperty);
        });
        log.debug("初始化租户数据源成功");
        return true;
    }

    /**
     * 求优化！
     *
     * @param tenantConnect 链接信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initConnect(TenantConnectDTO tenantConnect) {
        Map<String, DatasourceConfig> typeMap = new HashMap<>(8);
        if (TenantConnectTypeEnum.REMOTE.eq(tenantConnect.getConnectType())) {

            Long authorityDatasource = tenantConnect.getAuthorityDatasource();
            Long fileDatasource = tenantConnect.getFileDatasource();
            fileDatasource = fileDatasource == null ? authorityDatasource : fileDatasource;
            Long msgsDatasource = tenantConnect.getMsgDatasource();
            msgsDatasource = msgsDatasource == null ? authorityDatasource : msgsDatasource;
            Long oauthDatasource = tenantConnect.getOauthDatasource();
            oauthDatasource = oauthDatasource == null ? authorityDatasource : oauthDatasource;
            Long gateDatasource = tenantConnect.getGateDatasource();
            gateDatasource = gateDatasource == null ? authorityDatasource : gateDatasource;
            List<DatasourceConfig> dcList = datasourceConfigService.listByIds(Sets.newHashSet(authorityDatasource, fileDatasource, msgsDatasource, oauthDatasource, gateDatasource));
            dcList.forEach(item -> {
                item.setType(tenantConnect.getConnectType());
                item.setPoolName(tenantConnect.getTenant());
            });
            Map<Long, DatasourceConfig> dcMap = CollHelper.uniqueIndex(dcList, DatasourceConfig::getId, (data) -> data);

            DatasourceConfig authorityDc = dcMap.get(authorityDatasource);
            typeMap.put(BizConstant.AUTHORITY, authorityDc);
            typeMap.put(BizConstant.FILE, dcMap.getOrDefault(fileDatasource, authorityDc));
            typeMap.put(BizConstant.MSG, dcMap.getOrDefault(msgsDatasource, authorityDc));
            typeMap.put(BizConstant.OAUTH, dcMap.getOrDefault(oauthDatasource, authorityDc));
            typeMap.put(BizConstant.GATE, dcMap.getOrDefault(gateDatasource, authorityDc));

            tenantDatasourceConfigService.remove(Wraps.<TenantDatasourceConfig>lbQ().eq(TenantDatasourceConfig::getTenantId, tenantConnect.getId()));

            List<TenantDatasourceConfig> list = new ArrayList<>();
            list.add(TenantDatasourceConfig.builder().application(BizConstant.AUTHORITY).tenantId(tenantConnect.getId()).datasourceConfigId(authorityDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.FILE).tenantId(tenantConnect.getId()).datasourceConfigId(fileDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.MSG).tenantId(tenantConnect.getId()).datasourceConfigId(msgsDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.OAUTH).tenantId(tenantConnect.getId()).datasourceConfigId(oauthDatasource).build());
            list.add(TenantDatasourceConfig.builder().application(BizConstant.GATE).tenantId(tenantConnect.getId()).datasourceConfigId(gateDatasource).build());
            tenantDatasourceConfigService.saveBatch(list);
        } else {
            String tenant = tenantConnect.getTenant();
            DatasourceConfig dto = new DatasourceConfig();
            dto.setType(tenantConnect.getConnectType());
            dto.setPoolName(tenant);

            typeMap.put(BizConstant.AUTHORITY, dto);
            typeMap.put(BizConstant.FILE, dto);
            typeMap.put(BizConstant.MSG, dto);
            typeMap.put(BizConstant.OAUTH, dto);
            typeMap.put(BizConstant.GATE, dto);
        }

        // 动态初始化数据源
        return initDsService.initConnect(typeMap);
    }

    @Override
    public boolean reset(String tenant) {

        return true;
    }

    @Override
    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        tenantDatasourceConfigService.remove(Wraps.<TenantDatasourceConfig>lbQ().in(TenantDatasourceConfig::getTenantId, ids));

        tenantCodeList.forEach(initDsService::removeDataSource);
        return true;
    }
}

package com.dwi.saas.tenant.strategy.impl;

import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.datasource.plugin.service.DatasourceConfigService;
import com.dwi.basic.datasource.plugin.service.TenantDatasourceConfigService;
import com.dwi.basic.datasource.plugin.domain.entity.DatasourceConfig;
import com.dwi.basic.datasource.plugin.domain.entity.TenantDatasourceConfig;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO.ConnectConfig;
import com.dwi.saas.tenant.rc.InitDsService;
import com.dwi.saas.tenant.service.TenantService;
import com.dwi.saas.tenant.strategy.InitSystemStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 初始化系统
 * <p>
 * 初始化规则：
 * saas-authority-server/src/main/resources/sql 路径存放8个sql文件 (每个库对应一个文件)
 * saas_base.sql            # 基础库：权限、消息，短信，邮件，文件等
 * data_saas_base.sql       # 基础库数据： 如初始用户，初始角色，初始菜单
 *
 * @author dwi
 * @date 2020/10/25
 */
@Service("DATASOURCE")
@Slf4j
@RequiredArgsConstructor
public class DatasourceInitSystemStrategy implements InitSystemStrategy {

	 	private final DatasourceConfigService datasourceConfigService;
	 	
	    private final TenantDatasourceConfigService tenantDatasourceConfigService;
	    
	    private final InitDsService initDsService;
	    
	    private final TenantService tenantService;
	    

   /**

    /**
     * 求优化！
     *
     * @param tenantConnect 链接信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initConnect(Long tenantId, ConnectConfig connectConfig) {
        Map<String, DatasourceConfig> typeMap = new HashMap<>();
    	String tenant = tenantService.getById(tenantId).getCode();
		DatasourceConfig dataSourceConfig = datasourceConfigService.getById(connectConfig.getDataSourceConfigId());
		dataSourceConfig.setPoolName(tenant);
		typeMap.put(connectConfig.getServiceName(), dataSourceConfig);
		TenantDatasourceConfig datasourceConfig = TenantDatasourceConfig.builder().tenantId(tenantId).datasourceConfigId(connectConfig.getDataSourceConfigId()).build();
		tenantDatasourceConfigService.saveOrUpdate(datasourceConfig, 
				Wraps.<TenantDatasourceConfig>lbU()
					.eq(TenantDatasourceConfig::getTenantId, tenantId)
					.eq(TenantDatasourceConfig::getDatasourceConfigId, connectConfig.getDataSourceConfigId()));
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

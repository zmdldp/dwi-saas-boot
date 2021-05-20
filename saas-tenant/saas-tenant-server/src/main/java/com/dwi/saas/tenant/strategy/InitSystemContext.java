package com.dwi.saas.tenant.strategy;

import cn.afterturn.easypoi.excel.ExcelExportUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.poi.excel.ExcelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.utils.BizAssert;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO.ConnectConfig;
import com.dwi.saas.tenant.domain.enumeration.TenantStatusEnum;
import com.dwi.saas.tenant.service.TenantService;
import com.dwi.saas.tenant.strategy.InitSystemStrategy;

import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 初始化系统上下文
 *
 * @author dwi
 * @date 2020年03月15日11:58:47
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class InitSystemContext {
    
	private final Map<String, InitSystemStrategy> initSystemStrategyMap;
	
	private final TenantService tenantService;

    /**
     * 初始化链接
     *
     * @param tenantConnect 链接参数
     */
    public boolean initConnect(TenantConnectDTO tenantConnect) {
    	boolean flag = true;
    	List<ConnectConfig> connectConfigList = tenantConnect.getConnectConfigList();
    	for (ConnectConfig connectConfig : connectConfigList) {
    		 InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(connectConfig.getMultiTenantType().name());
    	     BizAssert.notNull(initSystemStrategy, StrUtil.format("您配置的租户模式:{}不可用", connectConfig.getMultiTenantType().name()));
    	     if(initSystemStrategy.initConnect(tenantConnect.getTenantId(), connectConfig)) {
    	    	 log.debug("租户:{}初始化数据源连接:{}, 成功!", tenantConnect.getTenantId(), connectConfig);
    	     }else {
    	    	 log.debug("租户:{}初始化数据源连接:{}, 失败!", tenantConnect.getTenantId(), connectConfig);
    	    	 flag = false;
    	     }
		}
    	if(flag) {
    		log.debug("租户所有服务初始化数据源连接:{}, 成功!", tenantConnect);
    		tenantService.updateStatus(Collections.singletonList(tenantConnect.getTenantId()), TenantStatusEnum.NORMAL);
    	} 	
    	return flag;
    }

//    /**
//     * 重置系统
//     *
//     * @param tenant 租户编码
//     */
//    public boolean reset(String tenant) {
//        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
//        BizAssert.notNull(initSystemStrategy, StrUtil.format("您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name()));
//        return initSystemStrategy.reset(tenant);
//    }
//
//    /**
//     * 删除租户数据
//     *
//     * @param tenantCodeList 租户编码
//     */
//    public boolean delete(List<Long> ids, List<String> tenantCodeList) {
//        InitSystemStrategy initSystemStrategy = initSystemStrategyMap.get(databaseProperties.getMultiTenantType().name());
//        BizAssert.notNull(initSystemStrategy, StrUtil.format("您配置的租户模式:{}不可用", databaseProperties.getMultiTenantType().name()));
//
//        return initSystemStrategy.delete(ids, tenantCodeList);
//    }
}

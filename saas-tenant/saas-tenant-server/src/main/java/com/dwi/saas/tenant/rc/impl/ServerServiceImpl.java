package com.dwi.saas.tenant.rc.impl;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.dwi.basic.base.R;
import com.dwi.basic.database.properties.MultiTenantType;
import com.dwi.basic.utils.CommonConstants;
import com.dwi.basic.utils.StrPool;
import com.dwi.saas.tenant.domain.vo.ServerServiceVO;
import com.dwi.saas.tenant.rc.ServerService;

import cn.hutool.core.collection.CollUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 注册中心服务查询
 * 
 * @author admin
 *
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class ServerServiceImpl implements ServerService{
	
	private final Environment env;

	/**
	 * 获取服务信息列表
	 * 
	 * @return
	 */
	@Override
	public List<ServerServiceVO> list() {
		return Collections.singletonList(ServerServiceVO.builder()
				.serviceName(env.getProperty(CommonConstants.APPLICATION_NAME_KEY))
				.multiTenantType(MultiTenantType.get(env.getProperty(CommonConstants.MULTI_TENANT_TYPE_KEY)))
				.build());	
	}

	/**
	 * 获取当前租户服务同源的所有其它服务名称列表
	 * 
	 * @return
	 */
	@Override
	public List<String> getServices() {
		return Collections.singletonList(env.getProperty(CommonConstants.APPLICATION_NAME_KEY));
	}

	@Override
	public List<String> getDsReqUrls(String service, String method) {
		if(CommonConstants.INIT_DS_PARAM_METHOD_INIT.equals(method)) {		
			return Collections.singletonList("127.0.0.1".concat(env.getProperty("server.port")).concat(CommonConstants.INIT_DS_REQUESTMAPPING_INIT));
		}else if(CommonConstants.INIT_DS_PARAM_METHOD_REMOVE.equals(method)) {
			return Collections.singletonList("127.0.0.1".concat(env.getProperty("server.port")).concat(CommonConstants.INIT_DS_REQUESTMAPPING_REMOVE));
		}else {
			log.debug("操作数据源的方法:{}不存在!", method);
			return null;
		}
		
	}
	
	
	
	
	



}

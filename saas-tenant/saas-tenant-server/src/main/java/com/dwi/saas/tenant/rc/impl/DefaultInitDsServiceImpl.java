package com.dwi.saas.tenant.rc.impl;

import cn.hutool.core.bean.BeanUtil;
import com.dwi.basic.base.R;
import com.dwi.basic.mq.properties.MqProperties;
import com.dwi.basic.utils.CommonConstants;
import com.dwi.saas.tenant.rc.InitDsService;
import com.dwi.saas.tenant.rc.ServerService;
import com.dwi.basic.datasource.plugin.domain.dto.DataSourcePropertyDTO;
import com.dwi.basic.datasource.plugin.domain.entity.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Map;

/**
 * 没有开启消息队列就只能轮训了
 *
 * @author dwi
 * @date 2020年04月05日16:27:03
 */
@Service
@Slf4j
@ConditionalOnProperty(prefix = MqProperties.PREFIX, name = "enabled", havingValue = "false", matchIfMissing = true)
@RequiredArgsConstructor
public class DefaultInitDsServiceImpl implements InitDsService {

    
    private final ServerService serverService;
    
    private final RestTemplate restTemplate;

   
    
    /**
     * 注意，集群部署时，请勿使用Feign调用触发初始化数据源时，否则会导致只有一个实例被初始化成功 ！！！
     *
     * @param typeMap 数据源信息
     * @return
     */
    @Override
    public boolean initConnect(Map<String, DatasourceConfig> typeMap) { 	
    	return typeMap.entrySet().stream().allMatch(e -> this.initConnect(e.getKey(), e.getValue()));	
    }
    
    @SuppressWarnings("unchecked")
    public boolean initConnect(String service, DatasourceConfig datasourceConfig) {
    	boolean flag = true;
    	List<String> initDsUrls = serverService.getDsReqUrls(service, CommonConstants.INIT_DS_PARAM_METHOD_INIT);
        for (String u : initDsUrls) {
        	DataSourcePropertyDTO dsp = BeanUtil.toBean(datasourceConfig, DataSourcePropertyDTO.class);
        	R<Boolean> r = restTemplate.postForObject(u, dsp, R.class);
        	if(r !=null && r.getData()) {
        		log.debug("初始化数据源-service:{}, dsp:{}, url:{},请求处理返回成功.", service, dsp, u);
        	}else {
        		log.debug("初始化数据源-service:{}, dsp:{}, url:{},请求处理返回失败.", service, dsp, u);
        		flag = false;
        	}    
		}
        return flag;   	
    }
    
    /**
     * 注意，集群部署时，请勿使用Feign调用操作数据源
     *
     * @param tenant 租户编码/数据源名
     * @return
     */
    @Override
	public boolean removeDataSource(String tenant) {
			serverService.getServices().forEach(s -> removeDataSource(tenant, s)); 	
	        return true;
	    }
     
    @SuppressWarnings("unchecked")
    public boolean removeDataSource(String tenant, String service) {	
    	serverService.getDsReqUrls(service, CommonConstants.INIT_DS_PARAM_METHOD_REMOVE)
        .forEach(u -> {
        	MultiValueMap<String, Object> params = new LinkedMultiValueMap<>();
        	params.add("tenant", tenant);
        	R<Boolean> r = restTemplate.getForObject(u, R.class, params);
        	if(r !=null && r.getData()) {
        		log.debug("移除数据源-tenent:{}, service:{}, url:{},请求处理返回成功.", tenant, service, u);
        	}else {
        		log.debug("移除数据源-tenent:{}, service:{}, url:{},请求处理返回失败.", tenant, service, u);
        	}
        });
        return true;
    }
}

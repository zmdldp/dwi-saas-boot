package com.dwi.saas.tenant.rc.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.dwi.basic.mq.properties.MqProperties;
import com.dwi.saas.tenant.rc.InitDsService;
import com.dwi.saas.tenant.rc.ServerService;
import com.dwi.basic.datasource.plugin.domain.dto.DataSourcePropertyDTO;
import com.dwi.basic.datasource.plugin.domain.entity.DatasourceConfig;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_METHOD;
import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_METHOD_INIT;
import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_METHOD_REMOVE;
import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_TENANT;


/**
 * 开启消息队列就广播
 *
 * @author dwi
 * @date 2020年04月05日16:27:03
 */
@Service
@Slf4j
@ConditionalOnProperty(prefix = MqProperties.PREFIX, name = "enabled", havingValue = "true")
@RequiredArgsConstructor
public class MqInitDsServiceImpl implements InitDsService {

    private final RabbitTemplate rabbitTemplate;
    
    private final ServerService serverService;

    @Bean
    public List<FanoutExchange> getFanoutExchangeList() {
    	return serverService.getServices().stream().map(FanoutExchange::new).collect(Collectors.toList());
    }


    /**
     * 1. 每个服务的连接参数可能不一样，所以需要单独发送
     * 2. 每个服务都可能会集群部署，所以要保证每个集群都能收到消息
     *
     * @param typeMap 数据源信息
     * @return
     */
    @Override
    public boolean initConnect(Map<String, DatasourceConfig> typeMap) { 	
    	typeMap.forEach(this :: initConnect);
    	return true;       
    }   

    /**
     * 1. 每个服务的连接参数可能不一样，所以需要单独发送
     * 2. 每个服务都可能会集群部署，所以要保证每个集群都能收到消息
     * 
     * @param service	服务名
     * @param datasourceConfig 	数据源配置
     * @return
     */
    public boolean initConnect(String service, DatasourceConfig datasourceConfig) {  	
    	DataSourcePropertyDTO dsp = BeanUtil.toBean(service, DataSourcePropertyDTO.class);
        JSONObject param = new JSONObject();
        param.put(INIT_DS_PARAM_TENANT, dsp);
        param.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        //Fanout Exchange – 不处理路由键。
        rabbitTemplate.convertAndSend(service, "", param.toString());
        return true;
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
    
    public boolean removeDataSource(String tenant, String service) {
    	JSONObject param = new JSONObject();
        param.put(INIT_DS_PARAM_TENANT, tenant);
        param.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_REMOVE);
        rabbitTemplate.convertAndSend(service, "", param.toString());
        return true;
    }
}

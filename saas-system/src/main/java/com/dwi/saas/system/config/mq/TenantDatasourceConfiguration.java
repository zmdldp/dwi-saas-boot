//package com.dwi.saas.system.config.mq;
//
//import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_METHOD;
//import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_METHOD_INIT;
//import static com.dwi.basic.utils.CommonConstants.INIT_DS_PARAM_TENANT;
//
//import java.util.Collections;
//import org.springframework.amqp.core.Binding;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.annotation.RabbitListener;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.messaging.handler.annotation.Payload;
//
//import com.alibaba.fastjson.JSON;
//import com.alibaba.fastjson.JSONObject;
//import com.dwi.basic.database.properties.DatabaseProperties;
//import com.dwi.basic.mq.properties.MqProperties;
//import com.dwi.basic.datasource.plugin.context.DataSourceService;
//import com.dwi.basic.datasource.plugin.domain.dto.DataSourcePropertyDTO;
//
//import lombok.AllArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//
///**
// * 消息队列开启时，启用
// *
// * @author dwi
// * @date 2020年04月05日19:22:02
// */
//@Slf4j
//@AllArgsConstructor
//@Configuration
//@ConditionalOnProperty(prefix = MqProperties.PREFIX, name = "enabled", havingValue = "true")
//public class TenantDatasourceConfiguration {
//
//	private final DataSourceService datasourceApi;
//	
//	@Value("${spring.application.name}")
//    private String applicationName;
//
//	@Bean
//	@ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//	public Queue dsQueue() {
//		return new Queue(BizMqQueue.TENANT_DS_QUEUE_BY_FILE);
//	}
//
//	@Bean
//	@ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//	public Binding dsQueueBinding() {
//		return new Binding(BizMqQueue.TENANT_DS_QUEUE_BY_FILE, Binding.DestinationType.QUEUE,
//				applicationName, "", Collections.emptyMap());
//	}
//
//	@RabbitListener(queues = BizMqQueue.TENANT_DS_QUEUE_BY_FILE)
//	@ConditionalOnProperty(prefix = DatabaseProperties.PREFIX, name = "multiTenantType", havingValue = "DATASOURCE")
//	public void dsRabbitListener(@Payload String param) {
//		log.debug("异步初始化数据源=={}", param);
//		JSONObject map = JSON.parseObject(param);
//		if (INIT_DS_PARAM_METHOD_INIT.equals(map.getString(INIT_DS_PARAM_METHOD))) {
//			datasourceApi.initConnect(map.getObject(INIT_DS_PARAM_TENANT, DataSourcePropertyDTO.class));
//		} else {
//			datasourceApi.remove(map.getString(INIT_DS_PARAM_TENANT));
//		}
//	}
//}

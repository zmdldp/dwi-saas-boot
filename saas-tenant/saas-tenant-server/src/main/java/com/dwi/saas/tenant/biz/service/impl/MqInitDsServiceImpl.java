package com.dwi.saas.tenant.biz.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.alibaba.fastjson.JSONObject;
import com.dwi.basic.mq.properties.MqProperties;
import com.dwi.saas.common.constant.BizConstant;
import com.dwi.saas.common.constant.BizMqQueue;
import com.dwi.saas.tenant.biz.service.InitDsService;
import com.dwi.saas.tenant.domain.dto.DataSourcePropertyDTO;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.Map;

import static com.dwi.saas.common.constant.BizConstant.INIT_DS_PARAM_METHOD;
import static com.dwi.saas.common.constant.BizConstant.INIT_DS_PARAM_METHOD_INIT;
import static com.dwi.saas.common.constant.BizConstant.INIT_DS_PARAM_METHOD_REMOVE;
import static com.dwi.saas.common.constant.BizConstant.INIT_DS_PARAM_TENANT;


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

    @Bean
    public FanoutExchange getFanoutExchangeAuthority() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_AUTHORITY);
        log.debug("Query {} [{}]", BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_AUTHORITY, queue);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeFile() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_FILE);
        log.debug("Query {} [{}]", BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_FILE, queue);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeMsgs() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_MSG);
        log.debug("Query {} [{}]", BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_MSG, queue);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeGateway() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_GATEWAY);
        log.debug("Query {} [{}]", BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_GATEWAY, queue);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeOauth() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_OAUTH);
        log.debug("Query {} [{}]", BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_OAUTH, queue);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeBaseExecutor() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_BASE_EXECUTOR);
        return queue;
    }

    @Bean
    public FanoutExchange getFanoutExchangeExtendExecutor() {
        FanoutExchange queue = new FanoutExchange(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_EXTEND_EXECUTOR);
        return queue;
    }

    /**
     * 1. 每个服务的连接参数可能不一样，所以需要单独发送
     * 2. 每个服务都可能会集群部署，所以要保证每个集群都能收到消息
     *
     * @param typeMap 租户
     * @return
     */
    @Override
    public boolean initConnect(Map<String, DatasourceConfig> typeMap) {
        DataSourcePropertyDTO authorityDsp = BeanUtil.toBean(typeMap.get(BizConstant.AUTHORITY), DataSourcePropertyDTO.class);
        JSONObject param = new JSONObject();
        param.put(INIT_DS_PARAM_TENANT, authorityDsp);
        param.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_AUTHORITY, null, param.toString());

//         oauth
        JSONObject oauth = new JSONObject();
        oauth.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.OAUTH), DataSourcePropertyDTO.class));
        oauth.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_OAUTH, null, oauth.toString());

        // file
        JSONObject file = new JSONObject();
        file.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.FILE), DataSourcePropertyDTO.class));
        file.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_FILE, null, file.toString());


        // msg
        JSONObject msg = new JSONObject();
        msg.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.MSG), DataSourcePropertyDTO.class));
        msg.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_MSG, null, msg.toString());

        // 网关
        JSONObject gate = new JSONObject();
        gate.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.GATE), DataSourcePropertyDTO.class));
        gate.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_GATEWAY, null, gate.toString());

        // job 执行器
        JSONObject executor = new JSONObject();
        executor.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.BASE_EXECUTOR), DataSourcePropertyDTO.class));
        executor.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_BASE_EXECUTOR, null, executor.toString());

        JSONObject extendExecutor = new JSONObject();
        extendExecutor.put(INIT_DS_PARAM_TENANT, BeanUtil.toBean(typeMap.get(BizConstant.EXTEND_EXECUTOR), DataSourcePropertyDTO.class));
        extendExecutor.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_INIT);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_EXTEND_EXECUTOR, null, extendExecutor.toString());

        return true;
    }

    @Override
    public boolean removeDataSource(String tenant) {
        JSONObject param = new JSONObject();
        param.put(INIT_DS_PARAM_TENANT, tenant);
        param.put(INIT_DS_PARAM_METHOD, INIT_DS_PARAM_METHOD_REMOVE);
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_GATEWAY, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_AUTHORITY, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_FILE, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_OAUTH, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_MSG, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_BASE_EXECUTOR, null, param.toString());
        rabbitTemplate.convertAndSend(BizMqQueue.TENANT_DS_FANOUT_EXCHANGE_EXTEND_EXECUTOR, null, param.toString());
        return true;
    }
}

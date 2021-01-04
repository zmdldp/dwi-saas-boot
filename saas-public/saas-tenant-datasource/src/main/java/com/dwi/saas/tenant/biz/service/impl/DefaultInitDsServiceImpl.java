package com.dwi.saas.tenant.biz.service.impl;

import java.util.Map;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Service;

import com.dwi.basic.mq.properties.MqProperties;
import com.dwi.saas.common.constant.BizConstant;
import com.dwi.saas.tenant.biz.service.DataSourceService;
import com.dwi.saas.tenant.biz.service.InitDsService;
import com.dwi.saas.tenant.domain.dto.DataSourcePropertyDTO;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

import cn.hutool.core.bean.BeanUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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

    private final DataSourceService dataSourceService;

    @Override
    public boolean removeDataSource(String tenant) {
        // 权限服务
        dataSourceService.remove(tenant);
        return true;
    }

    @Override
    public boolean initConnect(Map<String, DatasourceConfig> typeMap) {
        // 权限服务
        DataSourcePropertyDTO authorityDsp = BeanUtil.toBean(typeMap.get(BizConstant.AUTHORITY), DataSourcePropertyDTO.class);
        return dataSourceService.initConnect(authorityDsp);
    }
}

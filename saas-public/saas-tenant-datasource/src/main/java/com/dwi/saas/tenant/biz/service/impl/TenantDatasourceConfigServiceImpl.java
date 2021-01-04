package com.dwi.saas.tenant.biz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.tenant.biz.dao.TenantDatasourceConfigMapper;
import com.dwi.saas.tenant.biz.service.TenantDatasourceConfigService;
import com.dwi.saas.tenant.domain.entity.TenantDatasourceConfig;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * 租户数据源关系
 *
 * @author dwi
 * @date 2020/8/27 下午4:51
 */
@Slf4j
@Service
@DS("master")
public class TenantDatasourceConfigServiceImpl extends SuperServiceImpl<TenantDatasourceConfigMapper, TenantDatasourceConfig> implements TenantDatasourceConfigService {
}

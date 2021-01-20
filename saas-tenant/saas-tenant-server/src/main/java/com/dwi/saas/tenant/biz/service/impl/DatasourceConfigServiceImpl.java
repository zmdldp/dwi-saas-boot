package com.dwi.saas.tenant.biz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.tenant.biz.dao.DatasourceConfigMapper;
import com.dwi.saas.tenant.biz.service.DataSourceService;
import com.dwi.saas.tenant.biz.service.DatasourceConfigService;
import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 业务实现类
 * 数据源
 * </p>
 *
 * @author dwi
 * @date 2020-08-21
 */
@Slf4j
@Service
@DS("master")
@RequiredArgsConstructor
public class DatasourceConfigServiceImpl extends SuperServiceImpl<DatasourceConfigMapper, DatasourceConfig> implements DatasourceConfigService {

    private final DataSourceService dataSourceService;

    @Override
    public Boolean testConnection(DataSourceProperty dataSourceProperty) {
        return dataSourceService.testConnection(dataSourceProperty);
    }

}

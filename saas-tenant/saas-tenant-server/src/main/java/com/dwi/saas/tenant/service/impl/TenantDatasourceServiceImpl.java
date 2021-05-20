package com.dwi.saas.tenant.service.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperCacheServiceImpl;
import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.saas.common.cache.tenant.TenantCacheKeyBuilder;
import com.dwi.saas.common.cache.tenant.TenantCodeCacheKeyBuilder;
import com.dwi.saas.tenant.domain.dto.TenantSaveDTO;
import com.dwi.saas.tenant.domain.entity.Tenant;
import com.dwi.saas.tenant.domain.enumeration.TenantTypeEnum;
import com.dwi.saas.tenant.service.TenantDatasourceService;
import com.dwi.saas.tenant.service.TenantService;
import com.dwi.saas.tenant.strategy.InitSystemContext;
import com.dwi.saas.tenant.dao.TenantMapper;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO.ConnectConfig;
import com.dwi.saas.tenant.domain.enumeration.TenantStatusEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.function.Function;

import static com.dwi.basic.utils.BizAssert.isFalse;

/**
 * <p>
 * 业务实现类
 * 企业
 * </p>
 *
 * @author dwi
 * @date 2020-10-24
 */
@Slf4j
@Service
@DS("master")
@RequiredArgsConstructor
public class TenantDatasourceServiceImpl implements TenantDatasourceService {

    private final InitSystemContext initSystemContext;
  

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean connect(TenantConnectDTO tenantConnect) {
        return initSystemContext.initConnect(tenantConnect);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean deleteAll(List<Long> ids) {
//        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
//        if (tenantCodeList.isEmpty()) {
//            return true;
//        }
//        removeByIds(ids);
//        return initSystemContext.delete(ids, tenantCodeList);
    	return true;
    }

}

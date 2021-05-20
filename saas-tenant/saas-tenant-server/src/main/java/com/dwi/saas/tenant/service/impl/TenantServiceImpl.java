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
public class TenantServiceImpl extends SuperCacheServiceImpl<TenantMapper, Tenant> implements TenantService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new TenantCacheKeyBuilder();
    }


    /**
     * tenant_name:{tenantCode} -> id 只存租户的id，然后根据id再次查询缓存，这样子的好处是，删除或者修改租户信息时，只需要根据id淘汰缓存即可
     * 缺点就是 每次查询，需要多查一次缓存
     *
     * @param tenant
     * @return
     */
    @Override
    public Tenant getByCode(String tenant) {
        Function<CacheKey, Object> loader = (k) ->
                getObj(Wraps.<Tenant>lbQ().select(Tenant::getId).eq(Tenant::getCode, tenant), Convert::toLong);
        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant);
        return getByKey(cacheKey, loader);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Tenant save(TenantSaveDTO data) {
        // defaults 库
        isFalse(check(data.getCode()), "编码重复，请重新输入");

        // 1， 保存租户 (默认库)
        Tenant tenant = BeanPlusUtil.toBean(data, Tenant.class);
        tenant.setStatus(TenantStatusEnum.WAIT_INIT);
        tenant.setType(TenantTypeEnum.CREATE);
        // defaults 库
        save(tenant);

        CacheKey cacheKey = new TenantCodeCacheKeyBuilder().key(tenant.getCode());
        cacheOps.set(cacheKey, tenant.getId());
        return tenant;
    }

    @Override
    public boolean check(String tenantCode) {
        return super.count(Wraps.<Tenant>lbQ().eq(Tenant::getCode, tenantCode)) > 0;
    }

    

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean delete(List<Long> ids) {
        List<String> tenantCodeList = listObjs(Wraps.<Tenant>lbQ().select(Tenant::getCode).in(Tenant::getId, ids), Convert::toStr);
        if (tenantCodeList.isEmpty()) {
            return true;
        }
        return removeByIds(ids);
    }

    @Override
    public List<Tenant> find() {
        return list(Wraps.<Tenant>lbQ().eq(Tenant::getStatus, TenantStatusEnum.NORMAL));
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Boolean updateStatus(List<Long> ids, TenantStatusEnum status) {
        boolean update = super.update(Wraps.<Tenant>lbU().set(Tenant::getStatus, status)
                .in(Tenant::getId, ids));

        delCache(ids);
        return update;
    }
}

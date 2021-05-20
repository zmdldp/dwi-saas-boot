package com.dwi.saas.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperCacheServiceImpl;
import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.database.mybatis.conditions.query.LbqWrapper;
import com.dwi.saas.authority.dao.auth.ApplicationMapper;
import com.dwi.saas.authority.domain.entity.auth.Application;
import com.dwi.saas.authority.service.auth.ApplicationService;
import com.dwi.saas.common.cache.auth.ApplicationCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.ApplicationClientCacheKeyBuilder;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.function.Function;

/**
 * <p>
 * 业务实现类
 * 应用
 * </p>
 *
 * @author dwi
 * @date 2020-12-15
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class ApplicationServiceImpl extends SuperCacheServiceImpl<ApplicationMapper, Application> implements ApplicationService {

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new ApplicationCacheKeyBuilder();
    }

    @Override
    public Application getByClient(String clientId, String clientSecret) {
        LbqWrapper<Application> wrapper = Wraps.<Application>lbQ()
                .select(Application::getId).eq(Application::getClientId, clientId).eq(Application::getClientSecret, clientSecret);
        Function<CacheKey, Object> loader = k -> super.getObj(wrapper, Convert::toLong);
        CacheKey cacheKey = new ApplicationClientCacheKeyBuilder().key(clientId, clientSecret);
        return getByKey(cacheKey, loader);
    }

}

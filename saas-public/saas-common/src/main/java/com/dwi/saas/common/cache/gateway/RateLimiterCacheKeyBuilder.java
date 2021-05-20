package com.dwi.saas.common.cache.gateway;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 限流 KEY
 * <p>
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class RateLimiterCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.RATE_LIMITER;
    }

}

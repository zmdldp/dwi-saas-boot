package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:TOTAL_PV -> long
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class TotalPvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TotalPvCacheKeyBuilder().key();
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TOTAL_PV;
    }
}

package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:TOTAL_LOGIN_IV -> long
 * <p>
 * #c_login_log
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class TotalLoginIvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build() {
        return new TodayLoginIvCacheKeyBuilder().key();
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TOTAL_LOGIN_IV;
    }
}

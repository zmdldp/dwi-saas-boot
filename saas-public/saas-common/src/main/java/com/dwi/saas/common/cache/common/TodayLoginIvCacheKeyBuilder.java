package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;
import java.time.LocalDate;

/**
 * 参数 KEY
 * {tenant}:TODAY_LOGIN_IV:{now} -> long
 * <p>
 * #c_login_log
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class TodayLoginIvCacheKeyBuilder implements CacheKeyBuilder {
    public static CacheKey build(LocalDate now) {
        return new TodayLoginIvCacheKeyBuilder().key(now.toString());
    }

    @Override
    public String getPrefix() {
        return CacheKeyDefinition.TODAY_LOGIN_IV;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofDays(2L);
    }
}

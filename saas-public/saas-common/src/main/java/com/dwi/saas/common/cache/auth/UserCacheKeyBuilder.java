package com.dwi.saas.common.cache.auth;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 系统用户 KEY
 * <p>
 * #c_user
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class UserCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}

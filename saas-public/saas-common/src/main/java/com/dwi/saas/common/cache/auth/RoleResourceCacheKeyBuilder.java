package com.dwi.saas.common.cache.auth;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 资源 KEY
 * <p>
 * #c_role_authority
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class RoleResourceCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.ROLE_RESOURCE;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }


}

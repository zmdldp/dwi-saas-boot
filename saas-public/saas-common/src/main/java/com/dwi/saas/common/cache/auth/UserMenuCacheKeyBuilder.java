package com.dwi.saas.common.cache.auth;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 用户菜单 KEY
 * <p>
 * #c_role_authority & #c_user_role
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class UserMenuCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_MENU;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(12);
    }
}

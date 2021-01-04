package com.dwi.saas.common.cache.auth;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 系统用户 KEY
 * <p>
 * #c_user
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class UserAccountCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.USER_ACCOUNT;
    }
}

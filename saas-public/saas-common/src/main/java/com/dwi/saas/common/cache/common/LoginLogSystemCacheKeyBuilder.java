package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * {tenant}:LOGIN_LOG_SYSTEM -> long
 * <p>
 * #c_login_log
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class LoginLogSystemCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.LOGIN_LOG_SYSTEM;
    }

}

package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 参数 KEY
 * <p>
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class CaptchaCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.CAPTCHA;
    }

    @Override
    public String getTenant() {
        return null;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofMinutes(15);
    }
}

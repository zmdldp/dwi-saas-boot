package com.dwi.saas.common.cache.core;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

import java.time.Duration;

/**
 * 岗位 KEY
 * <p>
 * #c_station
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class StationCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.STATION;
    }

    @Override
    public Duration getExpire() {
        return Duration.ofHours(24);
    }
}

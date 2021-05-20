package com.dwi.saas.common.cache.gateway;

import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 阻止列表 KEY
 * <p>
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class BlockListCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.BLOCKLIST;
    }

}

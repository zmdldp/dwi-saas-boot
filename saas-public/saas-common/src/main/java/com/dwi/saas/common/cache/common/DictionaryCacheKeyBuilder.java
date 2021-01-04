package com.dwi.saas.common.cache.common;


import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.saas.common.cache.CacheKeyDefinition;

/**
 * 参数 KEY
 * <p>
 * #c_dictionary_item
 *
 * @author dwi
 * @date 2020/9/20 6:45 下午
 */
public class DictionaryCacheKeyBuilder implements CacheKeyBuilder {
    @Override
    public String getPrefix() {
        return CacheKeyDefinition.DICTIONARY;
    }
}

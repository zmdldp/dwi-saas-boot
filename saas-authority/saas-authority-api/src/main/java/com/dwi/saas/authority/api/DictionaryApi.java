package com.dwi.saas.authority.api;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 数据字典API
 *
 * @author dwi
 * @date 2019/07/26
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "dictionary",
//        qualifier = "dictionaryApi", fallback = DictionaryApiFallback.class)
@RequestMapping("/dictionary")
public interface DictionaryApi {

    /**
     * 根据 code 查询字典
     *
     * @param codes 字典编码
     * @return 字典
     */
    @GetMapping("/findDictionaryItem")
    Map<Serializable, Object> findDictionaryItem(@RequestParam(value = "codes") Set<Serializable> codes);
}

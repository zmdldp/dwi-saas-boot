//package com.dwi.saas.authority;
//
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.io.Serializable;
//import java.util.Map;
//import java.util.Set;
//
///**
// * 数据字典API
// *
// * @author dwi
// * @date 2020/07/26
// */
//public interface DictionaryApi {
//
//    /**
//     * 根据 code 查询字典
//     *
//     * @param codes 字典编码
//     * @return 字典
//     */
//    @GetMapping("/findDictionaryItem")
//    Map<Serializable, Object> findDictionaryItem(@RequestParam(value = "codes") Set<Serializable> codes);
//}

package com.dwi.saas.authority.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.basic.base.R;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = ParameterBizApiFallback.class
//        , path = "/parameter", qualifier = "parameterBizApi")
@RequestMapping("/parameter")
public interface ParameterBizApi {

    /**
     * 根据key查询参数值
     *
     */
    @GetMapping("/getValue")
    public R<String> getValue(@RequestParam("key") String key, @RequestParam("defVal") String defVal);

}

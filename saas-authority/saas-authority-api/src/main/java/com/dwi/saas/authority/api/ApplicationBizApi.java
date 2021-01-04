package com.dwi.saas.authority.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.api.domain.Application;

import io.swagger.annotations.ApiOperation;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = ApplicationBizApiFallback.class
//        , path = "/application", qualifier = "applicationBizApi")
@RequestMapping("/application")
public interface ApplicationBizApi {


    /**
     * 查询客户端应用信息 ADD 2020-12-16
     *
     * @param clientId
     * @param clientSecret
     * @return
     */
    @ApiOperation(value = "查询客户端应用信息", notes = "查询客户端应用信息")
    @GetMapping(value = "/findByClient")
    public R<Application> getApplicationByClient(@RequestParam("clientId") String clientId, @RequestParam("clientSecret") String clientSecret);

}

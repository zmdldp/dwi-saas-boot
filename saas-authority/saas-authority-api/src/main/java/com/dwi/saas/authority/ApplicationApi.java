package com.dwi.saas.authority;

import io.swagger.annotations.ApiOperation;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.domain.entity.auth.Application;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
public interface ApplicationApi {

    
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

package com.dwi.saas.authority.controller.auth;


import cn.hutool.core.util.RandomUtil;
import com.github.xiaoymin.knife4j.annotations.ApiSupport;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperCacheController;
import com.dwi.saas.authority.ApplicationApi;
import com.dwi.saas.authority.domain.dto.auth.ApplicationPageQuery;
import com.dwi.saas.authority.domain.dto.auth.ApplicationSaveDTO;
import com.dwi.saas.authority.domain.dto.auth.ApplicationUpdateDTO;
import com.dwi.saas.authority.domain.entity.auth.Application;
import com.dwi.saas.authority.service.auth.ApplicationService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 应用
 * </p>
 *
 * @author dwi
 * @date 2020-12-15
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/application")
@Api(value = "Application", tags = "应用")
@ApiSupport(author = "dwi")
@PreAuth(replace = "authority:application:")
public class ApplicationController extends SuperCacheController<ApplicationService, Long, Application, ApplicationPageQuery, ApplicationSaveDTO, ApplicationUpdateDTO>
 implements ApplicationApi{

    @Override
    public R<Application> handlerSave(ApplicationSaveDTO applicationSaveDTO) {
        applicationSaveDTO.setClientId(RandomUtil.randomString(24));
        applicationSaveDTO.setClientSecret(RandomUtil.randomString(32));
        return super.handlerSave(applicationSaveDTO);
    }

    
    /**
     * 查询客户端应用信息 ADD 2020-12-16
     * 
     * @param clientId
     * @param clientSecret
     * @return
     */
    @ApiOperation(value = "查询客户端应用信息", notes = "查询客户端应用信息")
    @GetMapping(value = "/findByClient")
    public R<Application> getApplicationByClient(String clientId, String clientSecret) {
    	return success(baseService.getByClient(clientId, clientSecret));
    }


}

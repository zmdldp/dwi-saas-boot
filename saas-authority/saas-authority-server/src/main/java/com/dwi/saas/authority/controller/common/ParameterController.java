package com.dwi.saas.authority.controller.common;


import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.saas.authority.biz.service.common.ParameterService;
import com.dwi.saas.authority.domain.dto.common.ParameterPageQuery;
import com.dwi.saas.authority.domain.dto.common.ParameterSaveDTO;
import com.dwi.saas.authority.domain.dto.common.ParameterUpdateDTO;
import com.dwi.saas.authority.domain.entity.common.Parameter;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 参数配置
 * </p>
 *
 * @author dwi
 * @date 2020-02-05
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/parameter")
@Api(value = "Parameter", tags = "参数配置")
@PreAuth(replace = "authority:parameter:")
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageQuery, ParameterSaveDTO, ParameterUpdateDTO> {

}

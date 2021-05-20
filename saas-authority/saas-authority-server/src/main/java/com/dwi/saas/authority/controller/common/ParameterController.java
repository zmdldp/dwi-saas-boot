package com.dwi.saas.authority.controller.common;


import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.utils.TreeUtil;
import com.dwi.saas.authority.ParameterApi;
import com.dwi.saas.authority.domain.dto.common.ParameterPageQuery;
import com.dwi.saas.authority.domain.dto.common.ParameterSaveDTO;
import com.dwi.saas.authority.domain.dto.common.ParameterUpdateDTO;
import com.dwi.saas.authority.domain.entity.auth.Menu;
import com.dwi.saas.authority.domain.entity.common.Parameter;
import com.dwi.saas.authority.service.common.ParameterService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
public class ParameterController extends SuperController<ParameterService, Long, Parameter, ParameterPageQuery, ParameterSaveDTO, ParameterUpdateDTO> 
	implements ParameterApi{

	
	 /**
     * 根据key查询参数值
     *
     */
    @ApiOperation(value = "根据key查询参数值", notes = "根据key查询参数值")
    @SysLog("查询参数值")
    @Override
    public R<String> getValue(String key, String defVal) {
    	String value = baseService.getValue(key, defVal);
        return success(value);
    }
}

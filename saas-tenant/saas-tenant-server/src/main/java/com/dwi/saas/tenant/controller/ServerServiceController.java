package com.dwi.saas.tenant.controller;

import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.saas.tenant.domain.vo.ServerServiceVO;
import com.dwi.saas.tenant.rc.ServerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 服务
 * </p>
 *
 * @author dwi
 * @date 2021-03-03
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/service")
@Api(value = "ServerService", tags = "服务")
@PreAuth(enabled = false)
@SysLog(enabled = false)
@RequiredArgsConstructor
public class ServerServiceController  {
	
	private final ServerService serverService;
	
	

    @ApiOperation(value = "服务列表")
    @GetMapping("/list")
    public R<List<ServerServiceVO>> list() {
    	List<ServerServiceVO> list = serverService.list();
		return R.success(list);
    }
}

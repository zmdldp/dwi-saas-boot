package com.dwi.saas.tenant.controller;

import com.baomidou.dynamic.datasource.spring.boot.autoconfigure.DataSourceProperty;
import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.basic.datasource.plugin.biz.service.DatasourceConfigService;
import com.dwi.basic.datasource.plugin.domain.entity.DatasourceConfig;
import com.dwi.saas.tenant.domain.dto.DatasourceConfigPageQuery;
import com.dwi.saas.tenant.domain.dto.DatasourceConfigSaveDTO;
import com.dwi.saas.tenant.domain.dto.DatasourceConfigUpdateDTO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * <p>
 * 前端控制器
 * 数据源
 * </p>
 *
 * @author dwi
 * @date 2020-08-21
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/datasourceConfig")
@Api(value = "DatasourceConfig", tags = "数据源")
@PreAuth(enabled = false)
@SysLog(enabled = false)
public class DatasourceConfigController extends SuperController<DatasourceConfigService, Long, DatasourceConfig, DatasourceConfigPageQuery, DatasourceConfigSaveDTO, DatasourceConfigUpdateDTO> {

    @ApiOperation(value = "测试数据库链接")
    @PostMapping("/testConnect")
    public R<Boolean> testConnect(@RequestBody DataSourceProperty dataSourceProperty) {
        return R.success(baseService.testConnection(dataSourceProperty));
    }
}

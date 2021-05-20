package com.dwi.saas.authority.controller.common;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.DeleteController;
import com.dwi.basic.base.controller.PoiController;
import com.dwi.basic.base.controller.SuperSimpleController;
import com.dwi.basic.base.request.PageParams;
import com.dwi.basic.log.entity.OptLogDTO;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.saas.authority.OptLogApi;
import com.dwi.saas.authority.domain.dto.common.OptLogResult;
import com.dwi.saas.authority.domain.entity.common.OptLog;
import com.dwi.saas.authority.service.common.OptLogService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static com.dwi.saas.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

/**
 * <p>
 * 前端控制器
 * 系统日志
 * </p>
 *
 * @author dwi
 * @date 2020-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/optLog")
@Api(value = "OptLog", tags = "系统日志")
@PreAuth(replace = "authority:optLog:")
public class OptLogController extends SuperSimpleController<OptLogService, OptLog>
        implements DeleteController<OptLog, Long>, PoiController<OptLog, OptLog>,
        OptLogApi {

    @ApiOperation(value = "分页列表查询")
    @PostMapping(value = "/page")
    @PreAuth("hasAnyPermission('{}view')")
    public R<IPage<OptLog>> page(@RequestBody @Validated PageParams<OptLog> params) {
        IPage<OptLog> page = params.buildPage();
        query(params, page, null);
        return R.success(page);
    }

    /**
     * 查询
     *
     * @param id 主键id
     * @return 查询结果
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "id", value = "主键", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "单体查询", notes = "单体查询")
    @GetMapping("/{id}")
    @PreAuth("hasAnyPermission('{}view')")
    public R<OptLogResult> get(@PathVariable Long id) {
        return success(baseService.getOptLogResultById(id));
    }


    @ApiOperation("清空日志")
    @DeleteMapping("clear")
    @SysLog("清空日志")
    public R<Boolean> clear(@RequestParam(required = false, defaultValue = "1") Integer type) {
        LocalDateTime clearBeforeTime = null;
        Integer clearBeforeNum = null;
        if (type == 1) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-1);
        } else if (type == 2) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-3);
        } else if (type == 3) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-6);
        } else if (type == 4) {
            clearBeforeTime = LocalDateTime.now().plusMonths(-12);
        } else if (type == 5) {
            // 清理一千条以前日志数据
            clearBeforeNum = 1000;
        } else if (type == 6) {
            // 清理一万条以前日志数据
            clearBeforeNum = 10000;
        } else if (type == 7) {
            // 清理三万条以前日志数据
            clearBeforeNum = 30000;
        } else if (type == 8) {
            // 清理十万条以前日志数据
            clearBeforeNum = 100000;
        }
        return success(baseService.clearLog(clearBeforeTime, clearBeforeNum));
    }
    
    
    /**
     * 保存系统日志
     *
     * @param data 保存对象
     * @return 保存结果
     */
    @PostMapping
    @ApiOperation(value = "保存系统日志", notes = "保存系统日志不为空的字段")
    @Override
    public R<OptLog> save(@RequestBody OptLogDTO log) {
        baseService.save(log);
        return R.success(BeanPlusUtil.toBean(log, OptLog.class));
    }

}

package com.dwi.saas.example.controller.swagger;

import com.dwi.basic.annotation.base.IgnoreResponseBodyAdvice;
import com.dwi.basic.annotation.user.LoginUser;
import com.dwi.basic.base.R;
import com.dwi.basic.security.model.SysUser;
import com.dwi.saas.demo.domain.dto.DateDTO;
import com.dwi.saas.demo.domain.entity.Order;
import com.dwi.saas.example.domain.dto.ObjDTO;
import com.dwi.saas.example.domain.entity.UserExampleTest;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springfox.documentation.annotations.ApiIgnore;

import java.util.Arrays;

import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_BOOLEAN;
import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_MULTIPART_FILE;
import static com.dwi.saas.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

/**
 * @author dwi
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/swagger")
@AllArgsConstructor
@Api(value = "swagger", tags = "swagger")
public class SwaggerTestController {

    @PostMapping(value = "/postJson")
    public R postJson(@ApiIgnore @LoginUser SysUser user, @RequestBody Order order) {
        log.info("order={}", order);
        return R.success(order);
    }

    @PostMapping(value = "/postFrom23")
    public Order postFrom23(Order order) {
        log.info("user={}", order);
        return order;
    }

    @PostMapping(value = "/postFrom33")
    public Order postFrom33(Order order) {
        log.info("user={}", order);
        int a = 1 / 0;
        return order;
    }

    @PostMapping(value = "/postFrom13")
    @IgnoreResponseBodyAdvice
    public Order postFrom13(Order order) {
        log.info("user={}", order);
        return order;
    }

    @PostMapping(value = "/postFrom")
    public R postFrom(Order order) {
        log.info("user={}", order);
        return R.success(order);
    }

    @PostMapping(value = "/dateForm")
    public R dateForm(DateDTO date) {
        log.info("user={}", date);
        return R.success(date);
    }

    @PostMapping(value = "/dateJson")
    public R dateJson(@RequestBody DateDTO date) {
        log.info("user={}", date);
        return R.success(date);
    }

    @PostMapping(value = "/userJson")
    public R userJson(@RequestBody UserExampleTest userExampleTest) {
        log.info("user={}", userExampleTest);
        return R.success(userExampleTest);
    }

    @PostMapping(value = "/userForm")
    public R userForm(UserExampleTest userExampleTest) {
        log.info("user={}", userExampleTest);
        return R.success(userExampleTest);
    }

    @PostMapping(value = "/objdto")
    public R<ObjDTO> objdto() {
        ObjDTO s = new ObjDTO();
        s.setXx(Arrays.asList("123", "aa"));
        s.setYy(Arrays.asList("123", "aa"));

        return R.success(s);
    }

    @ApiOperation(value = "附件上传", notes = "附件上传")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "isSingle", value = "是否单文件", dataType = DATA_TYPE_BOOLEAN, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "id", value = "文件id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizId", value = "业务id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "bizType", value = "业务类型", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "file", value = "附件", dataType = DATA_TYPE_MULTIPART_FILE, allowMultiple = true, required = true),
    })
    @PostMapping(value = "/upload")
    public R upload(
            @RequestParam(value = "file") MultipartFile file,
            @RequestParam(value = "isSingle", required = false, defaultValue = "false") Boolean isSingle,
            @RequestParam(value = "id", required = false) Long id,
            @RequestParam(value = "token", required = false) String token,
            @RequestParam(value = "bizType", required = false) String bizType) {
        return R.success();
    }
}

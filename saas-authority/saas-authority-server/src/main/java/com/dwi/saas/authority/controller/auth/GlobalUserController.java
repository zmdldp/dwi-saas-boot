package com.dwi.saas.authority.controller.auth;


import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.basic.base.entity.SuperEntity;
import com.dwi.basic.base.request.PageParams;
import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.basic.utils.StrHelper;
import com.dwi.saas.authority.domain.dto.auth.GlobalUserPageDTO;
import com.dwi.saas.authority.domain.dto.auth.GlobalUserSaveDTO;
import com.dwi.saas.authority.domain.dto.auth.GlobalUserUpdateDTO;
import com.dwi.saas.authority.domain.dto.auth.UserUpdatePasswordDTO;
import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.saas.authority.service.auth.UserService;
import com.dwi.saas.common.constant.BizConstant;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static com.dwi.saas.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

/**
 * <p>
 * 前端控制器
 * 全局账号
 * </p>
 *
 * @author dwi
 * @date 2020-10-25
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/globalUser")
@Api(value = "GlobalUser", tags = "全局账号")
@SysLog(enabled = false)
@RequiredArgsConstructor
public class GlobalUserController extends SuperController<UserService, Long, User, GlobalUserPageDTO, GlobalUserSaveDTO, GlobalUserUpdateDTO> {

	//TODO TEST
    @Override
    public R<User> handlerSave(GlobalUserSaveDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        user.setName(StrHelper.getOrDef(model.getName(), model.getAccount()));
        if (StrUtil.isEmpty(user.getPassword())) {
            user.setPassword(BizConstant.DEF_PASSWORD);
        }
        user.setState(true);
        baseService.initUser(user);
        return success(user);
    }

    @Override
    public R<User> handlerUpdate(GlobalUserUpdateDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        User user = BeanPlusUtil.toBean(model, User.class);
        baseService.updateUser(user);
        return success(user);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "account", value = "账号", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "检测账号是否可用", notes = "检测账号是否可用")
    @GetMapping("/check")
    public R<Boolean> check(@RequestParam String tenantCode, @RequestParam String account) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.check(account));
    }

    @Override
    public void query(PageParams<GlobalUserPageDTO> params, IPage<User> page, Long defSize) {
        GlobalUserPageDTO model = params.getModel();
        ContextUtil.setTenant(model.getTenantCode());

//        LbqWrapper<User> wrapper = Wraps.lbq(null, params.getExtra(), User.class);
//        wrapper.like(User::getAccount, model.getAccount())
//                .like(User::getName, model.getName());

        baseService.pageByRole(page, params);
    }


    @ApiOperation(value = "删除用户")
    @DeleteMapping("/delete")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "tenantCode", value = "企业编码", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "ids[]", value = "主键id", dataType = DATA_TYPE_STRING, allowMultiple = true, paramType = PARAM_TYPE_QUERY),
    })
    public R<Boolean> delete(@RequestParam String tenantCode, @RequestParam("ids[]") List<Long> ids) {
        ContextUtil.setTenant(tenantCode);
        return success(baseService.remove(ids));
    }


    /**
     * 修改密码
     *
     * @param model 修改实体
     */
    @ApiOperation(value = "修改密码", notes = "修改密码")
    @PutMapping("/reset")
    public R<Boolean> updatePassword(@RequestBody @Validated(SuperEntity.Update.class) UserUpdatePasswordDTO model) {
        ContextUtil.setTenant(model.getTenantCode());
        return success(baseService.reset(model));
    }
}

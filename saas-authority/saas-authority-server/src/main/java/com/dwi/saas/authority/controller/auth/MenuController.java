package com.dwi.saas.authority.controller.auth;

import com.dwi.basic.annotation.log.SysLog;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.annotation.user.LoginUser;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperCacheController;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.security.model.SysUser;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.basic.utils.TreeUtil;
import com.dwi.saas.authority.domain.dto.auth.MenuSaveDTO;
import com.dwi.saas.authority.domain.dto.auth.MenuUpdateDTO;
import com.dwi.saas.authority.domain.dto.auth.VueRouter;
import com.dwi.saas.authority.domain.entity.auth.Menu;
import com.dwi.saas.authority.service.auth.MenuService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dwi.basic.dozer.DozerUtils;

import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_LONG;
import static com.dwi.saas.common.constant.SwaggerConstants.DATA_TYPE_STRING;
import static com.dwi.saas.common.constant.SwaggerConstants.PARAM_TYPE_QUERY;

import java.util.List;


/**
 * <p>
 * 前端控制器
 * 菜单
 * </p>
 *
 * @author dwi
 * @date 2020-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/menu")
@Api(value = "Menu", tags = "菜单")
@PreAuth(replace = "authority:menu:")
@RequiredArgsConstructor
public class MenuController extends SuperCacheController<MenuService, Long, Menu, Menu, MenuSaveDTO, MenuUpdateDTO> {

    private final DozerUtils dozer;
	
	@Override
    public R<Menu> handlerSave(MenuSaveDTO menuSaveDTO) {
        Menu menu = BeanPlusUtil.toBean(menuSaveDTO, Menu.class);
        baseService.saveWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Menu> handlerUpdate(MenuUpdateDTO model) {
        Menu menu = BeanPlusUtil.toBean(model, Menu.class);
        baseService.updateWithCache(menu);
        return success(menu);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        baseService.removeByIdWithCache(ids);
        return success();
    }

    /**
     * 查询系统中所有的的菜单树结构， 不用缓存，因为该接口很少会使用，就算使用，也会管理员维护菜单时使用
     *
     */
    @ApiOperation(value = "查询系统所有的菜单", notes = "查询系统所有的菜单")
    @GetMapping("/tree")
    @SysLog("查询系统所有的菜单")
    public R<List<Menu>> allTree() {
        List<Menu> list = baseService.list(Wraps.<Menu>lbQ().orderByAsc(Menu::getSortValue));
        return success(TreeUtil.buildTree(list));
    }
    
    
    //move from Oauth
    
    /**
     * 查询用户可用的所有菜单
     *
     * @param group  分组 <br>
     * @param userId 指定用户id
     */
    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "查询用户可用的所有菜单", notes = "查询用户可用的所有菜单")
    @GetMapping("/menus")
    public R<List<Menu>> myMenus(@RequestParam(value = "group", required = false) String group,
                                 @RequestParam(value = "userId", required = false) Long userId,
                                 @ApiIgnore @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Menu> list = baseService.findVisibleMenu(group, userId);
        List<Menu> tree = TreeUtil.buildTree(list);
        return R.success(tree);
    }

    @ApiImplicitParams({
            @ApiImplicitParam(name = "group", value = "菜单组", dataType = DATA_TYPE_STRING, paramType = PARAM_TYPE_QUERY),
            @ApiImplicitParam(name = "userId", value = "用户id", dataType = DATA_TYPE_LONG, paramType = PARAM_TYPE_QUERY),
    })
    @ApiOperation(value = "查询用户可用的所有菜单路由树", notes = "查询用户可用的所有菜单路由树")
    @GetMapping("/router")
    public R<List<VueRouter>> myRouter(@RequestParam(value = "group", required = false) String group,
                                       @RequestParam(value = "userId", required = false) Long userId,
                                       @ApiIgnore @LoginUser SysUser sysUser) {
        if (userId == null || userId <= 0) {
            userId = sysUser.getId();
        }
        List<Menu> list = baseService.findVisibleMenu(group, userId);
        List<VueRouter> treeList = dozer.mapList(list, VueRouter.class);
        return R.success(TreeUtil.buildTree(treeList));
    }
}

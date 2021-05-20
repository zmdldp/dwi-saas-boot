package com.dwi.saas.authority.controller.auth;

import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.annotation.user.LoginUser;
import com.dwi.basic.base.R;
import com.dwi.basic.base.controller.SuperCacheController;
import com.dwi.basic.security.model.SysUser;
import com.dwi.basic.security.properties.SecurityProperties;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.basic.utils.CollHelper;
import com.dwi.basic.utils.StrPool;
import com.dwi.saas.authority.domain.dto.auth.AuthorityResourceDTO;
import com.dwi.saas.authority.domain.dto.auth.ResourceQueryDTO;
import com.dwi.saas.authority.domain.dto.auth.ResourceSaveDTO;
import com.dwi.saas.authority.domain.dto.auth.ResourceUpdateDTO;
import com.dwi.saas.authority.domain.entity.auth.Resource;
//import com.dwi.saas.oauth.controller.AuthorityResourceDTO;
//import com.dwi.saas.oauth.controller.ResourceQueryDTO;
//import com.dwi.saas.oauth.controller.Role;
//import com.dwi.saas.oauth.controller.RoleService;
import com.dwi.saas.authority.domain.entity.auth.Role;
import com.dwi.saas.authority.service.auth.ResourceService;
import com.dwi.saas.authority.service.auth.RoleService;

import cn.hutool.core.util.ObjectUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import springfox.documentation.annotations.ApiIgnore;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 前端控制器
 * 资源
 * </p>
 *
 * @author dwi
 * @date 2020-07-22
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/resource")
@Api(value = "Resource", tags = "资源")
@PreAuth(replace = "authority:resource:")
@RequiredArgsConstructor
public class ResourceController extends SuperCacheController<ResourceService, Long, Resource, Resource, ResourceSaveDTO, ResourceUpdateDTO> {
   
	private final RoleService roleService;
	
	private final SecurityProperties securityProperties;
	
	@Override
    public R<Resource> handlerSave(ResourceSaveDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.saveWithCache(resource);
        return success(resource);
    }

    @Override
    public R<Boolean> handlerDelete(List<Long> ids) {
        return success(baseService.removeByIdWithCache(ids));
    }

    @Override
    public R<Resource> handlerUpdate(ResourceUpdateDTO data) {
        Resource resource = BeanPlusUtil.toBean(data, Resource.class);
        baseService.updateById(resource);
        return success(resource);
    }
    
    
    //move from Oauth

    /**
     * 查询用户可用的所有资源
     *
     * @param resource <br>
     *                 menuId 菜单 <br>
     *                 userId 当前登录人id
     */
    @ApiOperation(value = "查询用户可用的所有资源", notes = "查询用户可用的所有资源")
    @GetMapping("/visible")
    public R<AuthorityResourceDTO> visible(ResourceQueryDTO resource, @ApiIgnore @LoginUser SysUser sysUser) {
        if (resource == null) {
            resource = new ResourceQueryDTO();
        }

        if (resource.getUserId() == null) {
            resource.setUserId(sysUser.getId());
        }
        List<Resource> resourceList = baseService.findVisibleResource(resource);
        List<Role> roleList = roleService.findRoleByUserId(resource.getUserId());
        return R.success(AuthorityResourceDTO.builder()
                .roleList(roleList.parallelStream().filter(ObjectUtil::isNotEmpty).map(Role::getCode).distinct().collect(Collectors.toList()))
                .resourceList(CollHelper.split(resourceList, Resource::getCode, StrPool.SEMICOLON))
                .caseSensitive(securityProperties.getCaseSensitive())
                .enabled(securityProperties.getEnabled())
                .build());
    }
}

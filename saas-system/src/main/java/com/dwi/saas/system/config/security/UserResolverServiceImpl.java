package com.dwi.saas.system.config.security;


import com.dwi.basic.base.R;
import com.dwi.basic.security.feign.UserQuery;
import com.dwi.basic.security.feign.UserResolverService;
import com.dwi.basic.security.model.SysUser;
import com.dwi.saas.authority.service.auth.UserService;

/**
 * 本地 实现
 *
 * @author dwi
 * @date 2020年02月24日10:51:46
 */
public class UserResolverServiceImpl implements UserResolverService {
    private final UserService userService;

    public UserResolverServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public R<SysUser> getById(Long id, UserQuery userQuery) {
        return R.success(userService.getSysUserById(id, userQuery));
    }
}

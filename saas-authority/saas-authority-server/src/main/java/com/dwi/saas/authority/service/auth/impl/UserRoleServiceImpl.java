package com.dwi.saas.authority.service.auth.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.exception.BizException;
import com.dwi.saas.authority.dao.auth.RoleMapper;
import com.dwi.saas.authority.dao.auth.UserRoleMapper;
import com.dwi.saas.authority.domain.entity.auth.Role;
import com.dwi.saas.authority.domain.entity.auth.UserRole;
import com.dwi.saas.authority.service.auth.UserRoleService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.dwi.saas.common.constant.BizConstant.INIT_ROLE_CODE;

/**
 * <p>
 * 业务实现类
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Slf4j
@Service
@DS("#thread.tenant")
@RequiredArgsConstructor
public class UserRoleServiceImpl extends SuperServiceImpl<UserRoleMapper, UserRole> implements UserRoleService {

    private final RoleMapper roleMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean initAdmin(Long userId) {
        Role role = roleMapper.selectOne(Wraps.<Role>lbQ().eq(Role::getCode, INIT_ROLE_CODE));
        if (role == null) {
            throw BizException.wrap("初始化用户角色失败, 无法查询到内置角色:%s", INIT_ROLE_CODE);
        }
        UserRole userRole = UserRole.builder()
                .userId(userId).roleId(role.getId())
                .build();

        return super.save(userRole);
    }
}

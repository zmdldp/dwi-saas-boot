package com.dwi.saas.authority.dao.auth;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.entity.auth.UserRole;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 角色分配
 * 账号角色绑定
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Repository
public interface UserRoleMapper extends SuperMapper<UserRole> {

}

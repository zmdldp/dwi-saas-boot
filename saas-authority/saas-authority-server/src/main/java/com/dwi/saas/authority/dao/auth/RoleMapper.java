package com.dwi.saas.authority.dao.auth;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.entity.auth.Role;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 角色
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Repository
public interface RoleMapper extends SuperMapper<Role> {
    /**
     * 查询用户拥有的角色
     *
     * @param userId 用户id
     * @return 角色
     */
    List<Role> findRoleByUserId(@Param("userId") Long userId);

    /**
     * 根据角色编码查询用户ID
     *
     * @param codes 角色编码
     * @return 用户id
     */
    List<Long> findUserIdByCode(@Param("codes") String[] codes);
}

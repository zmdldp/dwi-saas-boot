package com.dwi.saas.authority.service.auth;

import com.dwi.basic.base.service.SuperService;
import com.dwi.saas.authority.domain.entity.auth.RoleOrg;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 角色组织关系
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
public interface RoleOrgService extends SuperService<RoleOrg> {

    /**
     * 根据角色id查询
     *
     * @param roleId 角色id
     * @return 组织Id
     */
    List<Long> listOrgByRoleId(Long roleId);
}

package com.dwi.saas.authority.service.auth.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.saas.authority.dao.auth.RoleOrgMapper;
import com.dwi.saas.authority.domain.entity.auth.RoleOrg;
import com.dwi.saas.authority.service.auth.RoleOrgService;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色组织关系
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class RoleOrgServiceImpl extends SuperServiceImpl<RoleOrgMapper, RoleOrg> implements RoleOrgService {
    @Override
    public List<Long> listOrgByRoleId(Long roleId) {
        List<RoleOrg> list = super.list(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, roleId));
        return list.stream().mapToLong(RoleOrg::getOrgId).boxed().collect(Collectors.toList());
    }
}

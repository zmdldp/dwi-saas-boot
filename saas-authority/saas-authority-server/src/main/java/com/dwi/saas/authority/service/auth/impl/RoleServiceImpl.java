package com.dwi.saas.authority.service.auth.impl;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.RandomUtil;
import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperCacheServiceImpl;
import com.dwi.basic.cache.model.CacheKey;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.security.constant.RoleConstant;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.basic.utils.StrHelper;
import com.dwi.saas.authority.dao.auth.RoleMapper;
import com.dwi.saas.authority.domain.dto.auth.RoleSaveDTO;
import com.dwi.saas.authority.domain.dto.auth.RoleUpdateDTO;
import com.dwi.saas.authority.domain.entity.auth.Role;
import com.dwi.saas.authority.domain.entity.auth.RoleAuthority;
import com.dwi.saas.authority.domain.entity.auth.RoleOrg;
import com.dwi.saas.authority.domain.entity.auth.UserRole;
import com.dwi.saas.authority.service.auth.RoleAuthorityService;
import com.dwi.saas.authority.service.auth.RoleOrgService;
import com.dwi.saas.authority.service.auth.RoleService;
import com.dwi.saas.authority.service.auth.UserRoleService;
import com.dwi.saas.authority.strategy.DataScopeContext;
import com.dwi.saas.common.cache.auth.RoleCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.RoleMenuCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.RoleResourceCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.UserMenuCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.UserResourceCacheKeyBuilder;
import com.dwi.saas.common.cache.auth.UserRoleCacheKeyBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <p>
 * 业务实现类
 * 角色
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Slf4j
@Service
@DS("#thread.tenant")
@RequiredArgsConstructor
public class RoleServiceImpl extends SuperCacheServiceImpl<RoleMapper, Role> implements RoleService {
    private final RoleOrgService roleOrgService;
    private final RoleAuthorityService roleAuthorityService;
    private final UserRoleService userRoleService;
    private final DataScopeContext dataScopeContext;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return new RoleCacheKeyBuilder();
    }

    @Override
    public boolean isPtAdmin(String code) {
        return RoleConstant.SUPER_ADMIN.equals(code);
    }


    /**
     * 删除角色时，需要级联删除跟角色相关的一切资源：
     * 1，角色本身
     * 2，角色-组织：
     * 3，角色-权限（菜单和按钮）：
     * 4，角色-用户：角色拥有的用户
     * 5，用户-权限：
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean removeByIdWithCache(List<Long> ids) {
        if (ids.isEmpty()) {
            return true;
        }
        // 橘色
        boolean removeFlag = removeByIds(ids);
        // 角色组织
        roleOrgService.remove(Wraps.<RoleOrg>lbQ().in(RoleOrg::getRoleId, ids));
        // 角色权限
        roleAuthorityService.remove(Wraps.<RoleAuthority>lbQ().in(RoleAuthority::getRoleId, ids));

        // 角色绑定了那些用户
        List<Long> userIds = userRoleService.listObjs(
                Wraps.<UserRole>lbQ().select(UserRole::getUserId).in(UserRole::getRoleId, ids),
                Convert::toLong);

        //角色拥有的用户
        userRoleService.remove(Wraps.<UserRole>lbQ().in(UserRole::getRoleId, ids));

        cacheOps.del(ids.stream().map(new RoleMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        cacheOps.del(ids.stream().map(new RoleResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));

        if (!userIds.isEmpty()) {
            //用户角色 、 用户菜单、用户资源
            cacheOps.del(userIds.stream().map(new UserRoleCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(userIds.stream().map(new UserResourceCacheKeyBuilder()::key).toArray(CacheKey[]::new));
            cacheOps.del(userIds.stream().map(new UserMenuCacheKeyBuilder()::key).toArray(CacheKey[]::new));
        }
        return removeFlag;
    }

    /**
     * 1、根据 {tenant}:USER_ROLE:{userId} 查询用户拥有的角色ID集合
     * 2、缓存中有，则根据角色ID集合查询 角色集合
     * 3、缓存中有查不到，则从DB查询，并写入缓存， 立即返回
     *
     * @param userId 用户id
     */
    @Override
    public List<Role> findRoleByUserId(Long userId) {
        CacheKey cacheKey = new UserRoleCacheKeyBuilder().key(userId);
        List<Role> roleList = new ArrayList<>();
        List<Long> list = cacheOps.get(cacheKey, k -> {
            roleList.addAll(baseMapper.findRoleByUserId(userId));
            return roleList.stream().map(Role::getId).collect(Collectors.toList());
        });

        if (!roleList.isEmpty()) {
            roleList.forEach(this::setCache);
            return roleList;
        } else {
            return findByIds(list, this::listByIds);
        }
    }

    /**
     * 1，保存角色
     * 2，保存 与组织的关系
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void saveRole(RoleSaveDTO data, Long userId) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        role.setCode(StrHelper.getOrDef(data.getCode(), RandomUtil.randomString(8)));
        role.setReadonly(false);
        save(role);

        saveRoleOrg(userId, role, data.getOrgList());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateRole(RoleUpdateDTO data, Long userId) {
        Role role = BeanPlusUtil.toBean(data, Role.class);
        updateById(role);

        roleOrgService.remove(Wraps.<RoleOrg>lbQ().eq(RoleOrg::getRoleId, data.getId()));
        saveRoleOrg(userId, role, data.getOrgList());

    }

    private void saveRoleOrg(Long userId, Role role, List<Long> orgList) {
        // 根据 数据范围类型 和 勾选的组织ID， 重新计算全量的组织ID
        List<Long> orgIds = dataScopeContext.getOrgIdsForDataScope(orgList, role.getDsType(), userId);
        if (orgIds != null && !orgIds.isEmpty()) {
            List<RoleOrg> list = orgIds.stream().map(orgId -> RoleOrg.builder().orgId(orgId).roleId(role.getId()).build()).collect(Collectors.toList());
            roleOrgService.saveBatch(list);
        }
    }

    @Override
    public List<Long> findUserIdByCode(String[] codes) {
        return baseMapper.findUserIdByCode(codes);
    }

    @Override
    public Boolean check(String code) {
        return super.count(Wraps.<Role>lbQ().eq(Role::getCode, code)) > 0;
    }
}

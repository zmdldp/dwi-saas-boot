package com.dwi.saas.authority.biz.strategy.impl;

import com.dwi.basic.model.RemoteData;
import com.dwi.saas.authority.biz.dao.auth.UserMapper;
import com.dwi.saas.authority.biz.service.core.OrgService;
import com.dwi.saas.authority.biz.strategy.AbstractDataScopeHandler;
import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.saas.authority.domain.entity.core.Org;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 本级以及子级
 *
 * @author dwi
 * @version 1.0
 * @date 2019-06-08 16:30
 */
@Component("THIS_LEVEL_CHILDREN")
@RequiredArgsConstructor
public class ThisLevelChildrenDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;
    private final OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = RemoteData.getKey(user.getOrg());
        List<Org> children = orgService.findChildren(Arrays.asList(orgId));
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }

}

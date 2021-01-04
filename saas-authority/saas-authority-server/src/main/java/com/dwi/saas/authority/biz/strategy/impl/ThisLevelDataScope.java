package com.dwi.saas.authority.biz.strategy.impl;

import com.dwi.basic.model.RemoteData;
import com.dwi.saas.authority.biz.dao.auth.UserMapper;
import com.dwi.saas.authority.biz.strategy.AbstractDataScopeHandler;
import com.dwi.saas.authority.domain.entity.auth.User;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * 本级
 *
 * @author dwi
 * @version 1.0
 * @date 2019-06-08 15:44
 */
@Component("THIS_LEVEL")
@RequiredArgsConstructor
public class ThisLevelDataScope implements AbstractDataScopeHandler {
    private final UserMapper userMapper;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        User user = userMapper.selectById(userId);
        if (user == null) {
            return Collections.emptyList();
        }
        Long orgId = RemoteData.getKey(user.getOrg());
        return Arrays.asList(orgId);
    }
}

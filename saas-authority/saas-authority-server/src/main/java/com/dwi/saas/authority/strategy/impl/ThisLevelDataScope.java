package com.dwi.saas.authority.strategy.impl;

import com.dwi.saas.authority.dao.auth.UserMapper;
import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.saas.authority.strategy.AbstractDataScopeHandler;

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
 * @date 2020-06-08 15:44
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
        Long orgId = user.getOrgId();
        return Arrays.asList(orgId);
    }
}

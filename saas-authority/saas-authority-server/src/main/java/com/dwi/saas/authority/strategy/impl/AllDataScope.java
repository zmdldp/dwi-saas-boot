package com.dwi.saas.authority.strategy.impl;

import com.dwi.saas.authority.domain.entity.core.Org;
import com.dwi.saas.authority.service.core.OrgService;
import com.dwi.saas.authority.strategy.AbstractDataScopeHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 所有
 *
 * @author dwi
 * @version 1.0
 * @date 2020-06-08 16:27
 */
@Component("ALL")
@RequiredArgsConstructor
public class AllDataScope implements AbstractDataScopeHandler {

    private final OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        List<Org> list = orgService.lambdaQuery().select(Org::getId).list();
        return list.stream().map(Org::getId).collect(Collectors.toList());
    }


}

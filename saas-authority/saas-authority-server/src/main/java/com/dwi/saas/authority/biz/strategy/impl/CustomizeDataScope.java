package com.dwi.saas.authority.biz.strategy.impl;

import com.dwi.basic.exception.BizException;
import com.dwi.basic.exception.code.ExceptionCode;
import com.dwi.saas.authority.biz.service.core.OrgService;
import com.dwi.saas.authority.biz.strategy.AbstractDataScopeHandler;
import com.dwi.saas.authority.domain.entity.core.Org;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义模式
 *
 * @author dwi
 * @version 1.0
 * @date 2019-06-08 16:31
 */
@Component("CUSTOMIZE")
@RequiredArgsConstructor
public class CustomizeDataScope implements AbstractDataScopeHandler {
    private final OrgService orgService;

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
        List<Org> children = orgService.findChildren(orgList);
        return children.stream().mapToLong(Org::getId).boxed().collect(Collectors.toList());
    }
}

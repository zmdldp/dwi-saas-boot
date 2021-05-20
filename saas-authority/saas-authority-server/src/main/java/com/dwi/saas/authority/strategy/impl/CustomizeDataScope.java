package com.dwi.saas.authority.strategy.impl;

import com.dwi.basic.exception.BizException;
import com.dwi.basic.exception.code.ExceptionCode;
import com.dwi.saas.authority.domain.entity.core.Org;
import com.dwi.saas.authority.service.core.OrgService;
import com.dwi.saas.authority.strategy.AbstractDataScopeHandler;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

/**
 * 自定义模式
 *
 * @author dwi
 * @version 1.0
 * @date 2020-06-08 16:31
 */
@Component("CUSTOMIZE")
@RequiredArgsConstructor
public class CustomizeDataScope implements AbstractDataScopeHandler {

    @Override
    public List<Long> getOrgIds(List<Long> orgList, Long userId) {
        if (orgList == null || orgList.isEmpty()) {
            throw new BizException(ExceptionCode.BASE_VALID_PARAM.getCode(), "自定义数据权限类型时，组织不能为空");
        }
       return orgList;
    }
}

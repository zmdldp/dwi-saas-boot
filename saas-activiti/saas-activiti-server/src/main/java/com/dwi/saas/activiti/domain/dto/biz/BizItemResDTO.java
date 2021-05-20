package com.dwi.saas.activiti.domain.dto.biz;

import com.dwi.saas.activiti.domain.entity.biz.BizItem;
import com.dwi.saas.authority.domain.entity.auth.User;

import lombok.Data;

/**
 * <p>
 * 实体类
 *
 * </p>
 *
 * @author wz
 * @since 2020-08-19
 */
@Data
public class BizItemResDTO extends BizItem{

    /**
     * 实体项公共信息-用户
     */
    private User cUser;
}

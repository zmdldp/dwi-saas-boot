package com.dwi.saas.activiti.domain.dto.biz;

import com.dwi.basic.annotation.echo.Echo;
import com.dwi.basic.model.RemoteData;
import com.dwi.saas.activiti.domain.dto.activiti.TaskResDTO;
import com.dwi.saas.activiti.domain.entity.biz.BizReimbursement;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * 活动任务返回实体
 *
 * @author wz
 * @date 2020-08-07
 */
@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
public class TaskReimbursementResDTO extends TaskResDTO {

    /**
     * 对应业务实例
     */
	@Echo(api = "bizReimbursementServiceImpl", method = "findBizByInstId")
    private RemoteData<String, BizReimbursement> biz;

}

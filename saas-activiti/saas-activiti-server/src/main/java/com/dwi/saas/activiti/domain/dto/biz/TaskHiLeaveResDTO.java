package com.dwi.saas.activiti.domain.dto.biz;

import com.dwi.basic.annotation.echo.Echo;
import com.dwi.basic.model.RemoteData;
import com.dwi.saas.activiti.domain.dto.activiti.TaskHiResDTO;
import com.dwi.saas.activiti.domain.entity.biz.BizLeave;

import lombok.AllArgsConstructor;
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
@AllArgsConstructor
public class TaskHiLeaveResDTO extends TaskHiResDTO {

    /**
     * 对应业务实例
     */
	@Echo(api = "bizLeaveServiceImpl", method = "findBizByInstId")
    protected RemoteData<String, BizLeave> biz;


}

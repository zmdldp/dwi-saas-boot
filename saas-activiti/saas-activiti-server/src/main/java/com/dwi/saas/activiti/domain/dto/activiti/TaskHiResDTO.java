package com.dwi.saas.activiti.domain.dto.activiti;

import com.dwi.basic.annotation.echo.Echo;
import com.dwi.basic.model.RemoteData;
import com.dwi.saas.activiti.domain.entity.biz.BizItem;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 历史任务返回实体
 *
 * @author wz
 * @date 2020-08-07
 */
@Data
@NoArgsConstructor
@ApiModel(value = "TaskHiResDTO", description = "历史任务返回实体")
public class TaskHiResDTO {
    /**
     * 任务id
     */
    @ApiModelProperty(value = "任务id")
    protected String id;

    /**
     * 任务名称
     */
    @ApiModelProperty(value = "任务名称")
    protected String name;

    /**
     * 审批人id
     */
    @ApiModelProperty(value = "审批人id")
    protected String assignee;

    /**
     * 租户id
     */
    @ApiModelProperty(value = "租户id")
    protected String tenantId;

    /**
     * 是否挂起
     */
    @ApiModelProperty(value = "是否挂起")
    protected Boolean isSuspended;

    /**
     * 对应定义key
     */
    @ApiModelProperty(value = "对应定义key")
    protected String taskDefKey;

    /**
     * 对应流程实例
     */
    @ApiModelProperty(value = "对应流程实例")
    @Echo(api = "myProcessInstantService", method = "findProHiInst")
    protected RemoteData<String, ProcessInstanceResDTO> inst;

    /**
     * 对应业务实例
     */
    @ApiModelProperty(value = "对应业务实例")
    @Echo(api = "bizItemServiceImpl", method = "findItemByTaskId")
    protected RemoteData<String, BizItem> item;


}

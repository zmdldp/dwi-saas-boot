package com.dwi.saas.activiti.domain.dto.biz;

//import com.dwi.saas.common.constant.DictionaryType;
import java.io.Serializable;

import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

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
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "BizItemSaveDTO", description = "业务事项")
public class BizItemSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 事项名称
     */
    @ApiModelProperty(value = "事项名称")
    @Size(max = 64, message = "事项名称长度不能超过64")
    private String itemName;
    /**
     * 事项内容
     */
    @ApiModelProperty(value = "事项内容")
    @Size(max = 64, message = "事项内容长度不能超过64")
    private String itemContent;
    /**
     * 事项备注
     */
    @ApiModelProperty(value = "事项备注")
    @Size(max = 255, message = "事项备注长度不能超过255")
    private String itemRemake;
    /**
     * 租户code
     */
    @ApiModelProperty(value = "租户code")
    @Size(max = 32, message = "租户code长度不能超过32")
    private String tenantId;
    /**
     * 任务外键
     */
    @ApiModelProperty(value = "任务外键")
    @Size(max = 64, message = "任务外键长度不能超过64")
    private String taskId;
    /**
     * 流程实例外键
     */
    @ApiModelProperty(value = "流程实例外键")
    @Size(max = 64, message = "流程实例外键长度不能超过64")
    private String instId;
    /**
     * 模块名
     */
    @ApiModelProperty(value = "模块名")
    @Size(max = 255, message = "模块名长度不能超过255")
    private String module;

}

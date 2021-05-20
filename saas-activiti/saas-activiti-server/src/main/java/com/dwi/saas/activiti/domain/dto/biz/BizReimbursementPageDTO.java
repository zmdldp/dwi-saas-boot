package com.dwi.saas.activiti.domain.dto.biz;

import com.dwi.basic.base.entity.Entity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.validation.constraints.Size;

import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 报销流程
 * </p>
 *
 * @author wz
 * @since 2020-08-31
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "BizReimbursementPageDTO", description = "报销流程")
public class BizReimbursementPageDTO extends Entity<Long> implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 报销人员
     */
    @ApiModelProperty(value = "报销人员")
    @Size(max = 32, message = "报销人员长度不能超过32")
    private String name;
    /**
     * 报销类型
     */
    @ApiModelProperty(value = "报销类型")
    @Size(max = 8, message = "报销类型长度不能超过8")
    private String type;
    /**
     * 报销说明
     */
    @ApiModelProperty(value = "报销说明")
    @Size(max = 255, message = "报销说明长度不能超过255")
    private String reason;
    @ApiModelProperty(value = "")
    private Float amount;
    @ApiModelProperty(value = "")
    private Long userId;
    /**
     * 租户code
     */
    @ApiModelProperty(value = "租户code")
    @Size(max = 32, message = "租户code长度不能超过32")
    private String tenantId;
    /**
     * 流程实例外键
     */
    @ApiModelProperty(value = "流程实例外键")
    @Size(max = 64, message = "流程实例外键长度不能超过64")
    private String instId;
    /**
     * 删除标识
     */
    @ApiModelProperty(value = "删除标识")
    private Boolean isDelete;
    /**
     * 流程是否结束
     */
    @ApiModelProperty(value = "流程是否结束")
    private Boolean isOver;

    /**
     * 是否只看本人的
     */
    @ApiModelProperty(value = "流程是否结束")
    private Boolean isMine;
}

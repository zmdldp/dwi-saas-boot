package com.dwi.saas.authority.domain.dto.auth;

import com.dwi.basic.base.entity.SuperEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 菜单
 * </p>
 *
 * @author dwi
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "MenuUpdateDTO", description = "菜单")
public class MenuUpdateDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty(value = "主键")
    @NotNull(message = "id不能为空", groups = SuperEntity.Update.class)
    private Long id;

    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "请填写名称")
    @Size(max = 255, message = "名称长度不能超过255")
    protected String label;

    /**
     * 描述
     */
    @ApiModelProperty(value = "描述")
    @Size(max = 200, message = "描述长度不能超过200")
    private String describe;
    @ApiModelProperty(value = "上级资源")
    protected Long parentId;
    /**
     * 路径
     */
    @ApiModelProperty(value = "地址栏地址")
    @NotEmpty(message = "请填写地址栏地址")
    @Size(max = 255, message = "地址栏地址长度不能超过255")
    private String path;
    /**
     * 组件
     */
    @ApiModelProperty(value = "页面代码地址")
    @NotEmpty(message = "请填写页面代码地址")
    @Size(max = 255, message = "页面代码地址长度不能超过255")
    private String component;
    /**
     * 菜单图标
     */
    @ApiModelProperty(value = "图标")
    @Size(max = 255, message = "图标长度不能超过255")
    private String icon;

    /**
     * 分组
     */
    @ApiModelProperty(value = "分组")
    @Size(max = 20, message = "分组长度不能超过20")
    private String group;

    @ApiModelProperty(value = "排序号")
    protected Integer sortValue;

    /**
     * 通用菜单
     * True表示无需分配所有人就可以访问的
     */
    @ApiModelProperty(value = "通用菜单")
    private Boolean isGeneral;
    /**
     * 状态
     */
    @ApiModelProperty(value = "状态")
    private Boolean state;
    /**
     * 内置
     */
    @ApiModelProperty(value = "内置")
    private Boolean readonly;
}

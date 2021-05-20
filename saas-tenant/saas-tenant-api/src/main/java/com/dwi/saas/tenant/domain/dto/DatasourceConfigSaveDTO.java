package com.dwi.saas.tenant.domain.dto;

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

import com.dwi.saas.tenant.domain.enumeration.TenantConnectTypeEnum;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;

/**
 * <p>
 * 实体类
 * 数据源
 * </p>
 *
 * @author dwi
 * @since 2020-11-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "DatasourceConfigSaveDTO", description = "数据源")
public class DatasourceConfigSaveDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @NotEmpty(message = "名称不能为空")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;   
    /**
     * 服务
     */
    @ApiModelProperty(value = "服务")
    @NotEmpty(message = "服务不能为空") 
    @Size(max = 100, message = "服务长度不能超过100")
    private String application;
    /**
     * 连接类型
     * #TenantConnectTypeEnum{LOCAL:本地;REMOTE:远程}
     */
    @ApiModelProperty(value = "连接类型")
    private TenantConnectTypeEnum connectType;
    /**
     * 账号
     */
    @ApiModelProperty(value = "账号")
    @NotEmpty(message = "账号不能为空")
    @Size(max = 255, message = "账号长度不能超过255")
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    @NotEmpty(message = "密码不能为空")
    @Size(max = 255, message = "密码长度不能超过255")
    private String password;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    @NotEmpty(message = "链接不能为空")
    @Size(max = 255, message = "链接长度不能超过255")
    private String url;
    /**
     * 驱动
     */
    @ApiModelProperty(value = "驱动")
    @NotEmpty(message = "驱动不能为空")
    @Size(max = 255, message = "驱动长度不能超过255")
    private String driverClassName;

}

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

import java.io.Serializable;

import com.dwi.saas.tenant.domain.enumeration.TenantConnectTypeEnum;




/**
 * <p>
 * 实体类
 * 数据源
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
@ApiModel(value = "DatasourceConfigPageQuery", description = "数据源")
public class DatasourceConfigPageQuery implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    private String name;  
    /**
     * 服务
     */
    @ApiModelProperty(value = "服务")
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
    private String username;
    /**
     * 密码
     */
    @ApiModelProperty(value = "密码")
    private String password;
    /**
     * 链接
     */
    @ApiModelProperty(value = "链接")
    private String url;
    /**
     * 驱动
     */
    @ApiModelProperty(value = "驱动")
    private String driverClassName;

}

package com.dwi.saas.authority.api.domain;


import com.dwi.basic.base.entity.Entity;
import com.dwi.saas.authority.api.domain.enumeration.ApplicationAppTypeEnum;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotEmpty;
import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 应用
 * </p>
 *
 * @author dwi
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Application", description = "应用")
@AllArgsConstructor
public class Application extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 客户端ID
     */
    private String clientId;

    /**
     * 客户端密码
     */
    private String clientSecret;

    /**
     * 官网
     */
    private String website;

    /**
     * 应用名称
     */
    private String name;

    /**
     * 应用图标
     */
    private String icon;

    /**
     * 类型
     * #{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}
     */
    private ApplicationAppTypeEnum appType;

    /**
     * 备注
     */
    private String describe;

    /**
     * 状态
     */
    private Boolean state;


    @Builder
    public Application(Long id, Long createdBy, LocalDateTime createTime, Long updatedBy, LocalDateTime updateTime,
                       String clientId, String clientSecret, String website, String name, String icon,
                       ApplicationAppTypeEnum appType, String describe, Boolean state) {
        this.id = id;
        this.createdBy = createdBy;
        this.createTime = createTime;
        this.updatedBy = updatedBy;
        this.updateTime = updateTime;
        this.clientId = clientId;
        this.clientSecret = clientSecret;
        this.website = website;
        this.name = name;
        this.icon = icon;
        this.appType = appType;
        this.describe = describe;
        this.state = state;
    }

}

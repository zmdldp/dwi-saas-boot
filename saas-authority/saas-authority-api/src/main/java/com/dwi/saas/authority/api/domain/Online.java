//package com.dwi.saas.authority.api.domain;
//
//
//import io.swagger.annotations.ApiModel;
//import io.swagger.annotations.ApiModelProperty;
//import lombok.AllArgsConstructor;
//import lombok.Builder;
//import lombok.Data;
//import lombok.EqualsAndHashCode;
//import lombok.NoArgsConstructor;
//import lombok.ToString;
//import lombok.experimental.Accessors;
//import org.hibernate.validator.constraints.Length;
//
//import java.io.Serializable;
//import java.time.LocalDateTime;
//
//import static com.dwi.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;
//
///**
// * <p>
// * 实体类
// * 在线用户
// * </p>
// *
// * @author dwi
// * @since 2020-11-20
// */
//@Data
//@NoArgsConstructor
//@ToString(callSuper = true)
//@EqualsAndHashCode
//@Accessors(chain = true)
//@ApiModel(value = "Online", description = "在线用户")
//@AllArgsConstructor
//@Builder
//public class Online implements Serializable {
//
//    private static final long serialVersionUID = 1L;
//
//    /**
//     * 用户id
//     */
//    private Long userId;
//
//    /**
//     * 登录IP
//     */
//    private String loginIp;
//
//    /**
//     * 登录地点
//     */
//    private String location;
//
//    /**
//     * 客户端Key
//     */
//    private String clientId;
//
//    /**
//     * token
//     */
//    private String token;
//
//    /**
//     * 姓名
//     */
//    private String name;
//
//    /**
//     * 过期时间
//     */
//    private LocalDateTime expireTime;
//
//    /**
//     * 登陆时间
//     */
//    private LocalDateTime loginTime;
//
//    /**
//     * 账号
//     */
//    private String account;
//
//    /**
//     * 有效期
//     */
//    private Long expireMillis;
//
//
//}

//package com.dwi.saas.authority.api.domain;
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
//
//import javax.validation.constraints.NotEmpty;
//
///**
// * 登录参数
// *
// * @author dwi
// * @date 2020年01月05日22:18:12
// */
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@Accessors(chain = true)
//@ToString(callSuper = true)
//@EqualsAndHashCode(callSuper = false)
//@Builder
//@ApiModel(value = "LoginParamDTO", description = "登录参数")
//public class LoginParamDTO {
//    
//	/**
//     * 验证码KEY
//     */  
//    private String key;
//    
//    /**
//     * 验证码
//     */
//    private String code;
//
////    @ApiModelProperty(value = "企业编号", example = "MDAwMA==")
////    private String tenant;
//
//    private String account;
//
//    private String password;
//
//    /**
//     * password: 账号密码
//     * refresh_token: 刷新token
//     * captcha: 验证码
//     */
//    private String grantType;
//
//    /**
//     * 前端界面点击清空缓存时调用
//     */
//    private String refreshToken;
//}

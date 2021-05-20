package com.dwi.saas.oauth.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dwi.basic.annotation.base.IgnoreResponseBodyAdvice;
import com.dwi.basic.base.R;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.saas.oauth.domain.LoginParamDTO;
import com.dwi.saas.oauth.granter.TokenGranterBuilder;
import com.dwi.saas.oauth.service.OnlineService;
import com.dwi.saas.oauth.service.ValidateCodeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 认证Controller
 *
 * @author dwi
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping
@AllArgsConstructor
@Api(value = "用户授权认证", tags = "登录接口")
public class OauthController {

    private final ValidateCodeService validateCodeService;
    private final TokenGranterBuilder tokenGranterBuilder;
    private final TokenUtil tokenUtil;
    private final OnlineService onlineService;

    /**
     * 租户登录 saas-ui 系统
     */
    @ApiOperation(value = "登录接口", notes = "登录或者清空缓存时调用")
    @PostMapping(value = "/noToken/login")
    public R<AuthInfo> login(@Validated @RequestBody LoginParamDTO login) throws BizException {
        return tokenGranterBuilder.getGranter(login.getGrantType()).grant(login);
    }

    @ApiOperation(value = "退出", notes = "退出")
    @PostMapping(value = "/noToken/logout")
    public R<Boolean> logout(String token, Long userId, String clientId) {
        return R.success(onlineService.clear(token, userId, clientId));
    }

    /**
     * 验证验证码
     *
     * @param key  验证码唯一uuid key
     * @param code 验证码
     */
    @ApiOperation(value = "验证验证码", notes = "验证验证码")
    @GetMapping(value = "/anno/check")
    public R<Boolean> check(@RequestParam(value = "key") String key, @RequestParam(value = "code") String code) throws BizException {
        return this.validateCodeService.check(key, code);
    }

    @ApiOperation(value = "验证码", notes = "验证码")
    @GetMapping(value = "/anno/captcha", produces = "image/png")
    @IgnoreResponseBodyAdvice
    public void captcha(@RequestParam(value = "key") String key, HttpServletResponse response) throws IOException {
        this.validateCodeService.create(key, response);
    }

    /**
     * 验证token
     */
    @ApiOperation(value = "验证token", notes = "验证token")
    @GetMapping(value = "/anno/verify")
    public R<AuthInfo> verify(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.getAuthInfo(token));
    }


}

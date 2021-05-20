package com.dwi.saas.example.controller.jwt;

import com.dwi.basic.base.R;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.basic.jwt.model.JwtUserInfo;
//import com.dwi.saas.authority.domain.dto.auth.LoginParamDTO;
import com.dwi.saas.example.domain.dto.LoginParamDTO;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * 认证Controller
 *
 * @author dwi
 * @date 2020年03月31日10:10:36
 */
@Slf4j
@RestController
@RequestMapping("/jwt")
@Api(value = "jwt", tags = "jwt")
@RequiredArgsConstructor
public class JwtTestController {

    private final TokenUtil tokenUtil;

    @PostMapping(value = "/createAuthInfo")
    public R<AuthInfo> createAuthInfo(@Validated @RequestBody LoginParamDTO login) throws BizException {
//        String tenant = JwtUtil.base64Decoder(login.getTenant());
//        log.info("tenant={}", tenant);

        JwtUserInfo userInfo = new JwtUserInfo(1234L, login.getAccount(), "张三");

        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        return R.success(authInfo);
    }

    @GetMapping(value = "/getAuthInfo")
    public R<AuthInfo> getAuthInfo(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.getAuthInfo(token));
    }

    @GetMapping(value = "/parseRefreshToken")
    public R<AuthInfo> parseRefreshToken(@RequestParam(value = "token") String token) throws BizException {
        return R.success(tokenUtil.parseRefreshToken(token));
    }

}

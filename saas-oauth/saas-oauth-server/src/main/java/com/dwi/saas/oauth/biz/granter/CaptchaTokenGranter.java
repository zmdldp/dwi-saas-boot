package com.dwi.saas.oauth.biz.granter;

import com.dwi.basic.base.R;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.authority.api.ApplicationBizApi;
//import com.dwi.saas.authority.api.OnlineBizApi;
import com.dwi.saas.authority.api.UserBizApi;
//import com.dwi.saas.authority.api.domain.LoginParamDTO;
//import com.dwi.saas.authority.biz.service.auth.ApplicationService;
//import com.dwi.saas.authority.biz.service.auth.OnlineService;
//import com.dwi.saas.authority.biz.service.auth.UserService;
//import com.dwi.saas.authority.domain.dto.auth.LoginParamDTO;
import com.dwi.saas.oauth.biz.event.LoginEvent;
import com.dwi.saas.oauth.biz.event.model.LoginStatusDTO;
import com.dwi.saas.oauth.biz.service.OnlineService;
import com.dwi.saas.oauth.biz.service.ValidateCodeService;
import com.dwi.saas.oauth.domain.LoginParamDTO;
import com.dwi.saas.tenant.TenantApi;

import lombok.extern.slf4j.Slf4j;

import static com.dwi.saas.oauth.biz.granter.CaptchaTokenGranter.GRANT_TYPE;

import org.springframework.stereotype.Component;

/**
 * 验证码TokenGranter
 *
 * @author dwi
 */
@Component(GRANT_TYPE)
@Slf4j
public class CaptchaTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "captcha";
    private final ValidateCodeService validateCodeService;

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserBizApi userBizApi,
    						   TenantApi tenantApi, ApplicationBizApi applicationBizApi,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService) {
        super(tokenUtil, userBizApi, tenantApi, applicationBizApi, databaseProperties, onlineService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (!check.getIsSuccess()) {
            String msg = check.getMsg();
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}

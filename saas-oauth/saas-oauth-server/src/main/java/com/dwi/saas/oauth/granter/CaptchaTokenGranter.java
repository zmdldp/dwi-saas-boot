package com.dwi.saas.oauth.granter;

import com.dwi.basic.base.R;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.exception.BizException;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.authority.ApplicationApi;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.oauth.domain.LoginParamDTO;
import com.dwi.saas.oauth.event.LoginEvent;
import com.dwi.saas.oauth.event.model.LoginStatusDTO;
import com.dwi.saas.oauth.service.OnlineService;
import com.dwi.saas.oauth.service.ValidateCodeService;
import com.dwi.saas.tenant.TenantApi;

import lombok.extern.slf4j.Slf4j;

import static com.dwi.saas.oauth.granter.CaptchaTokenGranter.GRANT_TYPE;

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

    public CaptchaTokenGranter(TokenUtil tokenUtil, UserApi userApi,
    						   TenantApi tenantApi, ApplicationApi applicationApi,
                               DatabaseProperties databaseProperties, ValidateCodeService validateCodeService,
                               OnlineService onlineService) {
        super(tokenUtil, userApi, tenantApi, applicationApi, databaseProperties, onlineService);
        this.validateCodeService = validateCodeService;
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO loginParam) {
        R<Boolean> check = validateCodeService.check(loginParam.getKey(), loginParam.getCode());
        if (!check.isSuccess()) {
            String msg = check.getMsg();
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(loginParam.getAccount(), msg)));
            throw BizException.validFail(check.getMsg());
        }

        return login(loginParam);
    }

}

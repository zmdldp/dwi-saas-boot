/*
 * Copyright 2002-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.dwi.saas.oauth.granter;

import com.dwi.basic.base.R;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.saas.authority.ApplicationApi;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.oauth.domain.LoginParamDTO;
import com.dwi.saas.oauth.service.OnlineService;
import com.dwi.saas.oauth.service.ValidateCodeService;
import com.dwi.saas.tenant.TenantApi;

import static com.dwi.saas.oauth.granter.PasswordTokenGranter.GRANT_TYPE;

import org.springframework.stereotype.Component;

/**
 * 账号密码登录获取token
 *
 * @author Dave Syer
 * @author dwi
 * @date 2020年03月31日10:22:55
 */
@Component(GRANT_TYPE)
public class PasswordTokenGranter extends AbstractTokenGranter implements TokenGranter {

    public static final String GRANT_TYPE = "password";

    public PasswordTokenGranter(TokenUtil tokenUtil, UserApi userApi,
			   TenantApi tenantApi, ApplicationApi applicationApi,
               DatabaseProperties databaseProperties, OnlineService onlineService) {
    	super(tokenUtil, userApi, tenantApi, applicationApi, databaseProperties, onlineService);
    }

    @Override
    public R<AuthInfo> grant(LoginParamDTO tokenParameter) {
        return login(tokenParameter);
    }
}

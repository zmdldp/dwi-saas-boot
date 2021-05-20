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
import com.dwi.basic.jwt.model.AuthInfo;
//import com.dwi.saas.authority.domain.dto.auth.LoginParamDTO;
//import com.dwi.saas.authority.api.domain.LoginParamDTO;
import com.dwi.saas.oauth.domain.LoginParamDTO;

/**
 * 授予token接口
 *
 * @author Dave Syer
 * @author dwi
 * @date 2020年03月31日10:21:21
 */
public interface TokenGranter {

    /**
     * 获取用户信息
     *
     * @param loginParam 授权参数
     * @return LoginDTO
     */
    R<AuthInfo> grant(LoginParamDTO loginParam);

}

/*
 * Copyright 2006-2011 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.dwi.saas.oauth.granter;

import static com.dwi.basic.context.ContextConstants.BASIC_HEADER_KEY;
import static com.dwi.basic.utils.BizAssert.gt;
import static com.dwi.basic.utils.BizAssert.notNull;

import java.time.LocalDateTime;

import com.dwi.basic.base.R;
import com.dwi.basic.boot.utils.WebUtils;
import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.database.properties.MultiTenantType;
import com.dwi.basic.exception.code.ExceptionCode;
import com.dwi.basic.jwt.TokenUtil;
import com.dwi.basic.jwt.model.AuthInfo;
import com.dwi.basic.jwt.model.JwtUserInfo;
import com.dwi.basic.jwt.utils.JwtUtil;
import com.dwi.basic.utils.BeanPlusUtil;
import com.dwi.basic.utils.BizAssert;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.basic.utils.StrHelper;
import com.dwi.basic.utils.StrPool;
import com.dwi.saas.authority.ApplicationApi;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.authority.domain.entity.auth.Application;
import com.dwi.saas.authority.domain.entity.auth.User;
import com.dwi.saas.oauth.domain.LoginParamDTO;
import com.dwi.saas.oauth.domain.Online;
import com.dwi.saas.oauth.event.LoginEvent;
import com.dwi.saas.oauth.event.model.LoginStatusDTO;
import com.dwi.saas.oauth.service.OnlineService;
import com.dwi.saas.oauth.utils.TimeUtils;
import com.dwi.saas.tenant.TenantApi;
import com.dwi.saas.tenant.domain.entity.Tenant;
//import com.dwi.saas.tenant.init.base.enumeration.TenantStatusEnum;
import com.dwi.saas.tenant.domain.enumeration.TenantStatusEnum;

import cn.hutool.core.convert.Convert;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.SecureUtil;
import cn.hutool.extra.servlet.ServletUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * 验证码TokenGranter
 *
 * @author dwi
 */
@Slf4j
@RequiredArgsConstructor
public abstract class AbstractTokenGranter implements TokenGranter {
	
    // TODO 改造为Feign调用
//    protected final UserService userService;
//    protected final TenantService tenantService;
//    protected final ApplicationService applicationService;
     
    protected final TokenUtil tokenUtil;
    
    protected final UserApi userApi;
    
    protected final TenantApi tenantApi;
    
    protected final ApplicationApi applicationApi;
    
    protected final DatabaseProperties databaseProperties;
    
//    protected final OnlineApi onlineApi;  
    protected final OnlineService onlineService;
 

    /**
     * 处理登录逻辑
     *
     * @param loginParam 登录参数
     * @return 认证信息
     */
    protected R<AuthInfo> login(LoginParamDTO loginParam) {
        if (StrHelper.isAnyBlank(loginParam.getAccount(), loginParam.getPassword())) {
            return R.fail("请输入用户名或密码");
        }
        // 1，检测租户是否可用
        if (!MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType())) {
        	Tenant tenant = null;
        	R<Tenant> result = tenantApi.getByCode(ContextUtil.getTenant());
        	if(result!=null && result.isSuccess()) {
        		tenant = result.getData();
        	}
            notNull(tenant, "企业不存在");
            BizAssert.equals(TenantStatusEnum.NORMAL, tenant.getStatus(), "企业不可用~");
            if (tenant.getExpirationTime() != null) {
                gt(LocalDateTime.now(), tenant.getExpirationTime(), "企业服务已到期~");
            }
        }

        // 2.检测client是否可用
        R<String[]> checkClient = checkClient();
        if (!checkClient.isSuccess()) {
            return R.fail(checkClient.getMsg());
        }

        // 3. 验证登录
        R<User> result = this.getUser(loginParam.getAccount(), loginParam.getPassword());
        if (!result.isSuccess()) {
            return R.fail(result.getCode(), result.getMsg());
        }

        // 4.生成 token
        User user = result.getData();
        AuthInfo authInfo = this.createToken(user);

        Online online = getOnline(checkClient.getData()[0], authInfo);

        //成功登录事件
        LoginStatusDTO loginStatus = LoginStatusDTO.success(user.getId(), online);
        SpringUtils.publishEvent(new LoginEvent(loginStatus));

        onlineService.save(online);
        return R.success(authInfo);
    }

    protected Online getOnline(String clientId, AuthInfo authInfo) {
        Online online = new Online();
        BeanPlusUtil.copyProperties(authInfo, online);
        online.setClientId(clientId);
        online.setExpireTime(authInfo.getExpiration());
        online.setLoginTime(LocalDateTime.now());
        return online;
    }


    /**
     * 检测 client
     */
    protected R<String[]> checkClient() {
        String basicHeader = ServletUtil.getHeader(WebUtils.request(), BASIC_HEADER_KEY, StrPool.UTF_8);
        String[] client = JwtUtil.getClient(basicHeader);
        Application application = null;
        R<Application> result = applicationApi.getApplicationByClient(client[0], client[1]);
        if(result.isSuccess()) {
        	application = result.getData();
        }

        if (application == null) {
            return R.fail("请填写正确的客户端ID或者客户端秘钥");
        }
        if (!application.getState()) {
            return R.fail("客户端[%s]已被禁用", application.getClientId());
        }
        return R.success(client);
    }


    /**
     * 检测用户密码是否正确
     *
     * @param account  账号
     * @param password 密码
     * @return 用户信息
     */
    protected R<User> getUser(String account, String password) {
        //User user = this.userService.getByAccount(account);
    	R<User> result = userApi.getByAccount(account);
    	User user = null;
    	if (result.isSuccess()) {
    		user = result.getData();
        }
        // 密码错误
        if (user == null) {
            return R.fail(ExceptionCode.JWT_USER_INVALID);
        }

        String passwordMd5 = SecureUtil.sha256(password + user.getSalt());
        if (!passwordMd5.equalsIgnoreCase(user.getPassword())) {
            String msg = "用户名或密码错误!";
            // 密码错误事件
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.pwdError(user.getId(), msg)));
            return R.fail(msg);
        }

        // 密码过期
        if (user.getPasswordExpireTime() != null && LocalDateTime.now().isAfter(user.getPasswordExpireTime())) {
            String msg = "用户密码已过期，请修改密码或者联系管理员重置!";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        if (!user.getState()) {
            String msg = "用户被禁用，请联系管理员！";
            SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
            return R.fail(msg);
        }

        //TODO 用户锁定
        Integer maxPasswordErrorNum = 0;
        Integer passwordErrorNum = Convert.toInt(user.getPasswordErrorNum(), 0);
        if (maxPasswordErrorNum > 0 && passwordErrorNum > maxPasswordErrorNum) {
            log.info("当前错误次数{}, 最大次数:{}", passwordErrorNum, maxPasswordErrorNum);

            LocalDateTime passwordErrorLockTime = TimeUtils.getPasswordErrorLockTime("0");
            log.info("passwordErrorLockTime={}", passwordErrorLockTime);
            if (passwordErrorLockTime.isAfter(user.getPasswordErrorLastTime())) {
                // 登录失败事件
                String msg = StrUtil.format("密码连续输错次数已达到{}次,用户已被锁定~", maxPasswordErrorNum);
                SpringUtils.publishEvent(new LoginEvent(LoginStatusDTO.fail(user.getId(), msg)));
                return R.fail(msg);
            }
        }

        return R.success(user);
    }

    /**
     * 创建用户TOKEN
     *
     * @param user 用户
     * @return token
     */
    protected AuthInfo createToken(User user) {
        JwtUserInfo userInfo = new JwtUserInfo(user.getId(), user.getAccount(), user.getName());
        AuthInfo authInfo = tokenUtil.createAuthInfo(userInfo, null);
        authInfo.setAvatar(user.getAvatar());
        authInfo.setWorkDescribe(user.getWorkDescribe());
        return authInfo;
    }


}

package com.dwi.saas.oauth.event.listener;

import cn.hutool.core.date.DatePattern;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.database.properties.MultiTenantType;
import com.dwi.saas.authority.LoginLogApi;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.authority.domain.entity.common.LoginLog;
import com.dwi.saas.oauth.event.LoginEvent;
import com.dwi.saas.oauth.event.model.LoginStatusDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.Date;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 登录事件监听，用于记录登录日志
 *
 * @author dwi
 * @date 2020年03月18日17:39:59
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class LoginListener {
    private final LoginLogApi loginLogApi;
    private final UserApi userApi;
    private final DatabaseProperties databaseProperties;

    @Async
    @EventListener({LoginEvent.class})
    public void saveSysLog(LoginEvent event) {
        LoginStatusDTO loginStatus = (LoginStatusDTO) event.getSource();

        if (!MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType()) && StrUtil.isEmpty(loginStatus.getTenant())) {
            log.warn("忽略记录登录日志:{}", loginStatus);
            return;
        }

        ContextUtil.setTenant(loginStatus.getTenant());
        if (LoginStatusDTO.Type.SUCCESS == loginStatus.getType()) {
            // 重置错误次数 和 最后登录时间
            this.userApi.resetPassErrorNum(loginStatus.getId());


        } else if (LoginStatusDTO.Type.PWD_ERROR == loginStatus.getType()) {
            // 密码错误
            this.userApi.incrPasswordErrorNumById(loginStatus.getId());
        }
        loginLogApi.save(
        		LoginLog.builder().userId(loginStatus.getId())
        		.account(loginStatus.getOnline().getAccount())
        		.userName(loginStatus.getOnline().getName())
        		.loginDate(DateUtil.format(new Date(), DatePattern.NORM_DATE_PATTERN))
        		.ua(loginStatus.getUa()).browser(loginStatus.getBrowser()).browserVersion(loginStatus.getBrowserVersion())
        		.operatingSystem(loginStatus.getOperatingSystem())
        		.requestIp(loginStatus.getIp()).location(loginStatus.getLocation()).description(loginStatus.getDescription()).build());
    }

}

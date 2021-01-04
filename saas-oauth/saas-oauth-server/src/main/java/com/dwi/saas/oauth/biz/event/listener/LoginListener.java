package com.dwi.saas.oauth.biz.event.listener;

import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import com.dwi.basic.context.ContextUtil;
//import com.dwi.saas.authority.biz.service.auth.UserService;
//import com.dwi.saas.authority.biz.service.common.LoginLogService;
import com.dwi.saas.oauth.biz.event.LoginEvent;
import com.dwi.saas.oauth.biz.event.model.LoginStatusDTO;

import cn.hutool.core.util.StrUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

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
    //TODO 改造为Feign调用
    //    private final LoginLogService loginLogService;
    //    private final UserService userService;

    @Async
    @EventListener({LoginEvent.class})
    public void saveSysLog(LoginEvent event) {
        LoginStatusDTO loginStatus = (LoginStatusDTO) event.getSource();

        if (StrUtil.isEmpty(loginStatus.getTenant())) {
            log.warn("忽略记录登录日志:{}", loginStatus);
            return;
        }

        //TODO
        ContextUtil.setTenant(loginStatus.getTenant());
        if (LoginStatusDTO.Type.SUCCESS == loginStatus.getType()) {
            // 重置错误次数 和 最后登录时间
            //TODO 改造为Feign调用
            // this.userService.resetPassErrorNum(loginStatus.getId());


        } else if (LoginStatusDTO.Type.PWD_ERROR == loginStatus.getType()) {
            // 密码错误
            //TODO 改造为Feign调用
            // this.userService.incrPasswordErrorNumById(loginStatus.getId());
        }
        //TODO 改造为Feign调用
        //loginLogService.save(loginStatus.getId(), loginStatus.getAccount(), loginStatus.getUa(), loginStatus.getIp(), loginStatus.getLocation(), loginStatus.getDescription());
    }

}

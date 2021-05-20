package com.dwi.saas.authority.event.listener;

import com.dwi.basic.context.ContextUtil;
import com.dwi.saas.authority.event.ParameterUpdateEvent;
import com.dwi.saas.authority.event.model.ParameterUpdate;
//import com.dwi.saas.authority.biz.service.auth.OnlineService;
import com.dwi.saas.common.constant.ParameterKey;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 参数修改事件监听，用于调整具体的业务
 *
 * @author dwi
 * @date 2020年03月18日17:39:59
 */
@Component
@Slf4j
@RequiredArgsConstructor
public class ParameterUpdateListener {

    //private final OnlineService onlineService;

    @Async
    @EventListener({ParameterUpdateEvent.class})
    public void saveSysLog(ParameterUpdateEvent event) {
        ParameterUpdate source = (ParameterUpdate) event.getSource();

        ContextUtil.setTenant(source.getTenant());
        if (ParameterKey.LOGIN_POLICY.equals(source.getKey())) {
           // onlineService.reset();
        }
    }
}

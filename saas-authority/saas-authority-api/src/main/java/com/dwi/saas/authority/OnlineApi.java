package com.dwi.saas.authority;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.domain.dto.auth.Online;

/**
 * 用户
 *
 * @author dwi
 * @date 2020/12/16
 */
public interface OnlineApi {

    /**
     * 保存在线用户信息 ADD 2020-12-16
     * 
     * @param model
     * @return
     */
    @PutMapping()
    public R<Boolean> saveOnlineInfo(@RequestBody Online online);
}

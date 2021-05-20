package com.dwi.saas.authority;


import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.R;
import com.dwi.saas.authority.domain.entity.common.LoginLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志保存 API
 *
 * @author dwi
 * @date 2020/07/02
 */
public interface LoginLogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @PostMapping
    R<LoginLog> save(@RequestBody LoginLog loginLog);

}

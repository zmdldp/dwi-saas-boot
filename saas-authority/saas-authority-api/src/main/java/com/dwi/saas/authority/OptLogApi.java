package com.dwi.saas.authority;


import com.dwi.basic.base.R;
import com.dwi.basic.log.entity.OptLogDTO;
import com.dwi.saas.authority.domain.entity.common.OptLog;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * 操作日志保存 API
 *
 * @author dwi
 * @date 2020/07/02
 */
public interface OptLogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @PostMapping
    R<OptLog> save(@RequestBody OptLogDTO log);

}

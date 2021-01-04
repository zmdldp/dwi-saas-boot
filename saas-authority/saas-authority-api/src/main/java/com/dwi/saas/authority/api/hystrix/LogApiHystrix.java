package com.dwi.saas.authority.api.hystrix;


//import com.dwi.saas.oauth.api.LogApi;
import com.dwi.basic.base.R;
import com.dwi.basic.log.entity.OptLogDTO;
import com.dwi.saas.authority.api.LogApi;

import org.springframework.stereotype.Component;

/**
 * 日志操作 熔断
 *
 * @author dwi
 * @date 2019/07/02
 */
@Component
public class LogApiHystrix implements LogApi {
    @Override
    public R<OptLogDTO> save(OptLogDTO log) {
        return R.timeout();
    }
}

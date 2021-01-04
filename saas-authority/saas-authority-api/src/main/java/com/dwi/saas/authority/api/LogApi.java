package com.dwi.saas.authority.api;


import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dwi.basic.base.R;
import com.dwi.basic.log.entity.OptLogDTO;

/**
 * 操作日志保存 API
 *
 * @author dwi
 * @date 2019/07/02
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = LogApiHystrix.class, qualifier = "logApi")
//@RequestMapping("/dictionary")
public interface LogApi {

    /**
     * 保存日志
     *
     * @param log 操作日志
     * @return 操作日志
     */
    @RequestMapping(value = "/optLog", method = RequestMethod.POST)
    R<OptLogDTO> save(@RequestBody OptLogDTO log);

}

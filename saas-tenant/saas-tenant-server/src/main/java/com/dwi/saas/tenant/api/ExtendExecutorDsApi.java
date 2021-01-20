package com.dwi.saas.tenant.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author dwi
 * @date 2021/1/4 10:45 下午
 */
@FeignClient(name = "ExtendExecutorDsApi", url = "${saas.feign.executor-server:http://127.0.0.1:8774}", path = "/saas-extend-executor/ds")
public interface ExtendExecutorDsApi extends DsApi {

}

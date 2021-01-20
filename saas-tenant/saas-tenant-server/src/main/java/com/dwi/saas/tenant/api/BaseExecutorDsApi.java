package com.dwi.saas.tenant.api;

import org.springframework.cloud.openfeign.FeignClient;

/**
 * @author dwi
 * @date 2021/1/4 10:45 下午
 */
@FeignClient(name = "BaseExecutorDsApi", url = "${saas.feign.executor-server:http://127.0.0.1:8776}", path = "/saas-base-executor/ds")
public interface BaseExecutorDsApi extends DsApi {

}

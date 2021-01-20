package com.dwi.saas.tenant.api;

import com.dwi.basic.base.R;
import com.dwi.saas.tenant.domain.dto.DataSourcePropertyDTO;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 网关服务初始化数据源
 * <p>
 * 若你使用的是zuul:
 * 1. 将下面的 saas-gateway-server 改成 saas-zuul-server ！！！！
 * 2. 将path 改成 /api/gate/ds
 *
 * @author dwi
 * @date 2020年04月05日18:18:26
 */
//@FeignClient(name = "${saas.feign.gateway-server:saas-zuul-server}", path = "/api/gate/ds")
@FeignClient(name = "${saas.feign.gateway-server:saas-gateway-server}", path = "/ds")
public interface GatewayDsApi {

    /**
     * 初始化数据源
     *
     * @param tenantConnect
     * @return
     */
    @RequestMapping(value = "/initConnect", method = RequestMethod.POST)
    R<Boolean> initConnect(@RequestBody DataSourcePropertyDTO tenantConnect);

    /**
     * 删除数据源
     *
     * @param tenant
     * @return
     */
    @RequestMapping(value = "/remove", method = RequestMethod.GET)
    R<Boolean> remove(@RequestParam(value = "tenant") String tenant);
}

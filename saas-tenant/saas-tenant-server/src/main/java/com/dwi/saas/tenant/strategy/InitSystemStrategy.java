package com.dwi.saas.tenant.strategy;

import java.util.List;

import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO.ConnectConfig;

/**
 * 初始化系统
 * <p>
 *
 * @author dwi
 * @date 2020/10/25
 */
public interface InitSystemStrategy {

    /**
     * 初始化 服务链接
     *
     * @param tenantId 租户表Id
     * @param connectConfig 链接信息
     * @return 是否成功
     */
    boolean initConnect(Long tenantId, ConnectConfig connectConfig);

    /**
     * 重置系统
     *
     * @param tenant 租户编码
     * @return 是否成功
     */
    boolean reset(String tenant);

    /**
     * 删除租户数据
     *
     * @param ids            租户id
     * @param tenantCodeList 租户编码
     * @return 是否成功
     */
    boolean delete(List<Long> ids, List<String> tenantCodeList);
}

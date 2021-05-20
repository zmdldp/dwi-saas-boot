package com.dwi.saas.tenant.service;

import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.saas.tenant.domain.dto.TenantSaveDTO;
import com.dwi.saas.tenant.domain.entity.Tenant;
import com.dwi.saas.tenant.domain.dto.TenantConnectDTO;
import com.dwi.saas.tenant.domain.enumeration.TenantStatusEnum;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 企业
 * </p>
 *
 * @author dwi
 * @date 2020-10-24
 */
public interface TenantDatasourceService {

    /**
     * 通知所有服务链接数据源
     *
     * @param tenantConnect 链接信息
     * @return 是否链接成功
     */
    Boolean connect(TenantConnectDTO tenantConnect);

    /**
     * 删除租户和基础数据
     *
     * @param ids id
     * @return 是否成功
     */
    Boolean deleteAll(List<Long> ids);
}

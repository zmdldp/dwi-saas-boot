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
public interface TenantService extends SuperCacheService<Tenant> {
    /**
     * 检测 租户编码是否存在
     *
     * @param tenantCode 租户编码
     * @return 是否存在
     */
    boolean check(String tenantCode);

    /**
     * 保存
     *
     * @param data 租户保存数据
     * @return 租户
     */
    Tenant save(TenantSaveDTO data);

    /**
     * 根据编码获取
     *
     * @param tenant 租户编码
     * @return 租户
     */
    Tenant getByCode(String tenant);

    /**
     * 删除租户数据
     *
     * @param ids id
     * @return 是否成功
     */
    Boolean delete(List<Long> ids);


    /**
     * 查询所有可用的租户
     *
     * @return 租户信息
     */
    List<Tenant> find();

    /**
     * 修改租户状态
     *
     * @param ids
     * @param status
     * @return
     */
    Boolean updateStatus(List<Long> ids, TenantStatusEnum status);
}

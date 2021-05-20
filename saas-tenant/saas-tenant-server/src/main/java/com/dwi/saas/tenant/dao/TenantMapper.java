package com.dwi.saas.tenant.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.tenant.domain.entity.Tenant;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 企业
 * </p>
 *
 * @author dwi
 * @date 2020-10-25
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface TenantMapper extends SuperMapper<Tenant> {

}

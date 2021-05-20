package com.dwi.saas.authority.service.common;

import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.saas.authority.domain.entity.common.Area;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 地区表
 * </p>
 *
 * @author dwi
 * @date 2020-07-02
 */
public interface AreaService extends SuperCacheService<Area> {

    /**
     * 递归删除
     *
     * @param ids 地区id
     * @return 是否成功
     */
    boolean recursively(List<Long> ids);
}

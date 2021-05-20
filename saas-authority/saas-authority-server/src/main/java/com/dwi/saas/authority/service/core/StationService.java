package com.dwi.saas.authority.service.core;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.request.PageParams;
import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.basic.model.LoadService;
import com.dwi.saas.authority.domain.dto.core.StationPageQuery;
import com.dwi.saas.authority.domain.entity.core.Station;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务接口
 * 岗位
 * </p>
 *
 * @author dwi
 * @date 2020-07-22
 */
public interface StationService extends SuperCacheService<Station>, LoadService {
    /**
     * 按权限查询岗位的分页信息
     *
     * @param page   分页对象
     * @param params 分页参数
     * @return 分页数据
     */
    IPage<Station> findStationPage(IPage<Station> page, PageParams<StationPageQuery> params);

}

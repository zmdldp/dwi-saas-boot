package com.dwi.saas.authority.dao.core;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.basic.database.mybatis.auth.DataScope;
import com.dwi.saas.authority.domain.entity.core.Station;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 岗位
 * </p>
 *
 * @author dwi
 * @date 2020-07-22
 */
@Repository
public interface StationMapper extends SuperMapper<Station> {
    /**
     * 分页查询岗位信息（含角色）
     *
     * @param page 分页
     * @param queryWrapper 参数包装器
     * @param dataScope 数据权限
     * @return 分页数据
     */
    IPage<Station> findStationPage(IPage<Station> page, @Param(Constants.WRAPPER) Wrapper<Station> queryWrapper, DataScope dataScope);
}

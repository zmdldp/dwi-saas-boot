package com.dwi.saas.authority.dao.auth;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.entity.auth.Menu;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 菜单
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Repository
public interface MenuMapper extends SuperMapper<Menu> {

    /**
     * 查询用户可用菜单
     *
     * @param userId 用户id
     * @return 菜单
     */
    List<Menu> findVisibleMenu(@Param("userId") Long userId);
}

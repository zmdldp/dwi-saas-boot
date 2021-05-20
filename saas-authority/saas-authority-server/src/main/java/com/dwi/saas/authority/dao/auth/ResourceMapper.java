package com.dwi.saas.authority.dao.auth;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.authority.domain.dto.auth.ResourceQueryDTO;
import com.dwi.saas.authority.domain.entity.auth.Resource;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 资源
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
@Repository
public interface ResourceMapper extends SuperMapper<Resource> {
    /**
     * 查询 拥有的资源
     *
     * @param resource 查询参数
     * @return 可用资源
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据唯一索引 保存或修改资源
     *
     * @param resource 资源
     * @return 操作条数
     */
    int saveOrUpdateUnique(Resource resource);

    /**
     * 根据资源id查询菜单id
     *
     * @param resourceIdList 资源id
     * @return 菜单id
     */
    List<Long> findMenuIdByResourceId(@Param("resourceIdList") List<Long> resourceIdList);
}

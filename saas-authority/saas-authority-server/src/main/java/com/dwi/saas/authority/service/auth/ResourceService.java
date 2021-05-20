package com.dwi.saas.authority.service.auth;

import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.saas.authority.domain.dto.auth.ResourceQueryDTO;
import com.dwi.saas.authority.domain.entity.auth.Resource;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 资源
 * </p>
 *
 * @author dwi
 * @date 2020-07-03
 */
public interface ResourceService extends SuperCacheService<Resource> {

    /**
     * 查询 拥有的资源
     *
     * @param resource 查询条件
     * @return 资源
     */
    List<Resource> findVisibleResource(ResourceQueryDTO resource);

    /**
     * 根据ID删除
     *
     * @param ids id
     * @return 是否成功
     */
    boolean removeByIdWithCache(List<Long> ids);

    /**
     * 保存
     *
     * @param resource 资源
     * @return 是否成功
     */
    boolean saveWithCache(Resource resource);


    /**
     * 根据菜单id删除资源
     *
     * @param menuIds 菜单id
     */
    void removeByMenuIdWithCache(List<Long> menuIds);

    /**
     * 根据资源id 查询菜单id
     *
     * @param resourceIdList 资源id
     * @return 菜单id
     */
    List<Long> findMenuIdByResourceId(List<Long> resourceIdList);
}

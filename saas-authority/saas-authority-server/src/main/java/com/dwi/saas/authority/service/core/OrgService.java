package com.dwi.saas.authority.service.core;

import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.basic.model.LoadService;
import com.dwi.saas.authority.domain.entity.core.Org;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 组织
 * </p>
 *
 * @author dwi
 * @date 2019-07-22
 */
public interface OrgService extends SuperCacheService<Org>, LoadService {
    /**
     * 查询指定id集合下的所有子集
     *
     * @param ids id
     * @return 所有的子组织
     */
    List<Org> findChildren(List<Long> ids);

    /**
     * 批量删除以及删除其子节点
     *
     * @param ids id
     * @return 是否成功
     */
    boolean remove(List<Long> ids);

    /**
     * 检测名称是否存在
     *
     * @param id   id
     * @param name name
     * @return boolean
     */
    boolean check(Long id, String name);
}

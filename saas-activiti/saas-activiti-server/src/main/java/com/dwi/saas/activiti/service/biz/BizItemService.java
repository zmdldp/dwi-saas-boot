package com.dwi.saas.activiti.service.biz;

import com.dwi.basic.base.service.SuperService;
import com.dwi.saas.activiti.domain.dto.biz.BizItemResDTO;
import com.dwi.saas.activiti.domain.entity.biz.BizItem;

import java.util.List;

/**
 * <p>
 * 业务接口
 * <p>
 * </p>
 *
 * @author wz
 * @date 2020-08-19
 */
public interface BizItemService extends SuperService<BizItem> {

    /**
     * 保存实体
     *
     * @param po 实体
     * @return 是否成功
     */
    boolean saveItem(BizItem po);

    /**
     * 查询实体
     *
     * @param instId 实例id
     * @return 业务节点
     */
    List<BizItemResDTO> find(String instId);
}

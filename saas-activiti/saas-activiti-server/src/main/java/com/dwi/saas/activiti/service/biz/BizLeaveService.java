package com.dwi.saas.activiti.service.biz;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.request.PageParams;
import com.dwi.basic.base.service.SuperService;
import com.dwi.saas.activiti.domain.dto.biz.BizLeavePageDTO;
import com.dwi.saas.activiti.domain.dto.biz.BizLeaveResDTO;
import com.dwi.saas.activiti.domain.entity.biz.BizLeave;
import com.dwi.saas.activiti.domain.entity.core.UpdateCollEntity;

/**
 * <p>
 * 业务接口
 * 请假流程
 * </p>
 *
 * @author wz
 * @date 2020-08-12
 */
public interface BizLeaveService extends SuperService<BizLeave> {

    /**
     * 业务key
     *
     * @return 业务key
     */
    String getKey();

    /**
     * 保存业务实体
     *
     * @param bizLeave 业务实体
     * @return 是否成功
     */
    Boolean saveBiz(BizLeave bizLeave);

    /**
     * 删除业务实体
     *
     * @param entity 标识集体修改实体
     * @return 是否成功
     */
    Boolean deleteBiz(UpdateCollEntity<String> entity);

    /**
     * 分页查询业务实体
     *
     * @param params 分页入参
     * @return 分页参数
     */
    IPage<BizLeaveResDTO> pageBiz(PageParams<BizLeavePageDTO> params);

}

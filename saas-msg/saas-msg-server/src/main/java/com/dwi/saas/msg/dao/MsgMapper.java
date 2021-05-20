package com.dwi.saas.msg.dao;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.basic.base.request.PageParams;
import com.dwi.saas.msg.domain.dto.MsgPageResult;
import com.dwi.saas.msg.domain.dto.MsgQuery;
import com.dwi.saas.msg.domain.entity.Msg;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 消息中心
 * </p>
 *
 * @author dwi
 * @date 2020-08-01
 */
@Repository
public interface MsgMapper extends SuperMapper<Msg> {
    /**
     * 查询消息中心分页数据
     *
     * @param page  分页参数
     * @param param 消息参数
     * @return 分页数据
     */
    IPage<MsgPageResult> page(IPage<MsgPageResult> page, @Param("param") PageParams<MsgQuery> param);
}

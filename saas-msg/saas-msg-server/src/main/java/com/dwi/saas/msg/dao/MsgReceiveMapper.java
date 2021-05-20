package com.dwi.saas.msg.dao;

import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.saas.msg.domain.entity.MsgReceive;

import org.springframework.stereotype.Repository;

/**
 * <p>
 * Mapper 接口
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author dwi
 * @date 2020-08-01
 */
@Repository
public interface MsgReceiveMapper extends SuperMapper<MsgReceive> {

}

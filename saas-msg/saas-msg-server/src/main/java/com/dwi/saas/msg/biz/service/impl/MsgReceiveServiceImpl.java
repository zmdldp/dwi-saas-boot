package com.dwi.saas.msg.biz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.msg.biz.dao.MsgReceiveMapper;
import com.dwi.saas.msg.biz.service.MsgReceiveService;
import com.dwi.saas.msg.domain.entity.MsgReceive;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 消息中心 接收表
 * 全量数据
 * </p>
 *
 * @author dwi
 * @date 2019-08-01
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class MsgReceiveServiceImpl extends SuperServiceImpl<MsgReceiveMapper, MsgReceive> implements MsgReceiveService {

}

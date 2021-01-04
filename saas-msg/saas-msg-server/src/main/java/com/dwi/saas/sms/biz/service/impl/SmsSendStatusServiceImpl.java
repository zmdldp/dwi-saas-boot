package com.dwi.saas.sms.biz.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.sms.biz.dao.SmsSendStatusMapper;
import com.dwi.saas.sms.biz.service.SmsSendStatusService;
import com.dwi.saas.sms.domain.entity.SmsSendStatus;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 业务实现类
 * 短信发送状态
 * </p>
 *
 * @author dwi
 * @date 2019-08-01
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class SmsSendStatusServiceImpl extends SuperServiceImpl<SmsSendStatusMapper, SmsSendStatus> implements SmsSendStatusService {

}

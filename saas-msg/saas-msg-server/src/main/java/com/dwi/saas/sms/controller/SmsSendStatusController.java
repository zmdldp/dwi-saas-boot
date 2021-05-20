package com.dwi.saas.sms.controller;


import com.dwi.basic.base.controller.QueryController;
import com.dwi.basic.base.controller.SuperSimpleController;
import com.dwi.saas.sms.domain.entity.SmsSendStatus;
import com.dwi.saas.sms.service.SmsSendStatusService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 短信发送状态
 * </p>
 *
 * @author dwi
 * @date 2020-08-01
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/smsSendStatus")
@Api(value = "SmsSendStatus", tags = "短信发送状态")
public class SmsSendStatusController extends SuperSimpleController<SmsSendStatusService, SmsSendStatus>
        implements QueryController<SmsSendStatus, Long, SmsSendStatus> {

}

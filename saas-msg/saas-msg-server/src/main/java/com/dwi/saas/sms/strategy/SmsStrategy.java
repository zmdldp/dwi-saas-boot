package com.dwi.saas.sms.strategy;


import com.dwi.basic.base.R;
import com.dwi.saas.sms.domain.entity.SmsTask;
import com.dwi.saas.sms.domain.entity.SmsTemplate;

/**
 * 抽象策略类: 发送短信
 * <p>
 * 每个短信 服务商都有自己的 发送短信策略(sdk)
 *
 * @author dwi
 * @date 2020-05-15
 */
public interface SmsStrategy {
    /**
     * 发送短信
     *
     * @param task     短信任务
     * @param template 短信模版
     * @return 任务id
     */
    R<String> sendSms(SmsTask task, SmsTemplate template);
}

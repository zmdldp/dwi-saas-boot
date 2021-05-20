package com.dwi.saas.sms.service;

import com.dwi.basic.base.service.SuperService;
import com.dwi.saas.sms.domain.entity.SmsTemplate;

/**
 * <p>
 * 业务接口
 * 短信模板
 * </p>
 *
 * @author dwi
 * @date 2020-08-01
 */
public interface SmsTemplateService extends SuperService<SmsTemplate> {
    /**
     * 保存模板，并且将模板内容解析成json格式
     *
     * @param smsTemplate 短信模版
     */
    void saveTemplate(SmsTemplate smsTemplate);

    /**
     * 修改
     *
     * @param smsTemplate 短信模版
     */
    void updateTemplate(SmsTemplate smsTemplate);
}

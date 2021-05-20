package com.dwi.saas.sms.strategy;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.R;
import com.dwi.basic.utils.BizAssert;
import com.dwi.saas.sms.dao.SmsTaskMapper;
import com.dwi.saas.sms.dao.SmsTemplateMapper;
import com.dwi.saas.sms.domain.entity.SmsTask;
import com.dwi.saas.sms.domain.entity.SmsTemplate;

import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 短信发送上下文
 *
 * @author dwi
 * @date 2020-05-15
 */
@Component
@DS("#thread.tenant")
public class SmsContext {
    private final Map<String, SmsStrategy> smsContextStrategyMap = new ConcurrentHashMap<>();

    private final SmsTaskMapper smsTaskMapper;
    private final SmsTemplateMapper smsTemplateMapper;

    public SmsContext(
            Map<String, SmsStrategy> strategyMap,
            SmsTaskMapper smsTaskMapper,
            SmsTemplateMapper smsTemplateMapper) {
        strategyMap.forEach(this.smsContextStrategyMap::put);
        this.smsTaskMapper = smsTaskMapper;
        this.smsTemplateMapper = smsTemplateMapper;
    }

    /**
     * 根据任务id发送短信
     * <p>
     * 待完善的点：
     * 1， 查询次数过多，想办法优化
     *
     * @param taskId 任务id
     * @return 任务id
     */
    public String smsSend(Long taskId) {
        SmsTask smsTask = smsTaskMapper.selectById(taskId);
        BizAssert.notNull(smsTask, "短信任务尚未保存成功");

        SmsTemplate template = smsTemplateMapper.selectById(smsTask.getTemplateId());
        BizAssert.notNull(template, "短信模板为空");

        // 根据短信任务选择的服务商，动态选择短信服务商策略类来具体发送短信
        SmsStrategy smsStrategy = smsContextStrategyMap.get(template.getProviderType().name());
        BizAssert.notNull(smsStrategy, "短信供应商不存在");

        R<String> result = smsStrategy.sendSms(smsTask, template);
        if (result.isSuccess()) {
            return result.getData();
        }
        return null;
    }

}

package com.dwi.saas.sms.biz.strategy.impl;

import com.alibaba.fastjson.JSONObject;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.sms.SmsClient;
import com.baidubce.services.sms.SmsClientConfiguration;
import com.baidubce.services.sms.model.SendMessageV2Request;
import com.baidubce.services.sms.model.SendMessageV2Response;
import com.dwi.saas.sms.biz.dao.SmsTaskMapper;
import com.dwi.saas.sms.biz.service.SmsSendStatusService;
import com.dwi.saas.sms.biz.strategy.domain.SmsDO;
import com.dwi.saas.sms.biz.strategy.domain.SmsResult;
import com.dwi.saas.sms.domain.enumeration.ProviderType;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * 百度发送短信 实现类
 *
 * @author dwi
 * @date 2018/12/20
 */
@Component("BAIDU")
@Slf4j
public class SmsBaiduStrategy extends AbstractSmsStrategy {
    public SmsBaiduStrategy(SmsTaskMapper smsTaskMapper, SmsSendStatusService smsSendStatusService) {
        super(smsTaskMapper, smsSendStatusService);
    }

    @Override
    protected SmsResult send(SmsDO smsDO) {
        try {
            // ak、sk等config
            SmsClientConfiguration config = new SmsClientConfiguration();
            config.setCredentials(new DefaultBceCredentials(smsDO.getAppId(), smsDO.getAppSecret()));
            config.setEndpoint(smsDO.getEndPoint());

            // 实例化发送客户端
            SmsClient smsClient = new SmsClient(config);
            // 若模板内容为：您的验证码是${code},在${time}分钟内输入有效

            //实例化请求对象
            SendMessageV2Request request = new SendMessageV2Request();
            request.withInvokeId(smsDO.getSignName())
                    .withPhoneNumber(smsDO.getPhone())
                    .withTemplateCode(smsDO.getTemplateCode())
                    .withContentVar(JSONObject.parseObject(smsDO.getTemplateParams(), Map.class));

            // 发送请求
            SendMessageV2Response response = smsClient.sendMessage(request);

            log.info("百度发送短信返回值={}", JSONObject.toJSONString(response));

            return SmsResult.build(ProviderType.BAIDU, response.getCode(),
                    response.getRequestId(), "", response.getMessage(), 0);
        } catch (Exception e) {
            log.error(e.getMessage());
            return SmsResult.fail(e.getMessage());
        }
    }


}

package com.dwi.saas.example.config;

import com.dwi.basic.boot.config.BaseConfig;
import com.dwi.basic.log.event.SysLogListener;
import com.dwi.saas.authority.OptLogApi;

//import com.dwi.saas.oauth.api.LogApi;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dwi
 * @date 2017-12-15 14:42
 */
@Configuration
public class ExampleWebConfiguration extends BaseConfig {

    @Bean
    @ConditionalOnExpression("${saas.log.enabled:true} && 'DB'.equals('${saas.log.type:LOGGER}')")
    public SysLogListener sysLogListener(OptLogApi optLogApi) {
        return new SysLogListener(optLogApi::save);
    }
}

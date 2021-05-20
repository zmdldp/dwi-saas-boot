package com.dwi.saas.gateway.config;

import com.dwi.saas.gateway.filter.PreCheckMvcConfigurer;
import com.dwi.saas.gateway.service.BlockListService;
import com.dwi.saas.gateway.service.RateLimiterService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author dwi
 * @date 2017-12-15 14:42
 */
@Configuration
public class GatewayWebConfiguration {

    @Bean
    public PreCheckMvcConfigurer getPreCheckMvcConfigurer(BlockListService blockListService, RateLimiterService rateLimiterService) {
        return new PreCheckMvcConfigurer(blockListService, rateLimiterService);
    }
}

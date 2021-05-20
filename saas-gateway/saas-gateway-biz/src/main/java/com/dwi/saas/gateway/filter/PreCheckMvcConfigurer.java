package com.dwi.saas.gateway.filter;

import com.dwi.saas.gateway.service.BlockListService;
import com.dwi.saas.gateway.service.RateLimiterService;
import lombok.AllArgsConstructor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 公共配置类, 一些公共工具配置
 *
 * @author dwi
 * @date 2018/8/25
 */
@AllArgsConstructor
public class PreCheckMvcConfigurer implements WebMvcConfigurer {

    private final BlockListService blockListService;
    private final RateLimiterService rateLimiterService;

    /**
     * 注册 拦截器
     *
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new PreCheckInterceptor(blockListService, rateLimiterService))
                .addPathPatterns("/**")
                .order(10);
    }
}

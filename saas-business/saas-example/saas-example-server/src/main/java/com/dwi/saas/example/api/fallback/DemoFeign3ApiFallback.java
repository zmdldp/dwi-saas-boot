package com.dwi.saas.example.api.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.example.api.DemoFeign3Api;
import com.dwi.saas.example.domain.dto.RestTestDTO;

import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author dwi
 * @date 2020/6/10 下午10:47
 */
@Component
@Slf4j
public class DemoFeign3ApiFallback implements FallbackFactory<DemoFeign3Api> {
    @Override
    public DemoFeign3Api create(Throwable throwable) {
        return new DemoFeign3Api() {
            @Override
            public R<RestTestDTO> fallback(String key) {
                log.error("error", throwable);
                return R.timeout();
            }

            @Override
            public RestTestDTO fallback2(String key) {
                log.error("error", throwable);
                return null;
            }
        };
    }
}

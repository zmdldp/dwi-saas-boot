package com.dwi.saas.example.api.fallback;

import com.dwi.basic.base.R;
import com.dwi.saas.example.api.DemoFeign2Api;
import com.dwi.saas.example.domain.dto.RestTestDTO;

import org.springframework.stereotype.Component;

/**
 * @author dwi
 * @date 2020/6/10 下午10:46
 */
@Component
public class DemoFeign2ApiFallback implements DemoFeign2Api {
    @Override
    public R<RestTestDTO> fallback(String key) {
        return R.timeout();
    }
}

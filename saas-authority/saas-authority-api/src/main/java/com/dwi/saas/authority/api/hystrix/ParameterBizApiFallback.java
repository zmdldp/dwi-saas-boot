package com.dwi.saas.authority.api.hystrix;

import com.dwi.saas.authority.api.ApplicationBizApi;
import com.dwi.saas.authority.api.ParameterBizApi;
import com.dwi.saas.authority.api.domain.Application;
import com.dwi.basic.base.R;
import org.springframework.stereotype.Component;

/**
 * 用户API熔断
 *
 * @author dwi
 * @date 2020/12/16
 */
@Component
public class ParameterBizApiFallback implements ParameterBizApi {


	@Override
	public R<String> getValue(String key, String defVal) {
		return R.timeout();
	}
}

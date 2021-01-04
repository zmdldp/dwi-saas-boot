package com.dwi.saas.authority.api.hystrix;

import com.dwi.saas.authority.api.UserBizApi;
import com.dwi.saas.authority.api.domain.User;
import com.dwi.basic.base.R;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * 用户API熔断
 *
 * @author dwi
 * @date 2019/07/23
 */
@Component
public class UserBizApiFallback implements UserBizApi {
    @Override
    public R<List<Long>> findAllUserId() {
        return R.timeout();
    }

    @Override
    public R<List<User>> findUserById(List<Long> ids) {
        return R.timeout();
    }

	@Override
	public R<User> getByAccount(String account) {
		return R.timeout();
	}

	@Override
	public R<User> getByIdCache(Long userId) {
		return R.timeout();
	}

	
	  @Override 
	  public Map<String, Object> getDataScopeById(Long userId) {
		  return null; 
	  }
	 
}

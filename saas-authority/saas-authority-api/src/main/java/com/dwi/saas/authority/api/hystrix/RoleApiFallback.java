package com.dwi.saas.authority.api.hystrix;


//import com.dwi.saas.oauth.api.RoleApi;
import com.dwi.basic.base.R;
import com.dwi.saas.authority.api.RoleApi;

import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 角色查询API
 *
 * @author dwi
 * @date 2019/08/02
 */
@Component
public class RoleApiFallback implements RoleApi {
    @Override
    public R<List<Long>> findUserIdByCode(String[] codes) {
        return R.timeout();
    }
}

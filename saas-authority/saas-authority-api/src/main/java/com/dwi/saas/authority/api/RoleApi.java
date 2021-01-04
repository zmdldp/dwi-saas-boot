package com.dwi.saas.authority.api;


import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.basic.base.R;

/**
 * 角色API
 *
 * @author dwi
 * @date 2019/08/02
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "/role", fallback = RoleApiFallback.class)
@RequestMapping("/role")
public interface RoleApi {
    /**
     * 根据角色编码，查找用户id
     *
     * @param codes 角色编码
     * @return 用户id
     */
    @GetMapping("/codes")
    R<List<Long>> findUserIdByCode(@RequestParam(value = "codes") String[] codes);
}

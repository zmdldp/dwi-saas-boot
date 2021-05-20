package com.dwi.saas.authority;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

/**
 * 岗位API
 *
 * @author dwi
 * @date 2020/08/02
 */
public interface OrgApi {

    /**
     * 根据 id 查询组织，并转换成Map结构
     *
     * @param ids id
     * @return id-org
     */
    @GetMapping("/findOrgByIds")
    Map<Serializable, Object> findOrgByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据 id 查询组织名称，并转换成Map结构
     *
     * @param ids id
     * @return id-name
     */
    @GetMapping("/findOrgNameByIds")
    Map<Serializable, Object> findOrgNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);

}

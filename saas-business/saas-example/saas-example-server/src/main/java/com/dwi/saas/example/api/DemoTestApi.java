package com.dwi.saas.example.api;

import com.dwi.basic.base.R;
//import com.dwi.saas.demo.domain.entity.Product;
import com.dwi.saas.example.domain.entity.Product;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * This is a Description
 *
 * @author dwi
 * @date 2019/08/12
 */
@FeignClient(name = "${saas.feign.demo-server:saas-demo-server}", path = "/seata")
public interface DemoTestApi {
    /**
     * 新增时发生异常
     *
     * @param data
     * @return
     */
    @PostMapping("/saveEx")
    R<Product> saveEx(@RequestBody Product data);

    /**
     * 新增
     *
     * @param data
     * @return
     */
    @PostMapping("/save")
    R<Product> save(@RequestBody Product data);
}

package com.dwi.saas.demo.biz.service;

import com.dwi.basic.base.service.SuperService;
import com.dwi.saas.demo.domain.entity.Product;

/**
 * <p>
 * 业务接口
 * 商品
 * </p>
 *
 * @author dwi
 * @date 2019-08-13
 */
public interface ProductService extends SuperService<Product> {

    boolean saveEx(Product data);
}

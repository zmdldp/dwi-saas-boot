package com.dwi.saas.demo.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.dwi.basic.base.service.SuperServiceImpl;
import com.dwi.saas.demo.dao.ProductMapper;
import com.dwi.saas.demo.domain.entity.Product;
import com.dwi.saas.demo.service.ProductService;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * <p>
 * 业务实现类
 * 商品
 * </p>
 *
 * @author dwi
 * @date 2020-08-13
 */
@Slf4j
@Service
@DS("#thread.tenant")
public class ProductServiceImpl extends SuperServiceImpl<ProductMapper, Product> implements ProductService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save(Product entity) {
        return super.save(entity);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean saveEx(Product data) {
        boolean bool = super.save(data);
        int a = 1 / 0;
        log.info("a=", a);
        return bool;
    }
}

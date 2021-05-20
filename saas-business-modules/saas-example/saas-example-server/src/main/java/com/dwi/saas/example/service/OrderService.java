package com.dwi.saas.example.service;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.base.service.SuperCacheService;
import com.dwi.saas.demo.domain.entity.Order;

import java.util.List;

/**
 * <p>
 * 业务接口
 * 订单
 * </p>
 *
 * @author dwi
 * @date 2020-08-13
 */
public interface OrderService extends SuperCacheService<Order> {

    List<Order> find(Order data);

    List<Order> findInjectionResult(Order data);

    IPage<Order> findPage(IPage page, Wrapper<Order> wrapper);

    boolean save1(Order order);

    boolean save2(Order order);
}

package com.dwi.saas.example.dao;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.dwi.basic.base.mapper.SuperMapper;
import com.dwi.basic.database.mybatis.auth.DataScope;
import com.dwi.saas.demo.domain.entity.Order;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * 订单
 * </p>
 *
 * @author dwi
 * @date 2020-08-13
 */
@Repository
public interface OrderMapper extends SuperMapper<Order> {

    List<Order> find(@Param("data") Order data);


    IPage<Order> findPage(IPage page, @Param(Constants.WRAPPER) Wrapper<Order> wrapper, DataScope dataScope);
}

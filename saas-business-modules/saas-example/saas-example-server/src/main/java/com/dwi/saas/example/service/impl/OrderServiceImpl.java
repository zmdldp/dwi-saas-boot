package com.dwi.saas.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.annotation.echo.EchoResult;

import com.dwi.basic.base.service.SuperCacheServiceImpl;
import com.dwi.basic.cache.model.CacheKeyBuilder;
import com.dwi.basic.database.mybatis.auth.DataScope;
import com.dwi.basic.database.mybatis.conditions.Wraps;
import com.dwi.basic.echo.properties.EchoProperties;
import com.dwi.saas.demo.domain.entity.Order;
import com.dwi.saas.example.dao.OrderMapper;
import com.dwi.saas.example.service.OrderService;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * <p>
 * 业务实现类
 * 订单
 * </p>
 *
 * @author dwi
 * @date 2020-08-13
 */
@Slf4j
@Service
@DS("#thread.tenant")
@AllArgsConstructor
public class OrderServiceImpl extends SuperCacheServiceImpl<OrderMapper, Order> implements OrderService {

    private final EchoProperties ips;

    @Override
    protected CacheKeyBuilder cacheKeyBuilder() {
        return () -> "order";
    }

    @Override
    public List<Order> find(Order data) {
        return baseMapper.find(data);
    }


    @Override
    @EchoResult
    public List<Order> findInjectionResult(Order data) {
        return baseMapper.find(data);
    }

    @Override
    public IPage<Order> findPage(IPage page, Wrapper<Order> wrapper) {
        return baseMapper.findPage(page, wrapper, new DataScope());
    }


    public Map<Serializable, Object> findDictionaryItem(Set<Serializable> codes) {
        if (codes.isEmpty()) {
            return Collections.emptyMap();
        }
        // 3. 将 Map<String, String> 转换成 Map<Serializable, Object>
        Map<Serializable, Object> typeCodeNameMap = new HashMap<>(16);
        typeCodeNameMap.put("EDUCATION" + ips.getDictSeparator() + "COLLEGE", "本科");
        typeCodeNameMap.put("EDUCATION" + ips.getDictSeparator() + "SUOSHI", "硕士");
        typeCodeNameMap.put("NATION" + ips.getDictSeparator() + "mz_hanz", "汉族");
        typeCodeNameMap.put("NATION" + ips.getDictSeparator() + "mz_zz", "壮族");

        return typeCodeNameMap;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save1(Order order) {
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        baseMapper.insertBatchSomeColumn(Arrays.asList(order));
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        updateBatchById(Arrays.asList(order));
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        baseMapper.update(order, Wraps.<Order>lbQ().eq(Order::getId, order.getId()));
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        baseMapper.update(null, Wraps.<Order>lbU().set(Order::getCode, order.getCode()).eq(Order::getId, order.getId()));
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        baseMapper.updateById(order);
        order.setUpdatedBy(null);
        order.setUpdateTime(null);
        baseMapper.updateAllById(order);
//        int a = 1 / 0;
        return true;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean save2(Order order) {
        saveBatch(Arrays.asList(order));
        int a = 1 / 0;
        log.info("a={}", a);
        return true;
    }
}

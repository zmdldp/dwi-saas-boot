package com.dwi.saas.example.controller.injection;

import com.dwi.basic.base.R;
import com.dwi.basic.injection.core.InjectionCore;
import com.dwi.saas.demo.domain.entity.Order;
import com.dwi.saas.example.biz.service.OrderService;

import io.swagger.annotations.Api;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author dwi
 * @date 2020/6/19 上午8:48
 */
@Slf4j
@RestController
@RequestMapping("/injection")
@AllArgsConstructor
@Api(value = "injection", tags = "injection")
public class InjectionController {
    private final OrderService orderService;
    private final InjectionCore injectionCore;


    /**
     * education: 本地注入
     * org: 远程注入
     * nation: 远程注入
     *
     * @param data
     * @return
     */
    @PostMapping("/injection")
    public R injection(@RequestBody Order data) {
        List<Order> orders = orderService.find(data);
        injectionCore.injection(orders, false, "education");
        return R.success(orders);
    }

    @PostMapping("/autoInjection")
    public R autoInjection(@RequestBody Order data) {
        List<Order> orders = orderService.findInjectionResult(data);
        return R.success(orders);
    }


}

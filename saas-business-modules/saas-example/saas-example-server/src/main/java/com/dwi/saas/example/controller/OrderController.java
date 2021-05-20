package com.dwi.saas.example.controller;


import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.controller.SuperCacheController;
import com.dwi.saas.demo.domain.entity.Order;
import com.dwi.saas.example.domain.dto.OrderPageDTO;
import com.dwi.saas.example.domain.dto.OrderSaveDTO;
import com.dwi.saas.example.domain.dto.OrderUpdateDTO;
import com.dwi.saas.example.service.OrderService;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 前端控制器
 * 订单
 * </p>
 *
 * @author dwi
 * @date 2020-08-13
 */
@Slf4j
@Validated
@RestController
@RequestMapping("/order")
@Api(value = "Order", tags = "订单")
@PreAuth(replace = "order:", enabled = false)
public class OrderController extends SuperCacheController<OrderService, Long, Order, OrderPageDTO, OrderSaveDTO, OrderUpdateDTO> {

}

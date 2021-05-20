package com.dwi.saas.demo.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.dwi.basic.annotation.security.PreAuth;
import com.dwi.basic.base.controller.SuperController;
import com.dwi.basic.echo.core.EchoService;
import com.dwi.saas.demo.domain.dto.ProductPageQuery;
import com.dwi.saas.demo.domain.dto.ProductSaveDTO;
import com.dwi.saas.demo.domain.dto.ProductUpdateDTO;
import com.dwi.saas.demo.domain.entity.Product;
import com.dwi.saas.demo.service.ProductService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
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
@RequestMapping("/product")
@Api(value = "ProductController", tags = "商品")
@PreAuth(replace = "demo:product:", enabled = false)
@RequiredArgsConstructor
public class ProductController extends SuperController<ProductService, Long, Product, ProductPageQuery, ProductSaveDTO, ProductUpdateDTO> {
    private final EchoService echoService;

    @Override
    public void handlerResult(IPage<Product> page) {
    	echoService.action(page);
    }
}

package com.dwi.saas.demo.controller.test.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 订单测试DTO
 *
 * @author dwi
 * @date 2020/08/01
 */
@Data
@ToString
public class Orddder implements Serializable {
    private Long id;
    private String name;
    private Producttt producttt;
}

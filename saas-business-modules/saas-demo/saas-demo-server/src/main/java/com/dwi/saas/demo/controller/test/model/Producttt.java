package com.dwi.saas.demo.controller.test.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * 商品测试DTO
 *
 * @author dwi
 * @date 2020/08/01
 */
@Data
@ToString
public class Producttt implements Serializable {
    private Long id;
    private String name;
}

package com.dwi.saas.demo.controller.test.model;

import com.dwi.saas.common.enums.HttpMethod;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

/**
 * 枚举测试DTO
 *
 * @author dwi
 * @date 2020/07/30
 */
@Data
@ToString
public class EnumDTO implements Serializable {
    private Long id;
    private String name;
    private HttpMethod httpMethod;

    private List<Producttt> list;

    public void testEx() throws Exception {
        throw new Exception("eeeeee");
    }
}

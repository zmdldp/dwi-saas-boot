package com.dwi.saas.authority.api;


import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * 岗位API
 *
 * @author dwi
 * @date 2019/08/02
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", path = "/station",
//        qualifier = "stationApi", fallback = StationApiFallback.class)
@RequestMapping("/station")
public interface StationApi {

    /**
     * 根据id查询 岗位
     *
     * @param ids id
     * @return id-station
     */
    @GetMapping("/findStationByIds")
    Map<Serializable, Object> findStationByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询 岗位名称
     *
     * @param ids id
     * @return id-name
     */
    @GetMapping("/findStationNameByIds")
    Map<Serializable, Object> findStationNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}

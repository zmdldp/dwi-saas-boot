package com.dwi.saas.authority;

import java.io.Serializable;
import java.util.Map;
import java.util.Set;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



/**
 * 注入
 *
 * @author dwi
 * @date 2021/01/20
 */
public interface InjectionApi {
	
	/**
	 * 根据id查询用户
	 * 
	 * @param ids
	 * @return
	 */
    @GetMapping("/user/findUserByIds")
    public Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * 根据id查询用户名称
     * 
     * @param ids
     * @return
     */
    @GetMapping("/user/findUserNameByIds")
    public Map<Serializable, Object> findUserNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);

    /**
     * @param ids
     * @return
     */
    @GetMapping("/station/findStationByIds")
    public Map<Serializable, Object> findStationByIds(@RequestParam("ids") Set<Serializable> ids);

    /**
     * @param ids
     * @return
     */
    @GetMapping("/station/findStationNameByIds")
    public Map<Serializable, Object> findStationNameByIds(@RequestParam("ids") Set<Serializable> ids);

    /**
     * 根据字典编码查询字典项
     * 
     * @param codes
     * @return
     */
    @GetMapping("/dictionary/findDictionaryItem")
    public Map<Serializable, Object> findDictionaryItem(@RequestParam Set<Serializable> codes);

    /**
     * @param ids
     * @return
     */
    @GetMapping("/org/findOrgByIds")
    public Map<Serializable, Object> findOrgByIds(@RequestParam("ids") Set<Serializable> ids);

    /**
     * @param ids
     * @return
     */
    @GetMapping("/org/findOrgNameByIds")
    public Map<Serializable, Object> findOrgNameByIds(@RequestParam("ids") Set<Serializable> ids);

}

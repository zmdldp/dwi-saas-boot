package com.dwi.saas.authority.api;

import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.api.domain.User;

/**
 * 用户
 *
 * @author dwi
 * @date 2019/07/02
 */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = UserBizApiFallback.class
//        , path = "/user", qualifier = "userBizApi")
@RequestMapping("/user")
public interface UserBizApi {

    /**
     * 根据用户ID查询缓存用户信息 ADD 2020-12-16
     *
     * @param userId
     * @return
     */
    @GetMapping(value = "/getByIdCache")
    public R<User> getByIdCache(@RequestParam("userId") Long userId);

    /**
     * 根据用户id，查询用户权限范围   ADD 2020-12-16
     *
     * @param id 用户id
     */
    @GetMapping(value = "/ds/{id}")
    public Map<String, Object> getDataScopeById(@PathVariable("id") Long id);


    /**
     * 查询所有的用户id
     *
     * @return 全部用户的id
     */
    @RequestMapping(value = "/find", method = RequestMethod.GET)
    R<List<Long>> findAllUserId();

    /**
     * 查询账户关联用户 ADD 2020-12-16
     *
     * @param account
     * @return
     */
    @GetMapping(value = "/findUserByAccount")
    R<User> getByAccount(@RequestParam("account") String account);


    /**
     * 根据id集合查询所有的用户
     *
     * @param ids id集合
     * @return 指定用户
     */
    @RequestMapping(value = "/findUserById", method = RequestMethod.GET)
    R<List<User>> findUserById(@RequestParam(value = "ids") List<Long> ids);

    //  /**
    //  * 根据id查询用户
    //  *
    //  * @param ids id
    //  * @return id-user
    //  */
    // @GetMapping("/findUserByIds")
    // Map<Serializable, Object> findUserByIds(@RequestParam(value = "ids") Set<Serializable> ids);
    //
    // /**
    //  * 根据id查询用户名称
    //  *
    //  * @param ids id
    //  * @return id-name
    //  */
    // @GetMapping("/findUserNameByIds")
    // Map<Serializable, Object> findUserNameByIds(@RequestParam(value = "ids") Set<Serializable> ids);
}

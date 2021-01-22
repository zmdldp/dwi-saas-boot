package com.dwi.saas.authority;

import com.dwi.basic.base.R;
import com.dwi.saas.authority.domain.entity.auth.User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Map;

/**
 * 用户
 *
 * @author dwi
 * @date 2019/07/02
 */
public interface UserApi {
	
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
    @GetMapping(value = "/find")
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
    @GetMapping(value = "/findUserById")
    R<List<User>> findUserById(@RequestParam(value = "ids") List<Long> ids);
    
    @PostMapping(value = "/resetPassErrorNum")
    R<Boolean> resetPassErrorNum(@RequestParam("userId") Long userId);
    
    @PostMapping(value = "/incrPasswordErrorNumById")
    R<Boolean> incrPasswordErrorNumById(@RequestParam("userId") Long userId);


}

package com.dwi.saas.authority.controller.common;

import com.baidu.fsg.uid.UidGenerator;
import com.dwi.basic.annotation.user.LoginUser;
import com.dwi.basic.base.R;
import com.dwi.basic.security.model.SysUser;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.authority.service.auth.UserService;
import com.dwi.saas.authority.service.common.LoginLogService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * 前端控制器
 * 首页
 * </p>
 *
 * @author dwi
 * @date 2020-10-20
 */
@Slf4j
@Validated
@RestController
@Api(value = "dashboard", tags = "首页")
@RequiredArgsConstructor
public class DashboardController {

    private final LoginLogService loginLogService;
    private final UserService userService;
    private final UidGenerator uidGenerator;

    @PostMapping("/dashboard/pvIncr")
    public R<Boolean> pvIncr() {
        loginLogService.pvIncr();
        return R.success();
    }
    
    @GetMapping("/dashboard/item")
    public R<Map<String, Object>> item() {
        Map<String, Object> data = new HashMap<>(11);
        // 用户数
        data.put("totalUserCount", userService.count());
        data.put("todayUserCount", userService.todayUserCount());
        // 页面 访问量
        data.put("totalPv", loginLogService.getTotalPv());
        data.put("todayPv", loginLogService.getTodayPv());
        // 独立 登录IV数
        data.put("totalLoginIv", loginLogService.getTotalLoginIv());
        data.put("todayLoginIv", loginLogService.getTodayLoginIv());
        // 独立 登录PV数
        data.put("totalLoginPv", loginLogService.getTotalLoginPv());
        data.put("todayLoginPv", loginLogService.getTodayLoginPv());

        return R.success(data);
    }
    
    @GetMapping("/dashboard/chart")
    public R<Map<String, Object>> chart(@ApiIgnore @LoginUser SysUser user) {
        Map<String, Object> data = new HashMap<>(11);
        data.put("lastTenVisitCount", loginLogService.findLastTenDaysVisitCount(null));
        data.put("lastTenUserVisitCount", loginLogService.findLastTenDaysVisitCount(user.getAccount()));

        data.put("browserCount", loginLogService.findByBrowser());
        data.put("operatingSystemCount", loginLogService.findByOperatingSystem());
        return R.success(data);
    }

    @GetMapping("/common/generateId")
    public R<Object> generate() {
        long uid = uidGenerator.getUid();
        return R.success(uid + "length" + String.valueOf(uid).length());
    }
}

package com.dwi.saas.authority.controller.common;

import com.baidu.fsg.uid.UidGenerator;
import com.dwi.basic.annotation.user.LoginUser;
import com.dwi.basic.base.R;
import com.dwi.basic.security.model.SysUser;
import com.dwi.saas.authority.biz.service.common.LoginLogService;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
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
 * @date 2019-10-20
 */
@Slf4j
@Validated
@RestController
@Api(value = "dashboard", tags = "首页")
@RequiredArgsConstructor
public class DashboardController {

    private final LoginLogService loginLogService;
    private final UidGenerator uidGenerator;

    @GetMapping("/dashboard/visit")
    public R<Map<String, Object>> index(@ApiIgnore @LoginUser SysUser user) {
        Map<String, Object> data = new HashMap<>(11);
        // 获取系统访问记录
        data.put("totalVisitCount", loginLogService.getTotalVisitCount());
        data.put("todayVisitCount", loginLogService.getTodayVisitCount());
        data.put("todayIp", loginLogService.getTodayIp());

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

package com.dwi.saas.demo.controller.cloud;

import com.dwi.basic.base.R;
import com.dwi.saas.demo.TestDateApi;
//import com.dwi.saas.authority.domain.dto.test.DateDTO;
//import com.dwi.saas.authority.domain.entity.auth.Menu;
import com.dwi.saas.demo.domain.dto.DateDTO;
import com.dwi.saas.demo.domain.entity.MenuTest;

import io.swagger.annotations.Api;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Date;

/**
 * 日期参数 测试
 *
 * @author dwi
 * @date 2020/07/24
 */
@Slf4j
@RestController
@RequestMapping("/date")
@Api(value = "Date", tags = "时间类型验证器")
public class DateController implements TestDateApi{

    @PostMapping("/post1")
    public R<DateDTO> bodyPos1(@RequestBody DateDTO data) {
        log.info("post1={}", data);
        return R.success(data);
    }

    @PostMapping("/post2")
    public R<MenuTest> bodyPos2(@RequestBody MenuTest data) {
        log.info("post2={}", data);
        return R.success(data);
    }


    @GetMapping("/get1")
    public R<DateDTO> get(DateDTO data) {
        log.info("get1={}", data);
        return R.success(data);
    }

    @GetMapping("/get2")
    public R<DateDTO> get2(@RequestParam(required = false, value = "date") Date date,
                                  @RequestParam(required = false, value = "dt") LocalDateTime dt,
                                  @RequestParam(required = false, value = "d") LocalDate d,
                                  @RequestParam(required = false, value = "t") LocalTime t) {
        DateDTO dateDTO = new DateDTO();
        dateDTO.setD(LocalDate.now());
        dateDTO.setDate(new Date());
        dateDTO.setDt(LocalDateTime.now());
        dateDTO.setT(LocalTime.now());
        return R.success(dateDTO);
    }


}

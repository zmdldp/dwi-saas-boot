//package com.dwi.saas.authority.api;
//
//import com.dwi.saas.authority.api.domain.Online;
//import com.dwi.saas.authority.api.domain.User;
//import com.dwi.saas.authority.api.hystrix.OnlineBizApiFallback;
//import com.dwi.saas.authority.api.hystrix.UserBizApiFallback;
////import com.dwi.saas.authority.domain.dto.auth.Online;
//
//import io.swagger.annotations.ApiOperation;
//
//import com.dwi.basic.base.R;
//import org.springframework.cloud.openfeign.FeignClient;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PutMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//
//import java.util.List;
//
///**
// * 用户
// *
// * @author dwi
// * @date 2020/12/16
// */
//@FeignClient(name = "${saas.feign.authority-server:saas-authority-server}", fallback = OnlineBizApiFallback.class
//        , path = "/online", qualifier = "onlineBizApi")
//public interface OnlineBizApi {
//
//    /**
//     * 保存在线用户信息 ADD 2020-12-16
//     * 
//     * @param model
//     * @return
//     */
//    @PutMapping()
//    public R<Boolean> saveOnlineInfo(@RequestBody Online online);
//}

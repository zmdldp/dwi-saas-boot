//package com.dwi.saas.authority.api.hystrix;
//
//import com.dwi.saas.authority.api.OnlineBizApi;
//import com.dwi.saas.authority.api.UserBizApi;
//import com.dwi.saas.authority.api.domain.Online;
//import com.dwi.saas.authority.api.domain.User;
//import com.dwi.basic.base.R;
//import org.springframework.stereotype.Component;
//
//import java.util.List;
//
///**
// * 用户API熔断
// *
// * @author dwi
// * @date 2020/12/16
// */
//@Component
//public class OnlineBizApiFallback implements OnlineBizApi {
//
//	@Override
//	public R<Boolean> saveOnlineInfo(Online model) {
//		return R.timeout();
//	}
//}

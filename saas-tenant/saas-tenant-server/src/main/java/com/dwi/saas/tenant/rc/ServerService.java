package com.dwi.saas.tenant.rc;

import java.util.List;

import com.dwi.saas.tenant.domain.vo.ServerServiceVO;

/**
 * 服务器服务
 * 
 * @author dwi
 *
 */
public interface ServerService {
	

	/**
	 * 获取服务信息列表
	 * 
	 * @return
	 */
	List<ServerServiceVO> list();

	/**
	 * 获取当前租户服务同源的所有其它服务名称列表
	 * 
	 * @return
	 */
	List<String> getServices();

	/**
	 * 根据服务名称和操作数据源方法获取请求映射列表
	 * 
	 * @param service
	 * @param method
	 * @return
	 */
	List<String> getDsReqUrls(String service, String initDsParamMethodInit);

}

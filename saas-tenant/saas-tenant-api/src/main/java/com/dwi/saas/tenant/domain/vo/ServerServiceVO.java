package com.dwi.saas.tenant.domain.vo;

import com.dwi.basic.database.properties.MultiTenantType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;

/**
 * <p>
 * 视图类
 * 服务
 * </p>
 *
 * @author dwi
 * @since 2021-03-03
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "ServerServiceVO", description = "服务")
public class ServerServiceVO {
	
	
	/**
	 * 服务名
	 */
	@ApiModelProperty(value = "服务名")
	private String serviceName;
	
	/**
	 * 多租户类型
	 */
	@ApiModelProperty(value = "多租户类型")
	private MultiTenantType multiTenantType;
	
	

}

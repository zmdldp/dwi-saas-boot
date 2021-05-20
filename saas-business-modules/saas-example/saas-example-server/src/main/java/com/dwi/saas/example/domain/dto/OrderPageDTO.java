package com.dwi.saas.example.domain.dto;

import com.dwi.basic.annotation.echo.Echo;
import com.dwi.basic.model.RemoteData;
import com.dwi.saas.common.constant.DictionaryType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import javax.validation.constraints.Size;

import java.io.Serializable;

import static com.dwi.saas.common.constant.EchoConstants.DICTIONARY_ITEM_FEIGN_CLASS;
import static com.dwi.saas.common.constant.EchoConstants.FIND_NAME_BY_IDS;
import static com.dwi.saas.common.constant.EchoConstants.ORG_ID_FEIGN_CLASS;

/**
 * <p>
 * 实体类
 * 订单(用于测试)
 * </p>
 *
 * @author dwi
 * @since 2020-06-19
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = false)
@Builder
@ApiModel(value = "OrderPageDTO", description = "订单(用于测试)")
public class OrderPageDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 名称
     */
    @ApiModelProperty(value = "名称")
    @Size(max = 255, message = "名称长度不能超过255")
    private String name;
    /**
     * 学历
     *
     * @Echo(api = "orderServiceImpl", method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "学历")
    @Size(max = 255, message = "学历长度不能超过255")
    @Echo(api = "orderServiceImpl", method = FIND_NAME_BY_IDS, dictType = DictionaryType.EDUCATION)
    private RemoteData<String, String> education;
    /**
     * 民族
     *
     * @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION) RemoteData<String, String>
     */
    @ApiModelProperty(value = "民族")
    @Size(max = 255, message = "民族长度不能超过255")
    @Echo(api = DICTIONARY_ITEM_FEIGN_CLASS, method = FIND_NAME_BY_IDS, dictType = DictionaryType.NATION)
    private RemoteData<String, String> nation;
    /**
     * 组织ID
     * #c_org
     *
     * @Echo(api = ORG_ID_FEIGN_CLASS, method = FIND_NAME_BY_IDS) RemoteData<Long, String>
     */
    @ApiModelProperty(value = "组织ID")
    @Echo(api = ORG_ID_FEIGN_CLASS, method = FIND_NAME_BY_IDS)
    private RemoteData<Long, String> org;
    /**
     * 编号
     */
    @ApiModelProperty(value = "编号")
    @Size(max = 255, message = "编号长度不能超过255")
    private String code;

}

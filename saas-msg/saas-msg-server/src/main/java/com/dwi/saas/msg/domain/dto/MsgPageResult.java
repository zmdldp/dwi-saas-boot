package com.dwi.saas.msg.domain.dto;

import cn.afterturn.easypoi.excel.annotation.Excel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDateTime;

import com.dwi.saas.msg.domain.entity.Msg;

import static com.dwi.basic.utils.DateUtils.DEFAULT_DATE_TIME_FORMAT;

/**
 * 消息分页返回
 *
 * @author dwi
 * @date 2020/08/02
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ApiModel(value = "MsgPageResult", description = "消息分页返回")
@ToString(callSuper = true)
public class MsgPageResult extends Msg {
    private static final long serialVersionUID = -44224723996050485L;
    @ApiModelProperty(value = "状态")
    @Excel(name = "状态", replace = {"已读_true", "未读_false", "_null"})
    private Boolean isRead;
    @ApiModelProperty(value = "读消息的时间")
    @Excel(name = "读消息时间", width = 20, format = DEFAULT_DATE_TIME_FORMAT)
    private LocalDateTime readTime;
    @ApiModelProperty(value = "接收表id")
    private Long receiveId;
}

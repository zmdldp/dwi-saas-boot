package com.dwi.saas.file.domain.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

import com.dwi.saas.file.domain.enumeration.DataType;

/**
 * 文件分页列表请求参数
 *
 * @author dwi
 * @date 2020-04-29
 */
@Data
@ApiModel(value = "FilePageReq", description = "文件分页列表请求参数")
public class FilePageReqDTO implements Serializable {
    @ApiModelProperty(value = "文件夹id")
    private Long folderId;
    @ApiModelProperty(value = "原始文件名")
    private String submittedFileName;
    @ApiModelProperty(value = "数据类型 null和''表示查询全部 图片：IMAGE 视频：VIDEO 音频：AUDIO 文档DOC 其他：OTHER", example = "IMAGE,VIDEO,AUDIO,DOC,OTHER")
    private DataType dataType;
    @ApiModelProperty(value = "开始时间")
    private LocalDateTime startCreateTime;
    @ApiModelProperty(value = "结束时间")
    private LocalDateTime endCreateTime;
}

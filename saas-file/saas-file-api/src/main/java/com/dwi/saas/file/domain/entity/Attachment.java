package com.dwi.saas.file.domain.entity;

import com.dwi.basic.base.entity.Entity;
import com.dwi.saas.file.domain.enumeration.DataType;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDateTime;

import static com.baomidou.mybatisplus.annotation.SqlCondition.LIKE;

/**
 * <p>
 * 实体类
 * 附件
 * </p>
 *
 * @author dwi
 * @since 2020-11-20
 */
@Data
@NoArgsConstructor
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@ApiModel(value = "Attachment", description = "附件")
@AllArgsConstructor
public class Attachment extends Entity<Long> {

    private static final long serialVersionUID = 1L;

    /**
     * 业务ID
     */
    private String bizId;

    /**
     * 业务类型
     * #AttachmentType
     */
    private String bizType;

    /**
     * 数据类型
     * #DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}
     */
    private DataType dataType;

    /**
     * 原始文件名
     */
    private String submittedFileName;

    /**
     * FastDFS返回的组
     * 用于FastDFS
     */
    private String group;

    /**
     * FastDFS的远程文件名
     * 用于FastDFS
     */
    private String path;

    /**
     * 文件相对路径
     */
    private String relativePath;

    /**
     * 文件访问链接
     * 需要通过nginx配置路由，才能访问
     */
    private String url;

    /**
     * 文件md5值
     */
    private String fileMd5;

    /**
     * 文件上传类型
     * 取上传文件的值
     */
    private String contextType;

    /**
     * 唯一文件名
     */
    private String filename;

    /**
     * 后缀
     * (没有.)
     */
    private String ext;

    /**
     * 大小
     */
    private Long size;

    /**
     * 组织ID
     * #c_core_org
     */
    private Long orgId;

    /**
     * 图标
     */
    private String icon;

    /**
     * 创建年月
     * 格式：yyyy-MM 用于统计
     */
    private String createMonth;

    /**
     * 创建时处于当年的第几周
     * 格式：yyyy-ww 用于统计
     */
    private String createWeek;

    /**
     * 创建年月日
     * 格式： yyyy-MM-dd 用于统计
     */
    private String createDay;


    @Builder
    public Attachment(Long id, LocalDateTime createTime, Long createdBy, LocalDateTime updateTime, Long updatedBy,
                      String bizId, String bizType, DataType dataType, String submittedFileName, String group,
                      String path, String relativePath, String url, String fileMd5, String contextType, String filename,
                      String ext, Long size, Long orgId, String icon, String createMonth, String createWeek, String createDay) {
        this.id = id;
        this.createTime = createTime;
        this.createdBy = createdBy;
        this.updateTime = updateTime;
        this.updatedBy = updatedBy;
        this.bizId = bizId;
        this.bizType = bizType;
        this.dataType = dataType;
        this.submittedFileName = submittedFileName;
        this.group = group;
        this.path = path;
        this.relativePath = relativePath;
        this.url = url;
        this.fileMd5 = fileMd5;
        this.contextType = contextType;
        this.filename = filename;
        this.ext = ext;
        this.size = size;
        this.orgId = orgId;
        this.icon = icon;
        this.createMonth = createMonth;
        this.createWeek = createWeek;
        this.createDay = createDay;
    }

}

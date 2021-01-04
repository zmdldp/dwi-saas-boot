package com.dwi.saas.file.biz.strategy;

import org.springframework.web.multipart.MultipartFile;

import com.dwi.saas.file.domain.domain.FileDeleteDO;
import com.dwi.saas.file.domain.entity.Attachment;

import java.util.List;

/**
 * 文件策略接口
 *
 * @author dwi
 * @date 2019/06/17
 */
public interface FileStrategy {
    /**
     * 文件上传
     *
     * @param file 文件
     * @return 文件对象
     */
    Attachment upload(MultipartFile file);

    /**
     * 删除源文件
     *
     * @param list 列表
     * @return 是否成功
     */
    boolean delete(List<FileDeleteDO> list);

}

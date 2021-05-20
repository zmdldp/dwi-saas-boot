package com.dwi.saas.file.strategy;


import com.dwi.basic.base.R;
import com.dwi.saas.file.domain.dto.chunk.FileChunksMergeDTO;
import com.dwi.saas.file.domain.entity.Attachment;

/**
 * 文件分片处理策略类
 *
 * @author dwi
 * @date 2020/06/19
 */
public interface FileChunkStrategy {

    /**
     * 根据md5检测文件
     *
     * @param md5    md5
     * @param userId 用户id
     * @return 附件
     */
    Attachment md5Check(String md5, Long userId);

    /**
     * 合并文件
     *
     * @param merge 合并参数
     * @return 附件
     */
    R<Attachment> chunksMerge(FileChunksMergeDTO merge);
}

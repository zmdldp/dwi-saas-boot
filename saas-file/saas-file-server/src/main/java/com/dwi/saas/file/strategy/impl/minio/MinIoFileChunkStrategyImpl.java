package com.dwi.saas.file.strategy.impl.minio;

import com.dwi.basic.base.R;
import com.dwi.saas.file.domain.dto.chunk.FileChunksMergeDTO;
import com.dwi.saas.file.domain.entity.Attachment;
import com.dwi.saas.file.properties.FileServerProperties;
import com.dwi.saas.file.service.AttachmentService;
import com.dwi.saas.file.strategy.impl.AbstractFileChunkStrategy;

import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * 欢迎PR
 * <p>
 * 思路1：minIO的putObject自身就支持断点续传， 所以先将分片文件上传到文件服务器并合并成大文件后， 在将大文件通过putObject直接上传到minIO
 *
 * @author zuihou
 * @date 2020/11/22 5:02 下午
 */
@Slf4j
public class MinIoFileChunkStrategyImpl extends AbstractFileChunkStrategy {
    public MinIoFileChunkStrategyImpl(AttachmentService fileService, FileServerProperties fileProperties) {
        super(fileService, fileProperties);
    }


    @Override
    protected void copyFile(Attachment file) {

    }


    @Override
    protected R<Attachment> merge(List<File> files, String path, String fileName, FileChunksMergeDTO info) throws IOException {

        Attachment filePo = Attachment.builder()
//                .relativePath(relativePath)
//                .url(StrUtil.replace(url, "\\\\", StrPool.SLASH))
                .build();
        return R.success(filePo);
    }
}

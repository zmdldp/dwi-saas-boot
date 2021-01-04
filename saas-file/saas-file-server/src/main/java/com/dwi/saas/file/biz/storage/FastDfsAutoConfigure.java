package com.dwi.saas.file.biz.storage;

import com.dwi.saas.file.biz.dao.AttachmentMapper;
import com.dwi.saas.file.biz.properties.FileServerProperties;
import com.dwi.saas.file.biz.service.AttachmentService;
import com.dwi.saas.file.biz.strategy.FileChunkStrategy;
import com.dwi.saas.file.biz.strategy.FileStrategy;
import com.dwi.saas.file.biz.strategy.impl.fastdfs.FastDfsFileChunkStrategyImpl;
import com.dwi.saas.file.biz.strategy.impl.fastdfs.FastDfsFileStrategyImpl;
import com.github.tobato.fastdfs.service.AppendFileStorageClient;
import com.github.tobato.fastdfs.service.FastFileStorageClient;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * FastDFS配置
 *
 * @author dwi
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "FAST_DFS")
public class FastDfsAutoConfigure {
    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties, FastFileStorageClient storageClient, AttachmentMapper attachmentMapper) {
        return new FastDfsFileStrategyImpl(fileProperties, storageClient, attachmentMapper);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties, AppendFileStorageClient storageClient) {
        return new FastDfsFileChunkStrategyImpl(fileService, fileProperties, storageClient);
    }
}

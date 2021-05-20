package com.dwi.saas.file.storage;

import com.dwi.saas.file.dao.AttachmentMapper;
import com.dwi.saas.file.properties.FileServerProperties;
import com.dwi.saas.file.service.AttachmentService;
import com.dwi.saas.file.strategy.FileChunkStrategy;
import com.dwi.saas.file.strategy.FileStrategy;
import com.dwi.saas.file.strategy.impl.fastdfs.FastDfsFileChunkStrategyImpl;
import com.dwi.saas.file.strategy.impl.fastdfs.FastDfsFileStrategyImpl;
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

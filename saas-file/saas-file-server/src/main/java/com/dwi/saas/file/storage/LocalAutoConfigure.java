package com.dwi.saas.file.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dwi.saas.file.properties.FileServerProperties;
import com.dwi.saas.file.service.AttachmentService;
import com.dwi.saas.file.strategy.FileChunkStrategy;
import com.dwi.saas.file.strategy.FileStrategy;
import com.dwi.saas.file.strategy.impl.local.LocalFileChunkStrategyImpl;
import com.dwi.saas.file.strategy.impl.local.LocalFileStrategyImpl;


/**
 * 本地上传配置
 *
 * @author dwi
 * @date 2020/06/18
 */

@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "LOCAL", matchIfMissing = true)
@Slf4j
public class LocalAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new LocalFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new LocalFileChunkStrategyImpl(fileService, fileProperties);
    }
}

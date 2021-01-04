package com.dwi.saas.file.biz.storage;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dwi.saas.file.biz.properties.FileServerProperties;
import com.dwi.saas.file.biz.service.AttachmentService;
import com.dwi.saas.file.biz.strategy.FileChunkStrategy;
import com.dwi.saas.file.biz.strategy.FileStrategy;
import com.dwi.saas.file.biz.strategy.impl.ali.AliFileChunkStrategyImpl;
import com.dwi.saas.file.biz.strategy.impl.ali.AliFileStrategyImpl;

/**
 * 阿里OSS
 *
 * @author dwi
 * @date 2019/08/09
 */
@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@Slf4j
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "ALI")
public class AliOssAutoConfigure {

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties fileProperties) {
        return new AliFileStrategyImpl(fileProperties);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties fileProperties) {
        return new AliFileChunkStrategyImpl(fileService, fileProperties);
    }
}

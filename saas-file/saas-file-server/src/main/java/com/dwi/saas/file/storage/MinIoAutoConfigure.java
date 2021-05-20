package com.dwi.saas.file.storage;

import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.dwi.saas.file.properties.FileServerProperties;
import com.dwi.saas.file.service.AttachmentService;
import com.dwi.saas.file.strategy.FileChunkStrategy;
import com.dwi.saas.file.strategy.FileStrategy;
import com.dwi.saas.file.strategy.impl.minio.MinIoFileChunkStrategyImpl;
import com.dwi.saas.file.strategy.impl.minio.MinIoFileStrategyImpl;


/**
 * 本地上传配置
 *
 * @author dwi
 * @date 2020/06/18
 */

@EnableConfigurationProperties(FileServerProperties.class)
@Configuration
@RequiredArgsConstructor
@ConditionalOnProperty(prefix = FileServerProperties.PREFIX, name = "type", havingValue = "MIN_IO", matchIfMissing = true)
@Slf4j
public class MinIoAutoConfigure {

    /**
     * 初始化minio客户端,不用每次都初始化
     *
     * @return MinioClient
     * @author tangyh
     */
    @Bean
    public MinioClient minioClient(FileServerProperties properties) {
        return new MinioClient.Builder()
                .endpoint(properties.getMinIo().getEndpoint())
                .credentials(properties.getMinIo().getAccessKey(), properties.getMinIo().getSecretKey())
                .build();
    }

    @Bean
    public FileStrategy getFileStrategy(FileServerProperties properties, MinioClient minioClient) {
        return new MinIoFileStrategyImpl(properties, minioClient);
    }

    @Bean
    public FileChunkStrategy getFileChunkStrategy(AttachmentService fileService, FileServerProperties properties) {
        return new MinIoFileChunkStrategyImpl(fileService, properties);
    }
}

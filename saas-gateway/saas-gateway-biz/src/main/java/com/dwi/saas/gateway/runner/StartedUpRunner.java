package com.dwi.saas.gateway.runner;

import com.dwi.basic.context.ContextUtil;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.database.properties.MultiTenantType;
import com.dwi.saas.gateway.service.BlockListService;
import com.dwi.saas.gateway.service.RateLimiterService;
import com.dwi.basic.datasource.plugin.dao.InitDatabaseMapper;
import com.dwi.basic.datasource.plugin.domain.enumeration.TenantStatusEnum;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 应用启动成功后执行， 该类会在InitDatabaseOnStarted之后执行
 *
 * @author dwi
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class StartedUpRunner implements ApplicationRunner {

    private final InitDatabaseMapper initDbMapper;
    private final BlockListService blockListService;
    private final RateLimiterService rateLimiterService;
    private final DatabaseProperties databaseProperties;

    @Override
    public void run(ApplicationArguments args) {
        if (MultiTenantType.NONE.eq(databaseProperties.getMultiTenantType())) {
            blockListService.loadAllBlockList();
            rateLimiterService.loadAllRateLimiters();
        } else {
            List<String> tenantCodeList = initDbMapper.selectTenantCodeList(TenantStatusEnum.NORMAL.name());
            tenantCodeList.forEach(tenantCode -> {
                ContextUtil.setTenant(tenantCode);
                blockListService.loadAllBlockList();
                rateLimiterService.loadAllRateLimiters();
            });
        }
    }
}

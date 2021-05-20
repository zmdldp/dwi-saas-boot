package com.dwi.saas.system.config.datasource;


import com.baomidou.mybatisplus.extension.plugins.inner.InnerInterceptor;
import com.dwi.basic.database.datasource.BaseMybatisConfiguration;
import com.dwi.basic.database.mybatis.auth.DataScopeInnerInterceptor;
import com.dwi.basic.database.properties.DatabaseProperties;
import com.dwi.basic.utils.SpringUtils;
import com.dwi.saas.authority.UserApi;
import com.dwi.saas.authority.service.auth.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.List;

/**
 * 配置一些 Mybatis 常用重用拦截器
 *
 * @author dwi
 * @date 2017-11-18 0:34
 */
@Configuration
@Slf4j
@EnableConfigurationProperties({DatabaseProperties.class})
public class SystemMybatisAutoConfiguration extends BaseMybatisConfiguration {

    public SystemMybatisAutoConfiguration(DatabaseProperties databaseProperties) {
        super(databaseProperties);
    }
    /**
     * 数据权限插件
     *
     * @return 数据权限插件
     */
    @Override
    protected List<InnerInterceptor> getPaginationBeforeInnerInterceptor() {
        List<InnerInterceptor> list = new ArrayList<>();
        Boolean isDataScope = databaseProperties.getIsDataScope();
        if (isDataScope) {
            list.add(new DataScopeInnerInterceptor(userId -> SpringUtils.getBean(UserService.class).getDataScopeById(userId)));
        }
        return list;
    }
}

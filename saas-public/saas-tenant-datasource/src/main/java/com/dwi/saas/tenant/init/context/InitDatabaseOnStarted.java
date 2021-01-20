package com.dwi.saas.tenant.init.context;

import lombok.AllArgsConstructor;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;

import com.dwi.saas.tenant.init.biz.service.DatasourceInitDataSourceService;


/**
 * 初始化数据源
 * context已经refresh且application and command-line runners（StartedUpRunner） 调用之前发送这个事件
 *
 * <p>
 * 一定要在容器初始化完成后，在初始化租户数据源
 * <p>
 * 使用 @PostConstruct 注解不行
 *
 * @author dwi
 * @date 2020年03月15日13:12:59
 */
@AllArgsConstructor
public class InitDatabaseOnStarted implements ApplicationListener<ApplicationStartedEvent> {

	DatasourceInitDataSourceService datasourceInitDataSourceService;

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
    	datasourceInitDataSourceService.initDataSource();
    }


}

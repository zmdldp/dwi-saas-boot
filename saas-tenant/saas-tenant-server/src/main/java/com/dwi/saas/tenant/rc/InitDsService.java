package com.dwi.saas.tenant.rc;


import java.util.Map;

import com.dwi.basic.datasource.plugin.domain.entity.DatasourceConfig;

//import com.dwi.saas.tenant.domain.entity.DatasourceConfig;

/**
 * 广播初始化数据源
 *
 * @author dwi
 * @date 2020年04月05日16:27:03
 */
public interface InitDsService {
    /**
     * 删除数据源
     *
     * @param tenant
     * @return
     */
    boolean removeDataSource(String tenant);

    /**
     * 初始化 数据源
     *
     * @param typeMap 租户
     * @return 是否成功
     */
    boolean initConnect(Map<String, DatasourceConfig> typeMap);
}

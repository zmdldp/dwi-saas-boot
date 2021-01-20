package com.dwi.saas.tenant.init.biz.dao;

import com.baomidou.mybatisplus.annotation.InterceptorIgnore;
import com.dwi.saas.tenant.init.domain.entity.DatasourceConfig;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 初始化数据库DAO
 *
 * @author dwi
 * @date 2019/09/02
 */
@Repository
@InterceptorIgnore(tenantLine = "true", dynamicTableName = "true")
public interface InitDatabaseMapper {
    /**
     * 创建数据库
     *
     * @param database
     * @return
     */
    int createDatabase(@Param("database") String database);


    /**
     * 删除数据库
     *
     * @param database
     * @return
     */
    int dropDatabase(@Param("database") String database);

    /**
     * 根据条件查询租户列表
     *
     * @param status      状态
     * @param connectType 连接类型
     * @return 租户编码
     */
    List<String> selectTenantCodeList(@Param("status") String status, @Param("connectType") String connectType);
    
    /**
     * 查询所有租户的数据源
     *
     * @param applicationName 服务名
     * @param status          状态
     * @param connectType     连接类型
     * @return
     */
    @Select("SELECT dc.id,dc.create_time,dc.created_by,dc.update_time,dc.updated_by, " +
            "       dc.name, dc.username, dc.password, dc.url, dc.driver_class_name driverClassName, t.code as pool_name " +
            " from c_datasource_config dc INNER JOIN c_tenant_datasource_config tdc on dc.id = tdc.datasource_config_id " +
            "INNER JOIN c_tenant t on t.id = tdc.tenant_id " +
            "where tdc.application = #{applicationName} " +
            "and t.`status` = #{status} and t.connect_type = #{connectType} ")
    List<DatasourceConfig> listByApplication(@Param("applicationName") String applicationName, @Param("status") String status,
            @Param("connectType") String connectType);               
}

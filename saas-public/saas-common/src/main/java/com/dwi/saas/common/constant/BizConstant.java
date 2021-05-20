package com.dwi.saas.common.constant;

/**
 * 业务常量
 *
 * @author dwi
 * @date 2020/08/06
 */
/**
 * @author admin
 *
 */
public interface BizConstant {
    /**
     * 工具类 需要扫描的包
     */
    String UTIL_PACKAGE = "com.dwi.basic";
    /**
     * 业务项目 需要扫描的包
     */
    String BUSINESS_PACKAGE = "com.dwi.saas";
    /**
     * 超级租户编码
     */
    String SUPER_TENANT = "admin";
    /**
     * 初始化的租户管理员角色
     */
    String INIT_ROLE_CODE = "SUPER_ADMIN";

    /**
     * 演示用的组织ID
     */
    Long DEMO_ORG_ID = 101L;
    /**
     * 角色前缀
     */
    String ROLE_PREFIX = "ROLE_";
    /**
     * 演示用的岗位ID
     */
    Long DEMO_STATION_ID = 101L;

    /**
     * 默认密码：123456
     */
    String DEF_PASSWORD = "123456";

    /**
     * 基础库
     */
    String BASE_DATABASE = "saas_base";
    /**
     * 扩展库
     */
    String EXTEND_DATABASE = "saas_extend";

    /**
     * 被T
     */
    String LOGIN_STATUS = "T";   
}

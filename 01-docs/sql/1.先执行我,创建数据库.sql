/*
saas_nacos、saas_seata、saas_sw、saas_zipkin是第三方组件的库， 分别为：nacos、seata、SkyWalking、zipkin 服务端需要的库

saas_none、saas_activiti、saas_column、saas_defaults、saas_base_0000、saas_extend_0000 是saas-cloud项目需要的库
 */
-- none 模式
CREATE DATABASE IF NOT EXISTS `saas_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `saas_none` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- column 模式
CREATE DATABASE IF NOT EXISTS `saas_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `saas_column` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_activiti` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

-- schema 模式、datasource模式 （暂不支持工作流）
CREATE DATABASE IF NOT EXISTS `saas_nacos` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_seata` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_zipkin` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_sw` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;

CREATE DATABASE IF NOT EXISTS `saas_defaults` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_base_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;
CREATE DATABASE IF NOT EXISTS `saas_extend_0000` CHARACTER SET utf8mb4 COLLATE utf8mb4_general_ci;



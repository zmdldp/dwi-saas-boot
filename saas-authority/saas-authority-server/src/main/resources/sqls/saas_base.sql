/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : 127.0.0.1:3306
 Source Schema         : saas_base_0000

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 28/11/2020 22:52:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for c_application
-- ----------------------------
DROP TABLE IF EXISTS `c_application`;
CREATE TABLE `c_application` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `client_id` varchar(24) DEFAULT '' COMMENT '客户端ID',
  `client_secret` varchar(32) DEFAULT '' COMMENT '客户端密码',
  `website` varchar(100) DEFAULT '' COMMENT '官网',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '应用名称',
  `icon` varchar(255) DEFAULT '' COMMENT '应用图标',
  `app_type` varchar(10) DEFAULT '' COMMENT '类型 \n#{SERVER:服务应用;APP:手机应用;PC:PC网页应用;WAP:手机网页应用}',
  `describe_` varchar(200) DEFAULT '' COMMENT '备注',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_client_id` (`client_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='应用';

-- ----------------------------
-- Table structure for c_area
-- ----------------------------
DROP TABLE IF EXISTS `c_area`;
CREATE TABLE `c_area` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `label` varchar(255) NOT NULL COMMENT '名称',
  `full_name` varchar(255) DEFAULT '' COMMENT '全名',
  `sort_value` int(10) DEFAULT '1' COMMENT '排序',
  `longitude` varchar(255) DEFAULT '' COMMENT '经度',
  `latitude` varchar(255) DEFAULT '' COMMENT '维度',
  `level` varchar(10) DEFAULT '' COMMENT '行政区级 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.AREA_LEVEL) RemoteData<String, String>',
  `source_` varchar(255) DEFAULT '' COMMENT '数据来源',
  `state` bit(1) DEFAULT b'0' COMMENT '状态',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='地区表';

-- ----------------------------
-- Table structure for c_attachment
-- ----------------------------
DROP TABLE IF EXISTS `c_attachment`;
CREATE TABLE `c_attachment` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT NULL COMMENT '业务ID',
  `biz_type` varchar(255) DEFAULT '' COMMENT '业务类型 \n#AttachmentType',
  `data_type` varchar(255) DEFAULT '' COMMENT '数据类型 \n#DataType{DIR:目录;IMAGE:图片;VIDEO:视频;AUDIO:音频;DOC:文档;OTHER:其他}',
  `submitted_file_name` varchar(255) DEFAULT '' COMMENT '原始文件名',
  `group_` varchar(255) DEFAULT '' COMMENT 'FastDFS返回的组\n用于FastDFS',
  `path` varchar(255) DEFAULT '' COMMENT 'FastDFS的远程文件名\n用于FastDFS',
  `relative_path` varchar(255) DEFAULT '' COMMENT '文件相对路径',
  `url` varchar(255) DEFAULT '' COMMENT '文件访问链接\n需要通过nginx配置路由，才能访问',
  `file_md5` varchar(255) DEFAULT '' COMMENT '文件md5值',
  `context_type` varchar(255) DEFAULT '' COMMENT '文件上传类型\n取上传文件的值',
  `filename` varchar(255) DEFAULT '' COMMENT '唯一文件名',
  `ext` varchar(64) DEFAULT '' COMMENT '后缀\n (没有.)',
  `size` bigint(20) DEFAULT '0' COMMENT '大小',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID \n#c_org',
  `icon` varchar(64) DEFAULT '' COMMENT '图标',
  `create_month` char(8) DEFAULT '' COMMENT '创建年月 \n格式：yyyy年MM周 用于统计',
  `create_week` char(8) DEFAULT '' COMMENT '创建时处于当年的第几周 \n格式：yyyy年ww周 用于统计',
  `create_day` char(11) DEFAULT '' COMMENT '创建年月日 \n格式： yyyy年MM月dd日',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='附件';

-- ----------------------------
-- Table structure for c_dictionary
-- ----------------------------
DROP TABLE IF EXISTS `c_dictionary`;
CREATE TABLE `c_dictionary` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `type` varchar(255) NOT NULL COMMENT '类型',
  `label` varchar(255) NOT NULL DEFAULT '' COMMENT '类型标签',
  `code` varchar(64) NOT NULL COMMENT '编码',
  `name` varchar(64) NOT NULL COMMENT '名称',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `sort_value` int(10) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '图标',
  `css_style` varchar(255) DEFAULT '' COMMENT 'css样式',
  `css_class` varchar(255) DEFAULT '' COMMENT 'css class',
  `readonly_` bit(1) DEFAULT b'0' COMMENT '内置',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_type_code` (`type`,`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典项';

-- ----------------------------
-- Table structure for c_login_log
-- ----------------------------
DROP TABLE IF EXISTS `c_login_log`;
CREATE TABLE `c_login_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) DEFAULT '' COMMENT '登录IP',
  `user_id` bigint(20) DEFAULT NULL COMMENT '登录人ID',
  `user_name` varchar(50) DEFAULT '' COMMENT '登录人姓名',
  `account` varchar(30) DEFAULT '' COMMENT '登录人账号',
  `description` varchar(255) DEFAULT '' COMMENT '登录描述',
  `login_date` char(10) DEFAULT '' COMMENT '登录时间',
  `ua` varchar(500) DEFAULT '' COMMENT '浏览器请求头',
  `browser` varchar(255) DEFAULT '' COMMENT '浏览器名称',
  `browser_version` varchar(255) DEFAULT '' COMMENT '浏览器版本',
  `operating_system` varchar(255) DEFAULT '' COMMENT '操作系统',
  `location` varchar(50) DEFAULT '' COMMENT '登录地点',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='登录日志';

-- ----------------------------
-- Table structure for c_menu
-- ----------------------------
DROP TABLE IF EXISTS `c_menu`;
CREATE TABLE `c_menu` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `label` varchar(20) NOT NULL DEFAULT '' COMMENT '名称',
  `describe_` varchar(200) DEFAULT '' COMMENT '描述',
  `is_general` bit(1) DEFAULT b'0' COMMENT '通用菜单 \nTrue表示无需分配所有人就可以访问的',
  `path` varchar(255) DEFAULT '' COMMENT '路径',
  `component` varchar(255) DEFAULT '' COMMENT '组件',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `sort_value` int(10) DEFAULT '1' COMMENT '排序',
  `icon` varchar(255) DEFAULT '' COMMENT '菜单图标',
  `group_` varchar(20) DEFAULT '' COMMENT '分组',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父级菜单ID',
  `readonly_` bit(1) DEFAULT b'0' COMMENT '内置',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_path` (`path`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='菜单';

-- ----------------------------
-- Table structure for c_opt_log
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log`;
CREATE TABLE `c_opt_log` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `request_ip` varchar(50) DEFAULT '' COMMENT '操作IP',
  `type` varchar(5) DEFAULT '' COMMENT '日志类型 \n#LogType{OPT:操作类型;EX:异常类型}',
  `user_name` varchar(50) DEFAULT '' COMMENT '操作人',
  `description` varchar(255) DEFAULT '' COMMENT '操作描述',
  `class_path` varchar(255) DEFAULT '' COMMENT '类路径',
  `action_method` varchar(50) DEFAULT '' COMMENT '请求方法',
  `request_uri` varchar(50) DEFAULT '' COMMENT '请求地址',
  `http_method` varchar(10) DEFAULT '' COMMENT '请求类型 \n#HttpMethod{GET:GET请求;POST:POST请求;PUT:PUT请求;DELETE:DELETE请求;PATCH:PATCH请求;TRACE:TRACE请求;HEAD:HEAD请求;OPTIONS:OPTIONS请求;}',
  `start_time` timestamp NULL DEFAULT NULL COMMENT '开始时间',
  `finish_time` timestamp NULL DEFAULT NULL COMMENT '完成时间',
  `consuming_time` bigint(20) DEFAULT NULL COMMENT '消耗时间',
  `ua` varchar(500) DEFAULT '' COMMENT '浏览器',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志';

-- ----------------------------
-- Table structure for c_opt_log_ext
-- ----------------------------
DROP TABLE IF EXISTS `c_opt_log_ext`;
CREATE TABLE `c_opt_log_ext` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `params` longtext COMMENT '请求参数',
  `result` longtext COMMENT '返回值',
  `ex_detail` longtext COMMENT '异常描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志扩展';

-- ----------------------------
-- Table structure for c_org
-- ----------------------------
DROP TABLE IF EXISTS `c_org`;
CREATE TABLE `c_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `label` varchar(255) NOT NULL COMMENT '名称',
  `type_` char(2) DEFAULT '' COMMENT '类型 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.ORG_TYPE) RemoteData<String, String>',
  `abbreviation` varchar(255) DEFAULT '' COMMENT '简称',
  `parent_id` bigint(20) DEFAULT '0' COMMENT '父ID',
  `tree_path` varchar(255) DEFAULT '' COMMENT '树结构',
  `sort_value` int(10) DEFAULT '1' COMMENT '排序',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE,
  FULLTEXT KEY `fu_path` (`tree_path`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='组织';

-- ----------------------------
-- Table structure for c_parameter
-- ----------------------------
DROP TABLE IF EXISTS `c_parameter`;
CREATE TABLE `c_parameter` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `key_` varchar(255) NOT NULL COMMENT '参数键',
  `value` varchar(255) NOT NULL COMMENT '参数值',
  `name` varchar(255) NOT NULL COMMENT '参数名称',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly_` bit(1) DEFAULT b'0' COMMENT '内置',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_key` (`key_`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='参数配置';

-- ----------------------------
-- Table structure for c_resource
-- ----------------------------
DROP TABLE IF EXISTS `c_resource`;
CREATE TABLE `c_resource` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `code` varchar(500) DEFAULT '' COMMENT '编码',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `menu_id` bigint(20) DEFAULT NULL COMMENT '菜单ID \n#c_menu',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `readonly_` bit(1) DEFAULT b'1' COMMENT '内置',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='资源';

-- ----------------------------
-- Table structure for c_role
-- ----------------------------
DROP TABLE IF EXISTS `c_role`;
CREATE TABLE `c_role` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(30) NOT NULL DEFAULT '' COMMENT '名称',
  `code` varchar(20) DEFAULT '' COMMENT '编码',
  `describe_` varchar(100) DEFAULT '' COMMENT '描述',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `readonly_` bit(1) DEFAULT b'0' COMMENT '内置角色',
  `ds_type` varchar(20) NOT NULL DEFAULT '' COMMENT '数据权限 \n#DataScopeType{ALL:1,全部;THIS_LEVEL:2,本级;THIS_LEVEL_CHILDREN:3,本级以及子级;CUSTOMIZE:4,自定义;SELF:5,个人;}',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_code` (`code`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色';

-- ----------------------------
-- Table structure for c_role_authority
-- ----------------------------
DROP TABLE IF EXISTS `c_role_authority`;
CREATE TABLE `c_role_authority` (
  `id` bigint(20) NOT NULL COMMENT '主键',
  `authority_id` bigint(20) NOT NULL COMMENT '资源id \n#c_resource #c_menu',
  `authority_type` varchar(10) NOT NULL COMMENT '权限类型 \n#AuthorizeType{MENU:菜单;RESOURCE:资源;}',
  `role_id` bigint(20) NOT NULL COMMENT '角色id \n#c_role',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_authority` (`authority_id`,`authority_type`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色的资源';

-- ----------------------------
-- Table structure for c_role_org
-- ----------------------------
DROP TABLE IF EXISTS `c_role_org`;
CREATE TABLE `c_role_org` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID \n#c_role',
  `org_id` bigint(20) NOT NULL COMMENT '部门ID \n#c_org',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_role_org` (`org_id`,`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色组织关系';

-- ----------------------------
-- Table structure for c_station
-- ----------------------------
DROP TABLE IF EXISTS `c_station`;
CREATE TABLE `c_station` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) NOT NULL DEFAULT '' COMMENT '名称',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID \n#c_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.dwi.saas.authority.entity.core.Org>',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `describe_` varchar(255) DEFAULT '' COMMENT '描述',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='岗位';

-- ----------------------------
-- Table structure for c_user
-- ----------------------------
DROP TABLE IF EXISTS `c_user`;
CREATE TABLE `c_user` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `account` varchar(30) NOT NULL DEFAULT '' COMMENT '账号',
  `name` varchar(50) NOT NULL DEFAULT '' COMMENT '姓名',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID \n#c_org\n@InjectionField(api = ORG_ID_CLASS, method = ORG_ID_METHOD, beanClass = Org.class) RemoteData<Long, com.dwi.saas.authority.entity.core.Org>',
  `station_id` bigint(20) DEFAULT NULL COMMENT '岗位ID \n#c_station\n@InjectionField(api = STATION_ID_CLASS, method = STATION_ID_NAME_METHOD) RemoteData<Long, String>',
  `readonly` bit(1) NOT NULL DEFAULT b'0' COMMENT '内置',
  `email` varchar(255) DEFAULT '' COMMENT '邮箱',
  `mobile` varchar(20) DEFAULT '' COMMENT '手机',
  `sex` varchar(1) DEFAULT '' COMMENT '性别 \n#Sex{W:女;M:男;N:未知}',
  `state` bit(1) DEFAULT b'1' COMMENT '状态',
  `avatar` varchar(255) DEFAULT '' COMMENT '头像',
  `nation` char(2) DEFAULT '' COMMENT '民族 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>',
  `education` char(2) DEFAULT '' COMMENT '学历 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>',
  `position_status` char(2) DEFAULT '' COMMENT '职位状态 \n@InjectionField(api = DICTIONARY_ITEM_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.POSITION_STATUS) RemoteData<String, String>',
  `work_describe` varchar(255) DEFAULT '' COMMENT '工作描述',
  `password_error_last_time` datetime DEFAULT NULL COMMENT '最后一次输错密码时间',
  `password_error_num` int(10) DEFAULT '0' COMMENT '密码错误次数',
  `password_expire_time` datetime DEFAULT NULL COMMENT '密码过期时间',
  `password` varchar(64) NOT NULL DEFAULT '' COMMENT '密码',
  `salt` varchar(20) NOT NULL DEFAULT '' COMMENT '盐',
  `last_login_time` datetime DEFAULT NULL COMMENT '最后登录时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '更新人id',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_account` (`account`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户';

-- ----------------------------
-- Table structure for c_user_role
-- ----------------------------
DROP TABLE IF EXISTS `c_user_role`;
CREATE TABLE `c_user_role` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `role_id` bigint(20) NOT NULL COMMENT '角色ID \n#c_role',
  `user_id` bigint(20) NOT NULL COMMENT '用户ID \n#c_user',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_user_role` (`role_id`,`user_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色分配\n账号角色绑定';



-- ----------------------------
-- Table structure for b_order
-- ----------------------------
DROP TABLE IF EXISTS `b_order`;
CREATE TABLE `b_order` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(255) DEFAULT NULL COMMENT '名称',
  `education` varchar(255) DEFAULT NULL COMMENT '学历 \n@InjectionField(api = "orderServiceImpl", method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>',
  `nation` varchar(255) DEFAULT NULL COMMENT '民族 \n@InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.NATION) RemoteData<String, String>',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织ID \n#c_org@InjectionField(api = ORG_ID_FEIGN_CLASS, method = ORG_ID_NAME_METHOD) RemoteData<Long, String>',
  `code` varchar(255) DEFAULT NULL COMMENT '编号',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='订单';

-- ----------------------------
-- Table structure for b_product
-- ----------------------------
DROP TABLE IF EXISTS `b_product`;
CREATE TABLE `b_product` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `name` varchar(24) DEFAULT NULL COMMENT '名称',
  `stock` int(10) DEFAULT NULL COMMENT '库存',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  `type_` text COMMENT '商品类型 \n#ProductType{ordinary:普通;gift:赠品}',
  `type2` longtext COMMENT '商品类型2 \n#{ordinary:普通;gift:赠品;}',
  `type3` varchar(255) DEFAULT NULL COMMENT '学历 \n@InjectionField(api = DICTIONARY_ITEM_FEIGN_CLASS, method = DICTIONARY_ITEM_METHOD, dictType = DictionaryType.EDUCATION) RemoteData<String, String>',
  `state` bit(1) DEFAULT NULL COMMENT '状态',
  `test4` tinyint(3) DEFAULT NULL COMMENT '测试',
  `test5` date DEFAULT NULL COMMENT '时间',
  `test6` datetime DEFAULT NULL COMMENT '日期',
  `parent_id` bigint(20) DEFAULT NULL COMMENT '父id',
  `label` varchar(255) DEFAULT NULL COMMENT '名称',
  `sort_value` int(10) DEFAULT NULL COMMENT '排序',
  `test7` char(10) DEFAULT NULL COMMENT '测试字段 \n@InjectionField(api = "userApi", method = USER_ID_NAME_METHOD) RemoteData<Long, String>',
  `user_id` bigint(20) DEFAULT NULL COMMENT '用户 \n@InjectionField(api = USER_ID_FEIGN_CLASS, method = USER_ID_NAME_METHOD) RemoteData<Long, String>',
  `org_id` bigint(20) DEFAULT NULL COMMENT '组织 \n@InjectionField(api = ORG_ID_FEIGN_CLASS, method = "findOrgNameByIds") RemoteData<Long, String>',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='商品';

-- ----------------------------
-- Table structure for e_block_list
-- ----------------------------
DROP TABLE IF EXISTS `e_block_list`;
CREATE TABLE `e_block_list` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `ip` varchar(20) DEFAULT '' COMMENT '阻止访问ip',
  `request_uri` varchar(255) DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方法 \n如果为ALL则表示对所有方法生效',
  `limit_start` varchar(8) DEFAULT '' COMMENT '限制时间起',
  `limit_end` varchar(8) DEFAULT '' COMMENT '限制时间止',
  `state` bit(1) DEFAULT b'0' COMMENT '状态',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='阻止访问';

-- ----------------------------
-- Table structure for e_msg
-- ----------------------------
DROP TABLE IF EXISTS `e_msg`;
CREATE TABLE `e_msg` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `biz_id` varchar(64) DEFAULT '' COMMENT '业务ID',
  `biz_type` varchar(64) DEFAULT '' COMMENT '业务类型 \n#MsgBizType{USER_LOCK:账号锁定;USER_REG:账号申请;WORK_APPROVAL:考勤审批;}',
  `msg_type` varchar(20) NOT NULL COMMENT '消息类型 \n#MsgType{WAIT:待办;NOTIFY:通知;PUBLICITY:公告;WARN:预警;}',
  `title` varchar(255) DEFAULT '' COMMENT '标题',
  `content` text COMMENT '内容',
  `author` varchar(50) DEFAULT '' COMMENT '发布人',
  `handler_url` varchar(255) DEFAULT '' COMMENT '处理地址 \n以http开头时直接跳转，否则与#c_application表拼接后跳转http可带参数',
  `handler_params` varchar(500) DEFAULT '' COMMENT '处理参数',
  `is_single_handle` bit(1) DEFAULT b'1' COMMENT '是否单人处理',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人id',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息表';

-- ----------------------------
-- Table structure for e_msg_receive
-- ----------------------------
DROP TABLE IF EXISTS `e_msg_receive`;
CREATE TABLE `e_msg_receive` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `msg_id` bigint(20) NOT NULL COMMENT '消息ID \n#msg',
  `user_id` bigint(20) NOT NULL COMMENT '接收人ID \n#c_user',
  `is_read` bit(1) DEFAULT b'0' COMMENT '是否已读',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='消息接收表';

-- ----------------------------
-- Table structure for e_rate_limiter
-- ----------------------------
DROP TABLE IF EXISTS `e_rate_limiter`;
CREATE TABLE `e_rate_limiter` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `count` int(10) DEFAULT '0' COMMENT '次数',
  `request_uri` varchar(255) DEFAULT '' COMMENT '请求URI',
  `request_method` varchar(10) DEFAULT '' COMMENT '请求方法 \n如果为ALL则表示对所有方法生效',
  `limit_start` varchar(8) DEFAULT '' COMMENT '限制时间起',
  `limit_end` varchar(8) DEFAULT '' COMMENT '限制时间止',
  `state` bit(1) DEFAULT b'0' COMMENT '状态',
  `interval_sec` bigint(20) DEFAULT '0' COMMENT '时间窗口',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `update_time` datetime DEFAULT NULL COMMENT '修改时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '修改人',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='限流';

-- ----------------------------
-- Table structure for e_sms_send_status
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_send_status`;
CREATE TABLE `e_sms_send_status` (
  `id` bigint(20) NOT NULL COMMENT 'ID',
  `task_id` bigint(20) NOT NULL COMMENT '任务ID \n#e_sms_task',
  `send_status` varchar(10) NOT NULL COMMENT '发送状态 \n#SendStatus{WAITING:等待发送;SUCCESS:发送成功;FAIL:发送失败}',
  `receiver` varchar(20) NOT NULL COMMENT '接收者手机号\n单个手机号 \n阿里：发送回执ID,可根据该ID查询具体的发送状态  腾讯：sid 标识本次发送id，标识一次短信下发记录  百度：requestId 短信发送请求唯一流水ID',
  `biz_id` varchar(255) DEFAULT '' COMMENT '发送回执ID',
  `ext` varchar(255) DEFAULT '' COMMENT '发送返回 \n阿里：RequestId 请求ID  腾讯：ext：用户的session内容，腾讯server回包中会原样返回   百度：无',
  `code` varchar(255) DEFAULT '' COMMENT '状态码 \n阿里：返回OK代表请求成功,其他错误码详见错误码列表  腾讯：0表示成功(计费依据)，非0表示失败  百度：1000 表示成功',
  `message` varchar(500) DEFAULT '' COMMENT '状态码的描述',
  `fee` int(10) DEFAULT NULL COMMENT '短信计费的条数\n腾讯专用',
  `create_month` char(7) DEFAULT '' COMMENT '创建时年月 \n格式：yyyy-MM 用于统计',
  `create_week` char(7) DEFAULT '' COMMENT '创建时年周 \n创建时处于当年的第几周 yyyy-ww 用于统计',
  `create_date` char(10) DEFAULT '' COMMENT '创建时年月日 \n格式： yyyy-MM-dd 用于统计',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信发送状态';

-- ----------------------------
-- Table structure for e_sms_task
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_task`;
CREATE TABLE `e_sms_task` (
  `id` bigint(20) NOT NULL COMMENT '短信记录ID',
  `template_id` bigint(20) NOT NULL COMMENT '模板ID \n#e_sms_template',
  `status` varchar(10) DEFAULT '' COMMENT '执行状态 \n(手机号具体发送状态看sms_send_status表) \n#TaskStatus{WAITING:等待执行;SUCCESS:执行成功;FAIL:执行失败}',
  `source_type` varchar(10) DEFAULT '' COMMENT '来源类型 \n#SourceType{APP:应用;SERVICE:服务}',
  `receiver` text COMMENT '接收者手机号 \n群发用英文逗号分割.\n支持2种 格式:1: 手机号,手机号  格式2: 姓名<手机号>,姓名<手机号>',
  `topic` varchar(255) DEFAULT '' COMMENT '主题',
  `template_params` varchar(500) DEFAULT '' COMMENT '参数 \n需要封装为{‘key’:’value’, ...}格式且key必须有序',
  `send_time` datetime DEFAULT NULL COMMENT '发送时间',
  `content` varchar(500) DEFAULT '' COMMENT '发送内容 \n需要封装正确格式化: 您好，张三，您有一个新的快递。',
  `draft` bit(1) DEFAULT b'0' COMMENT '是否草稿',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='发送任务';

-- ----------------------------
-- Table structure for e_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `e_sms_template`;
CREATE TABLE `e_sms_template` (
  `id` bigint(20) NOT NULL COMMENT '模板ID',
  `provider_type` varchar(10) NOT NULL DEFAULT '' COMMENT '供应商类型 \n#ProviderType{ALI:OK,阿里云短信;TENCENT:0,腾讯云短信;BAIDU:1000,百度云短信}',
  `app_id` varchar(255) NOT NULL DEFAULT '' COMMENT '应用ID',
  `app_secret` varchar(255) NOT NULL DEFAULT '' COMMENT '应用密码',
  `url` varchar(255) DEFAULT '' COMMENT 'SMS服务域名 \n百度、其他厂商会用',
  `custom_code` varchar(20) NOT NULL DEFAULT '' COMMENT '模板编码\n用于api发送',
  `name` varchar(255) DEFAULT '' COMMENT '模板名称',
  `content` varchar(255) NOT NULL DEFAULT '' COMMENT '模板内容',
  `template_params` varchar(255) NOT NULL DEFAULT '' COMMENT '模板参数',
  `template_code` varchar(50) NOT NULL DEFAULT '' COMMENT '模板CODE',
  `sign_name` varchar(100) DEFAULT '' COMMENT '签名',
  `template_describe` varchar(255) DEFAULT '' COMMENT '备注',
  `created_by` bigint(20) DEFAULT NULL COMMENT '创建人ID',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `updated_by` bigint(20) DEFAULT NULL COMMENT '最后修改人',
  `update_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='短信模板';

-- ----------------------------
-- Table structure for undo_log
-- ----------------------------
DROP TABLE IF EXISTS `undo_log`;
CREATE TABLE IF NOT EXISTS `undo_log`
(
    `id`            BIGINT(20)   NOT NULL AUTO_INCREMENT COMMENT 'increment id',
    `branch_id`     BIGINT(20)   NOT NULL COMMENT 'branch transaction id',
    `xid`           VARCHAR(100) NOT NULL COMMENT 'global transaction id',
    `context`       VARCHAR(128) NOT NULL COMMENT 'undo_log context,such as serialization',
    `rollback_info` LONGBLOB     NOT NULL COMMENT 'rollback info',
    `log_status`    INT(11)      NOT NULL COMMENT '0:normal status,1:defense status',
    `log_created`   DATETIME(6)  NOT NULL COMMENT 'create datetime',
    `log_modified`  DATETIME(6)  NOT NULL COMMENT 'modify datetime',
    PRIMARY KEY (`id`),
    UNIQUE KEY `ux_undo_log` (`xid`, `branch_id`)
) ENGINE = InnoDB
  AUTO_INCREMENT = 1
  DEFAULT CHARSET = utf8 COMMENT ='AT transaction mode undo table';

SET FOREIGN_KEY_CHECKS = 1;

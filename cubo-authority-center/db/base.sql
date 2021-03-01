-- 用户表
-- 角色表
-- 用户角色关系表
-- 权限表
-- 角色权限表
-- 登陆信息表


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(255) DEFAULT NULL COMMENT '账号',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `name` varchar(100) DEFAULT NULL COMMENT '用户姓名',
  `phone` varchar(20) DEFAULT NULL COMMENT '手机号码',
  `role_id` bigint(20) DEFAULT NULL COMMENT '角色id',
  `is_admin` tinyint(1) DEFAULT '0' COMMENT '是否管理员（0 否、1 是）',
  `status` tinyint(4) DEFAULT NULL COMMENT '状态：0正常、1停用、2删除',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`) USING BTREE,
  UNIQUE KEY `uk_idx_user` (`user_name`),
  KEY `idx` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

CREATE TABLE `role` (
  `role_id` bigint(20) unsigned NOT NULL COMMENT '主键',
  `role_name` varchar(30) NOT NULL COMMENT '角色名称',
  `role_type` tinyint(3) NOT NULL COMMENT '角色类型（0 管理端、1 客户端）',
  `role_key` varchar(100) NOT NULL COMMENT '角色权限字符串',
  `role_sort` int(11) DEFAULT '1' COMMENT '显示顺序',
  `data_scope` char(1) DEFAULT '1' COMMENT '数据范围',
  `status` char(1) DEFAULT '1' COMMENT '角色状态（0正常 1停用 2删除）',
  `create_by` varchar(64) DEFAULT '' COMMENT '创建者',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `update_by` varchar(64) DEFAULT '' COMMENT '更新者',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  `remark` varchar(500) DEFAULT '' COMMENT '备注',
  PRIMARY KEY (`role_id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 ROW_FORMAT=COMPACT COMMENT='角色表';
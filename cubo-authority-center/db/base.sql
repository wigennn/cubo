-- 用户表
drop table if exists `user`;
create table `user`(
  `id` bigint(20) NOT NULL COMMENT '主键',
  `account` varchar(150) CHARACTER SET utf8 DEFAULT '' COMMENT '账号',
  `name` varchar(60) CHARACTER SET utf8 DEFAULT '' COMMENT '姓名',
  `password` varchar(32) CHARACTER SET utf8 NOT NULL DEFAULT '0' COMMENT '密码',
  `salt` varchar(10) DEFAULT NULL COMMENT '盐',
  `phone` varchar(16) CHARACTER SET utf8 NOT NULL DEFAULT '' COMMENT '手机号',
  `email` varchar(60) CHARACTER SET utf8 DEFAULT '' COMMENT '邮箱',
  `photo` varchar(360) CHARACTER SET utf8 DEFAULT '' COMMENT '头像地址',
  `gender` smallint(1) DEFAULT '2' COMMENT '性别，1：男；0：女；2:保密；',
  `status` smallint(1) DEFAULT '0' COMMENT '0:正常，1:删除，2:禁用',
  `creator` bigint(20) DEFAULT '0' COMMENT '创建人',
  `modifier` bigint(20) DEFAULT '0' COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY(`id`),
  UNIQUE KEY `uk_phone` (`phone`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '用户表';

-- 角色表
drop table if exists `role`;
create table `role`(
  `id` bigint(20) NOT NULL COMMENT '主键',
  `name` varchar(60) CHARACTER SET utf8 DEFAULT '' COMMENT '名称',
  `creator` bigint(20) DEFAULT '0' COMMENT '创建人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modifier` bigint(20) DEFAULT '0' COMMENT '修改人',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`)
)ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT '角色表';

-- 用户角色关系表
drop table if exists `user_role`;
create table `user_role`(
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `creator` bigint(20) DEFAULT '0' COMMENT '创建人',
  `modifier` bigint(20) DEFAULT '0' COMMENT '修改人',
  `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `modify_time` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY(`id`),
  UNIQUE KEY `uk_role`(`user_id`,`role_id`)
)ENGINE=INNODB DEFAULT CHARSET=utf8mb4 COMMENT '用户角色表';

-- 权限表
-- 角色权限表

-- 登陆信息表
drop table if exists `login_log`;
create table `login_log`(
  `id` bigint(20) NOT NULL COMMENT '主键',
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `user_name` varchar(60) NOT NULL COMMENT '用户姓名',
  `ip_address` varchar(100) NOT NULL COMMENT '用户IP地址',
  `source` smallint(6) NOT NULL COMMENT '来源',
  `login_time` datetime NOT NULL COMMENT '登录时间',
  `terminal` smallint(6) NOT NULL COMMENT '终端设备',
  `creator` bigint(20) NOT NULL COMMENT '创建人',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `modifier` bigint(20) NOT NULL COMMENT '修改人',
  `modify_time` datetime NOT NULL COMMENT '修改时间',
  `operation_record` varchar(100) NOT NULL COMMENT '操作记录',
  PRIMARY KEY (`id`)
)ENGINE=Innodb DEFAULT CHARSET=utf8mb4 COMMENT '登陆日志表';

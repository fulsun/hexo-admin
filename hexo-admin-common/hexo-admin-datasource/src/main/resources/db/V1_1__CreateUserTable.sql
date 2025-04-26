CREATE TABLE `users`
(
    `id`              bigint unsigned NOT NULL  COMMENT '用户ID（自增主键）',
    `nick_name`       varchar(255) DEFAULT NULL COMMENT '用户昵称',
    `telephone`       varchar(20)  DEFAULT NULL COMMENT '手机号码',
    `password_hash`   varchar(255) DEFAULT NULL COMMENT '密码哈希',
    `state`           varchar(64)  DEFAULT NULL COMMENT '用户状态（ACTIVE，FROZEN）',
    `last_login_time` datetime     DEFAULT NULL COMMENT '最后登录时间',
    `user_role`       varchar(128) DEFAULT NULL COMMENT '用户角色',
    `gmt_create`      datetime NOT NULL COMMENT '创建时间',
    `gmt_modified`    datetime NOT NULL COMMENT '最后更新时间',
    `deleted`         int          DEFAULT NULL COMMENT '是否逻辑删除，0为未删除，非0为已删除',
    `lock_version`    int          DEFAULT NULL COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户信息表'
;

create table article
(
    `id`           bigint unsigned NOT NULL  COMMENT 'ID（主键）',
    `title`        varchar(255) NOT NULL COMMENT '文章标题',
    `content`      text         NOT NULL COMMENT '文章内容',
    `author_id`    bigint unsigned NOT NULL COMMENT '作者ID',
    `category_id`  bigint unsigned NOT NULL COMMENT '分类ID',
    `status`       int          NOT NULL DEFAULT 1 COMMENT '文章状态（1：发布，0：草稿）',
    `gmt_create`   datetime     NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL COMMENT '最后更新时间',
    `deleted`      int                   DEFAULT NULL COMMENT '是否逻辑删除，0为未删除，非0为已删除',
    `lock_version` int                   DEFAULT NULL COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`),
    KEY            `idx_author_id` (`author_id`),
    KEY            `idx_category_id` (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';


create table Category
(
    `id`           bigint unsigned NOT NULL  COMMENT 'ID（主键）',
    `name`         varchar(255) NOT NULL COMMENT '分类名称',
    `description`  text         NOT NULL COMMENT '分类描述',
    `gmt_create`   datetime     NOT NULL COMMENT '创建时间',
    `gmt_modified` datetime     NOT NULL COMMENT '最后更新时间',
    `deleted`      int DEFAULT NULL COMMENT '是否逻辑删除，0为未删除，非0为已删除',
    `lock_version` int DEFAULT NULL COMMENT '乐观锁版本号',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='分类表';
)
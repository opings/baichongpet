CREATE TABLE `article` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `article_id` varchar(32) NOT NULL COMMENT '文章id',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `author` varchar(32) NOT NULL COMMENT '作者',
  `publish_date` datetime NOT NULL COMMENT '发布时间',
  `last_update_date` datetime NOT NULL COMMENT '最后修改时间',
  `category` varchar(32) NOT NULL COMMENT '分类',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章表';

CREATE TABLE `article_extension` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `article_id` varchar(32) NOT NULL COMMENT '文章id',
  `content` text NOT NULL COMMENT '内容',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`article_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='文章扩展表';


CREATE TABLE `label` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `label_name` varchar(32) NOT NULL COMMENT '名称',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签表';


CREATE TABLE `label_relation` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `target_type` varchar(32) NOT NULL COMMENT '目标类型',
  `target_id` varchar(32) NOT NULL COMMENT '目标id',
  `label_id` bigint(20) NOT NULL COMMENT '标签id',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_target_id_type_label_id` (`target_id`,`target_type`,`label_id`),
  KEY `idx_label_id_target_type` (`label_id`,`target_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='标签关联表';

CREATE TABLE `biology_catalogue` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `biology_catalogue_id` varchar(64) NOT NULL COMMENT 'id',
  `title` varchar(32) NOT NULL COMMENT '标题',
  `introduction` varchar(2048) NOT NULL COMMENT '简介',
  `img` varchar(512) DEFAULT NULL COMMENT '图片',
  `content` varchar(2048) NOT NULL COMMENT '正文',
  `category` varchar(32) NOT NULL COMMENT '分类',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_biology_catalogue_id` (`biology_catalogue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生物名录表';


CREATE TABLE `biology_catalogue_extension` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '自增ID',
  `biology_catalogue_id` varchar(64) NOT NULL COMMENT 'id',
  `content` text NOT NULL COMMENT '正文',
  `chinese_name` varchar(32) NOT NULL COMMENT '中文名',
  `alias` varchar(32) DEFAULT NULL COMMENT '别名',
  `kingdom` varchar(32) DEFAULT NULL COMMENT '界',
  `phylum` varchar(32) DEFAULT NULL COMMENT '门',
  `sub_phylum` varchar(32) DEFAULT NULL COMMENT '亚门',
  `biology_class` varchar(32) DEFAULT NULL COMMENT '纲',
  `biology_sub_class` varchar(32) DEFAULT NULL COMMENT '亚纲',
  `orders` varchar(32) DEFAULT NULL COMMENT '目',
  `sub_order` varchar(32) DEFAULT NULL COMMENT '亚目',
  `family` varchar(32) DEFAULT NULL COMMENT '科',
  `sub_family` varchar(32) DEFAULT NULL COMMENT '亚科',
  `race` varchar(32) DEFAULT NULL COMMENT '族',
  `sub_race` varchar(32) DEFAULT NULL COMMENT '亚族',
  `genus` varchar(32) DEFAULT NULL COMMENT '属',
  `sub_genus` varchar(32) DEFAULT NULL COMMENT '亚属',
  `species` varchar(32) DEFAULT NULL COMMENT '种',
  `sub_species` varchar(32) DEFAULT NULL COMMENT '亚种',
  `create_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_dt` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`),
  KEY `idx_article_id` (`biology_catalogue_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='生物名录扩展表';
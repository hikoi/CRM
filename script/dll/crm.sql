CREATE DATABASE `crm` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `wechat` (
  `id` varchar(32) NOT NULL,
  `wxno` varchar(45) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `company_id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信信息表';

CREATE TABLE `wechat_friend` (
  `id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `wxno` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `remarkname` varchar(60) DEFAULT NULL,
  `head_img_url` varchar(255) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_message` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) DEFAULT NULL,
  `wxno` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `content` text,
  `extract` text,
  `type` int(10) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `conversation_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `account` (
  `id` varchar(32) NOT NULL,
  `username` varchar(12) NOT NULL,
  `phone` varchar(15) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `password` varchar(256) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `is_internal` tinyint(1) NOT NULL,
  `is_delete` tinyint(1) NOT NULL DEFAULT '0',
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `delete_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`),
  UNIQUE KEY `phone` (`phone`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `user` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) NOT NULL,
  `nickname` varchar(12) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `head_img_url` varchar(255) DEFAULT NULL,
  `age` tinyint(1) DEFAULT NULL,
  `birthday` date DEFAULT NULL,
  `sex` tinyint(1) DEFAULT '1',
  `autograph` varchar(120) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `im_user` (
  `id` varchar(32) NOT NULL,
  `relation_id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `nickname` varchar(12) NOT NULL,
  `head_img_url` varchar(255) DEFAULT NULL,
  `sig` text NOT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `state` tinyint(1) NOT NULL,
  `is_admin` tinyint(1) default '0' not null,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `permission` (
  `id` varchar(32) NOT NULL,
  `resource_id` varchar(32) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `menu` (
  `id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `url` varchar(256) DEFAULT NULL,
  `parent_id` varchar(32) DEFAULT NULL,
  `is_parent` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `function` (
  `id` varchar(32) NOT NULL,
  `url` varchar(256) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `need_allot` tinyint(4) NOT NULL,
  `method` varchar(10) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `company` (
  `id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone` varchar(30) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `department` (
  `id` varchar(32) NOT NULL,
  `company_id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `position` (
  `id` varchar(32) NOT NULL,
  `department_id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role_permission` (
  `role_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `account_permission` (
  `account_id` varchar(32) NOT NULL,
  `permission_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `account_role` (
  `account_id` varchar(32) NOT NULL,
  `role_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

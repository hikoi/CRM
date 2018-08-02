CREATE DATABASE `crm` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `wechat` (
  `id` varchar(32) NOT NULL,
  `wxno` varchar(45) NOT NULL,
  `nickname` varchar(100) NOT NULL,
  `company_id` varchar(32) NOT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='微信信息表';

CREATE TABLE `wechat_friend` (
  `id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `seller_id` varchar(32) DEFAULT NULL,
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
  `seller_id` varchar(32) DEFAULT NULL,
  `wechat_id` varchar(32) NOT NULL,
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
  `is_delete` tinyint(1) NOT NULL,
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
  `nickname` varchar(30) DEFAULT NULL,
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
  `sdk_app_id` varchar(30) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `role` (
  `id` varchar(32) NOT NULL,
  `name` varchar(60) DEFAULT NULL,
  `state` tinyint(1) NOT NULL,
  `company_id` varchar(32) DEFAULT NULL,
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
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `function` (
  `id` varchar(32) NOT NULL,
  `url` varchar(256) NOT NULL,
  `description` varchar(60) DEFAULT NULL,
  `need_allot` tinyint(1) NOT NULL DEFAULT '1',
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

CREATE TABLE `groups` (
  `id` varchar(32) NOT NULL,
  `name` varchar(32) DEFAULT NULL,
  `state` tinyint(1) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `company_id` varchar(32) DEFAULT NULL,
  `is_default` tinyint(1) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `word` (
  `id` varchar(32) NOT NULL,
  `content` varchar(30) NOT NULL,
  `state` tinyint(1) NOT NULL,
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

CREATE TABLE `call_record` (
  `call_id` varchar(64) NOT NULL,
  `app_id` varchar(32) DEFAULT NULL,
  `caller` varchar(11) DEFAULT NULL,
  `caller_name` varchar(32) DEFAULT NULL,
  `called` varchar(11) DEFAULT NULL,
  `called_name` varchar(32) DEFAULT NULL,
  `start_time` datetime DEFAULT NULL,
  `end_time` datetime DEFAULT NULL,
  `total_mins` bigint(20) DEFAULT NULL,
  `record_url` varchar(512) DEFAULT NULL,
  `call_type` varchar(20) DEFAULT NULL,
  `status` varchar(20) DEFAULT NULL,
  `dtmf_type` varchar(128) DEFAULT NULL,
  `data` varchar(32) DEFAULT NULL,
  `caller_id` varchar(32) DEFAULT NULL,
  `called_id` varchar(32) DEFAULT NULL,
  `tc_fail_code` varchar(32) DEFAULT NULL,
  `dtmf_code` varchar(32) DEFAULT NULL,
  PRIMARY KEY (`call_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4

CREATE TABLE `allocation_rule` (
  `id` varchar(32) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `state` tinyint(1) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `online_only` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `allocation_groups_wechat` (
  `rule_id` varchar(32) NOT NULL,
  `groups_wechat_id` varchar(32) NOT NULL,
  `region_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups_word` (
  `groups_id` varchar(32) NOT NULL,
  `word_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups_menu` (
  `groups_id` varchar(32) NOT NULL,
  `menu_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups_seller` (
  `groups_id` varchar(32) NOT NULL,
  `seller_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups_wechat` (
  `groups_id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups_wechat_friend` (
  `groups_id` varchar(32) NOT NULL,
  `friend_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
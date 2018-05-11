CREATE DATABASE `crm` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `wechat_allot_log` (
  `account_id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) DEFAULT NULL,
  `device_id` varchar(32) DEFAULT NULL,
  `wxno` varchar(50) NOT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_friend` (
  `id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `wxno` varchar(50) DEFAULT NULL,
  `nickname` varchar(50) DEFAULT NULL,
  `remarkname` varchar(60) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_message` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) DEFAULT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `content` text,
  `extract` text,
  `type` tinyint(1) NOT NULL,
  `status` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  `conversation_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `device` (
  `id` varchar(32) NOT NULL,
  `phone` varchar(11) DEFAULT NULL,
  `imei` varchar(20) DEFAULT NULL,
  `meid` varchar(20) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sensitive_word` (
  `id` varchar(32) NOT NULL,
  `content` varchar(20) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sensitive_group` (
  `sensitive_id` varchar(32) NOT NULL,
  `group_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `sensitive_wechat` (
  `sensitive_id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `groups` (
  `id` varchar(32) NOT NULL,
  `name` varchar(20) NOT NULL,
  `state` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_group` (
  `wechat_id` varchar(32) NOT NULL,
  `group_id` varchar(32) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




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
  `remark` varchar(60) DEFAULT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_message` (
  `id` varchar(32) NOT NULL,
  `account_id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `content` text,
  `type` tinyint(1) NOT NULL,
  `conversation_time` datetime NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
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




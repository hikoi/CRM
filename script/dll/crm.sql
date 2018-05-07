CREATE DATABASE `crm` CHARACTER SET 'utf8mb4' COLLATE 'utf8mb4_general_ci';

CREATE TABLE `account_wechat` (
  `account_id` varchar(32) NOT NULL,
  `wechat_id` varchar(32) NOT NULL,
  PRIMARY KEY (`account_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat` (
  `id` varchar(32) NOT NULL,
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
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `wechat_message` (
  `id` varchar(32) NOT NULL,
  `wechat_friend_id` varchar(32) NOT NULL,
  `wxid` varchar(50) DEFAULT NULL,
  `text` varchar(256) DEFAULT NULL,
  `type` tinyint(1) NOT NULL,
  `create_time` datetime NOT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;




/*
MySQL - 8.0.23 : Database - personal_project
*********************************************************************
*/

CREATE DATABASE `personal_project`;

USE `personal_project`;

/*Table structure for table `blog` */

DROP TABLE IF EXISTS `blog`;

CREATE TABLE `blog` (
  `id` int NOT NULL AUTO_INCREMENT COMMENT 'blogID',
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '作者',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '标题',
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '内容',
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  PRIMARY KEY (`id`),
  KEY `user_code_ref_user` (`user_code`),
  CONSTRAINT `user_code_ref_user` FOREIGN KEY (`user_code`) REFERENCES `user` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `blog_comment` */

DROP TABLE IF EXISTS `blog_comment`;

CREATE TABLE `blog_comment` (
  `id` int unsigned NOT NULL AUTO_INCREMENT,
  `user_code` varchar(255) CHARACTER SET utf8 NOT NULL,
  `blog_id` int NOT NULL,
  `content` text CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_code_ref_user03` (`user_code`),
  KEY `blog_id_ref_blog02` (`blog_id`),
  CONSTRAINT `blog_id_ref_blog02` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_code_ref_user03` FOREIGN KEY (`user_code`) REFERENCES `user` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `blog_like` */

DROP TABLE IF EXISTS `blog_like`;

CREATE TABLE `blog_like` (
  `user_code` varchar(255) CHARACTER SET utf8 NOT NULL,
  `blog_id` int NOT NULL,
  `create_date` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`user_code`,`blog_id`),
  KEY `blog_id_ref_blog` (`blog_id`),
  CONSTRAINT `blog_id_ref_blog` FOREIGN KEY (`blog_id`) REFERENCES `blog` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_code_ref_user02` FOREIGN KEY (`user_code`) REFERENCES `user` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `resource` */

DROP TABLE IF EXISTS `resource`;

CREATE TABLE `resource` (
  `id` int unsigned NOT NULL AUTO_INCREMENT COMMENT '文件id',
  `file_name` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '文件原名字',
  `file_description` text CHARACTER SET utf8 COMMENT '文件描述',
  `download_sum` int DEFAULT '0',
  `timestamp` varchar(30) CHARACTER SET utf8 NOT NULL COMMENT '上传时间',
  `owner_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '文件所属人',
  PRIMARY KEY (`id`),
  KEY `owner_code_ref_user` (`owner_code`),
  CONSTRAINT `owner_code_ref_user` FOREIGN KEY (`owner_code`) REFERENCES `user` (`user_code`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

/*Table structure for table `user` */

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `user_code` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '唯一识别符',
  `email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户邮箱',
  `username` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '用户名',
  `passwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL COMMENT '密码',
  `level` int NOT NULL DEFAULT '0' COMMENT 'vip级别',
  `avatar_url` varchar(255) CHARACTER SET utf8 NOT NULL COMMENT '头像地址',
  PRIMARY KEY (`user_code`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

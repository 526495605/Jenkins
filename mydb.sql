/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-04-23 23:53:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for child
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `child_name` varchar(15) DEFAULT NULL,
  `child_sex` varchar(15) DEFAULT NULL,
  `child_height` varchar(15) DEFAULT NULL,
  `child_age` int(11) DEFAULT NULL,
  `child_nation` varchar(15) DEFAULT NULL,
  `child_tel` varchar(15) DEFAULT NULL,
  `child_grade` varchar(15) DEFAULT NULL,
  `child_idcard` varchar(15) DEFAULT NULL,
  `child_idcardnum` varchar(15) DEFAULT NULL,
  `child_health` varchar(15) DEFAULT NULL,
  `child_healthinfo` varchar(100) DEFAULT NULL,
  `child_father_name` varchar(15) DEFAULT NULL,
  `child_mother_name` varchar(15) DEFAULT NULL,
  `child_father_tel` varchar(15) DEFAULT NULL,
  `child_mother_tel` varchar(15) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `child_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`child_id`),
  KEY `product_id` (`product_id`),
  KEY `users_id` (`users_id`),
  CONSTRAINT `product_id` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `users_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_name` varchar(20) DEFAULT NULL,
  `users_phone` varchar(15) DEFAULT NULL,
  `users_date` datetime DEFAULT NULL,
  `users_wx` varchar(20) DEFAULT NULL,
  `users_password` varchar(20) DEFAULT NULL,
  `users_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`users_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------

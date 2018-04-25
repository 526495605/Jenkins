/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-25 15:03:21
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
  `child_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`child_id`),
  KEY `users_id` (`users_id`),
  KEY `child_id` (`child_id`,`users_id`),
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
-- Table structure for purchase
-- ----------------------------
DROP TABLE IF EXISTS `purchase`;
CREATE TABLE `purchase` (
  `purchase_id` int(11) NOT NULL,
  `users_id` int(11) DEFAULT NULL,
  `child_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `purchase_time` datetime DEFAULT NULL,
  PRIMARY KEY (`purchase_id`),
  KEY `users_id` (`users_id`,`child_id`),
  KEY `product_id` (`product_id`),
  CONSTRAINT `purchase_ibfk_1` FOREIGN KEY (`users_id`, `child_id`) REFERENCES `child` (`child_id`, `users_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `purchase_ibfk_2` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of purchase
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

/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 50721
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50721
File Encoding         : 65001

Date: 2018-04-27 17:48:05
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for category
-- ----------------------------
DROP TABLE IF EXISTS `category`;
CREATE TABLE `category` (
  `category_id` int(11) NOT NULL AUTO_INCREMENT,
  `category_name` varchar(20) DEFAULT NULL,
  `category_status` int(11) DEFAULT '1',
  PRIMARY KEY (`category_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------

-- ----------------------------
-- Table structure for child
-- ----------------------------
DROP TABLE IF EXISTS `child`;
CREATE TABLE `child` (
  `child_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) NOT NULL,
  `child_name` varchar(15) DEFAULT NULL,
  `child_age` int(11) DEFAULT NULL,
  `child_sex` varchar(15) DEFAULT NULL,
  `child_height` int(11) DEFAULT NULL,
  `child_nation` varchar(15) DEFAULT NULL,
  `child_tel` varchar(15) DEFAULT NULL,
  `child_grade` varchar(15) DEFAULT NULL,
  `child_idcard` varchar(15) DEFAULT NULL,
  `child_idcardnum` varchar(15) DEFAULT NULL,
  `child_health` varchar(15) DEFAULT NULL,
  `child_healthinfo` varchar(100) DEFAULT NULL,
  `child_father_name` varchar(15) DEFAULT NULL,
  `child_father_tel` varchar(15) DEFAULT NULL,
  `child_father_idcard` varchar(15) DEFAULT NULL,
  `child_mother_tel` varchar(15) DEFAULT NULL,
  `child_mother_name` varchar(15) DEFAULT NULL,
  `child_mother_idcard` varchar(15) DEFAULT NULL,
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
-- Table structure for child_category
-- ----------------------------
DROP TABLE IF EXISTS `child_category`;
CREATE TABLE `child_category` (
  `child_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `child_category_status` int(11) DEFAULT '1',
  PRIMARY KEY (`child_category_id`),
  KEY `child_id` (`child_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `child_category_ibfk_1` FOREIGN KEY (`child_id`) REFERENCES `child` (`child_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `child_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child_category
-- ----------------------------

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `product_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_name` varchar(20) DEFAULT NULL,
  `product_from` varchar(20) DEFAULT NULL,
  `product_to` varchar(20) DEFAULT NULL,
  `product_theme` varchar(20) DEFAULT NULL,
  `product_grade` varchar(20) DEFAULT NULL,
  `product_url` varchar(50) DEFAULT NULL,
  `product_money` int(11) DEFAULT NULL,
  `product_time` datetime DEFAULT NULL,
  `product_status` int(11) DEFAULT '1',
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
  `purchase_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) DEFAULT NULL,
  `child_id` int(11) DEFAULT NULL,
  `product_id` int(11) DEFAULT NULL,
  `purchase_time` datetime DEFAULT NULL,
  `users_comment` varchar(255) DEFAULT NULL,
  `users_comment_time` datetime DEFAULT NULL,
  `child_comment` varchar(255) DEFAULT NULL,
  `child_comment_time` datetime DEFAULT NULL,
  `purchase_poster` varchar(100) DEFAULT NULL,
  `product_sharemoney` int(11) DEFAULT NULL,
  `purchase_status` int(11) DEFAULT '1',
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
  `users_password` varchar(50) DEFAULT NULL,
  `users_image` varchar(255) DEFAULT NULL,
  `users_wx` varchar(20) DEFAULT NULL,
  `users_date` datetime DEFAULT NULL,
  `users_point` int(11) NOT NULL,
  `users_share` int(11) NOT NULL,
  `users_parent` int(11) DEFAULT NULL,
  `users_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`users_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'owen', '189797979', '123', null, null, null, '0', '0', '0', '1');

-- ----------------------------
-- Table structure for users_category
-- ----------------------------
DROP TABLE IF EXISTS `users_category`;
CREATE TABLE `users_category` (
  `users_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `users_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `users_category_status` int(11) DEFAULT '1',
  PRIMARY KEY (`users_category_id`),
  KEY `users_id` (`users_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `users_category_ibfk_1` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `users_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_category
-- ----------------------------

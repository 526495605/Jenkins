/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : mydb

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-05-02 22:35:48
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
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of category
-- ----------------------------
INSERT INTO `category` VALUES ('5', null, '0');
INSERT INTO `category` VALUES ('6', '体育', '1');
INSERT INTO `category` VALUES ('7', '体育', '1');
INSERT INTO `category` VALUES ('8', '生物', '1');
INSERT INTO `category` VALUES ('9', '数学', '1');

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
  `child_idcard` varchar(30) DEFAULT NULL,
  `child_idcardnum` varchar(30) DEFAULT NULL,
  `child_health` varchar(15) DEFAULT NULL,
  `child_healthinfo` varchar(100) DEFAULT NULL,
  `child_father_name` varchar(15) DEFAULT NULL,
  `child_father_tel` varchar(15) DEFAULT NULL,
  `child_father_idcard` varchar(30) DEFAULT NULL,
  `child_mother_tel` varchar(15) DEFAULT NULL,
  `child_mother_name` varchar(15) DEFAULT NULL,
  `child_mother_idcard` varchar(30) DEFAULT NULL,
  `child_status` int(11) NOT NULL DEFAULT '1',
  `child_school` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`child_id`),
  KEY `users_id` (`users_id`),
  KEY `child_id` (`child_id`,`users_id`),
  CONSTRAINT `users_id` FOREIGN KEY (`users_id`) REFERENCES `users` (`users_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child
-- ----------------------------
INSERT INTO `child` VALUES ('3', '1', 's4tring', '30', '男', '176', '123', '18636920124', '9854', 'shenfenz', '140108199111271611', 'abc', 'adb', 'owen', '18636920124', '140108199111271611', '18636920124', 'owen', '140108199111271611', '0', '123456');
INSERT INTO `child` VALUES ('4', '1', 's4tring', '50', '男', '176', '123', null, '9854', 'shenfenz', '140108199111271611', 'owennnn', 'adb', 'owen', '18636920124', '140108199111271611', '18636920124', 'owen', '140108199111271611', '1', '123456');
INSERT INTO `child` VALUES ('5', '1', 'string', '30', '男', '176', 'string', '18636920124', '9854', 'shenfenz', '140108199111271611', 'abc', 'adb', 'owen', '18636920124', '140108199111271611', '18636920124', 'owen', '140108199111271611', '1', null);

-- ----------------------------
-- Table structure for child_category
-- ----------------------------
DROP TABLE IF EXISTS `child_category`;
CREATE TABLE `child_category` (
  `child_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `child_id` int(11) NOT NULL,
  `category_id` int(11) NOT NULL,
  `child_category_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`child_category_id`),
  KEY `child_id` (`child_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `child_category_ibfk_1` FOREIGN KEY (`child_id`) REFERENCES `child` (`child_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `child_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of child_category
-- ----------------------------
INSERT INTO `child_category` VALUES ('4', '3', '7', '1');
INSERT INTO `child_category` VALUES ('5', '3', '8', '1');
INSERT INTO `child_category` VALUES ('6', '3', '9', '1');
INSERT INTO `child_category` VALUES ('7', '3', '7', '1');
INSERT INTO `child_category` VALUES ('9', '3', '9', '1');

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
  `product_url` varchar(50) DEFAULT NULL,
  `product_money` int(11) DEFAULT NULL,
  `product_time` bigint(20) DEFAULT NULL,
  `product_status` int(11) DEFAULT '1',
  PRIMARY KEY (`product_id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '1', 'string', 'stri123ng', 'string', 'st123ring', '40', '123123', '1');
INSERT INTO `product` VALUES ('2', 'string', 'string', 'string', 'string', 'string', '0', '1525078135585', '1');

-- ----------------------------
-- Table structure for product_category
-- ----------------------------
DROP TABLE IF EXISTS `product_category`;
CREATE TABLE `product_category` (
  `product_category_id` int(11) NOT NULL AUTO_INCREMENT,
  `product_id` int(11) DEFAULT NULL,
  `category_id` int(11) DEFAULT NULL,
  `product_category_status` int(11) DEFAULT '1',
  PRIMARY KEY (`product_category_id`),
  KEY `product_id` (`product_id`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `product_category_ibfk_2` FOREIGN KEY (`category_id`) REFERENCES `category` (`category_id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `product_category_ibfk_1` FOREIGN KEY (`product_id`) REFERENCES `product` (`product_id`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product_category
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
  `purchase_time` bigint(20) DEFAULT NULL,
  `users_comment` varchar(255) DEFAULT NULL,
  `users_comment_time` bigint(20) DEFAULT NULL,
  `child_comment` varchar(255) DEFAULT NULL,
  `child_comment_time` bigint(20) DEFAULT NULL,
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
  `users_phone` varchar(50) NOT NULL,
  `users_password` varchar(50) DEFAULT NULL,
  `users_image` varchar(255) DEFAULT NULL,
  `users_wx` varchar(20) DEFAULT NULL,
  `users_date` bigint(20) DEFAULT NULL,
  `users_point` int(11) NOT NULL DEFAULT '0',
  `users_parent` int(11) NOT NULL DEFAULT '0',
  `users_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`users_id`),
  UNIQUE KEY `users_phone` (`users_phone`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'owen', '18918501675', '1234', '1', '2', '20180402221041', '0', '1', '1');
INSERT INTO `users` VALUES ('2', null, '18600000001', '202CB962AC59075B964B07152D234B70', null, null, '1525059488235', '0', '0', '0');

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
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users_category
-- ----------------------------

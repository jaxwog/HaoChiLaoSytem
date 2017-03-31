/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50041
Source Host           : localhost:3306
Source Database       : hcl

Target Server Type    : MYSQL
Target Server Version : 50041
File Encoding         : 65001

Date: 2014-12-15 16:40:53
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for book
-- ----------------------------
DROP TABLE IF EXISTS `book`;
CREATE TABLE `book` (
  `bookId` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `phone` varchar(255) default NULL,
  `address` varchar(255) default NULL,
  `busunessname` varchar(255) default NULL,
  `dishName` varchar(255) default NULL,
  `number` int(11) default NULL,
  `price` float default NULL,
  `totalconsumption` float default NULL,
  `bookTime` datetime default NULL,
  `bookFinish` varchar(255) default NULL,
  PRIMARY KEY  (`bookId`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of book
-- ----------------------------
INSERT INTO `book` VALUES ('3', 'wkk', '13673716405', '文化路', '晨风小吃', '刀削面', '1', '8', '16', '2014-12-15 16:09:45', '完成');

-- ----------------------------
-- Table structure for business
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `busunessid` int(11) NOT NULL auto_increment,
  `bususername` varchar(255) default NULL,
  `buspassword` varchar(255) default NULL,
  `busunessname` varchar(255) default NULL,
  `busunessPhone` varchar(255) default NULL,
  `busunessAddress` varchar(255) default NULL,
  PRIMARY KEY  (`busunessid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('1', '吴康康', '111111', '晨风小吃', '3673716', '文化路');
INSERT INTO `business` VALUES ('2', '李泽华', '111111', '谈心小吃', '1108776', '文化路');
INSERT INTO `business` VALUES ('3', '秦鑫', '111111', '谷记快餐', '6567889', '丰产路');
INSERT INTO `business` VALUES ('4', '杨建松', '111111', '侯家小店', '6767670', '红专路');
INSERT INTO `business` VALUES ('5', '王永政', '111111', '速味居', '8877766', '经五路');
INSERT INTO `business` VALUES ('6', '王磊', '111111', '重庆美食', '4444550', '黄河路');
INSERT INTO `business` VALUES ('7', '陈一航', '111111', '莱比克', '3344555', '经三路');
INSERT INTO `business` VALUES ('8', '郝少英', '111111', '赛百味', '5677650', '红旗路');
INSERT INTO `business` VALUES ('9', '王晨光', '111111', '单县羊肉汤', '5577678', '丰产路');
INSERT INTO `business` VALUES ('10', '吕幸凯', '111111', '快餐车汉堡', '6778457', '红专路');
INSERT INTO `business` VALUES ('12', '百岁鱼', '111111', '鱼', '111111', '东三街');

-- ----------------------------
-- Table structure for customer
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `userid` int(11) NOT NULL auto_increment,
  `username` varchar(255) default NULL,
  `password` varchar(255) default NULL,
  `userEmail` varchar(255) default NULL,
  `userPhone` varchar(255) default NULL,
  `userAddress` varchar(255) default NULL,
  PRIMARY KEY  (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('3', 'www', '123', '1281573890@qq.com', '13673716405', '文化路');
INSERT INTO `customer` VALUES ('4', 'wkk', '123', '1281573@qq.com', '13673716405', '文化路');

-- ----------------------------
-- Table structure for dish
-- ----------------------------
DROP TABLE IF EXISTS `dish`;
CREATE TABLE `dish` (
  `dishId` int(11) NOT NULL auto_increment,
  `businessid` int(11) default NULL,
  `dishtype` varchar(255) default NULL,
  `price` float default NULL,
  `dishName` varchar(255) default NULL,
  `dishMessage` varchar(255) default NULL,
  PRIMARY KEY  (`dishId`),
  KEY `FK2081BA63E7CB1D` (`businessid`),
  CONSTRAINT `FK2081BA63E7CB1D` FOREIGN KEY (`businessid`) REFERENCES `business` (`busunessid`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312;

-- ----------------------------
-- Records of dish
-- ----------------------------
INSERT INTO `dish` VALUES ('2', '1', '饭食类', '8', '麻辣豆腐', '辣的过瘾');
INSERT INTO `dish` VALUES ('3', '1', '饭食类', '10', '红烧肉', '肉多味美');
INSERT INTO `dish` VALUES ('4', '1', '饭食类', '10', '宫保鸡丁', '香辣有味');
INSERT INTO `dish` VALUES ('5', '1', '饭食类', '8', '西红柿鸡蛋', '鸡蛋很多');
INSERT INTO `dish` VALUES ('7', '1', '饭食类', '8', '地三鲜', '酱浓味美');
INSERT INTO `dish` VALUES ('8', '1', '饭食类', '8', '红烧茄子', '正宗四川味');
INSERT INTO `dish` VALUES ('9', '1', '饭食类', '10', '青椒肉丝', '好吃');
INSERT INTO `dish` VALUES ('11', '1', '面条类', '8', '刀削面', '辣的过瘾');
INSERT INTO `dish` VALUES ('12', '1', '面条类', '10', '鸡汤面', '肉多味美');
INSERT INTO `dish` VALUES ('13', '1', '面条类', '10', '兰州拉面', '香辣有味');
INSERT INTO `dish` VALUES ('14', '1', '面条类', '8', '茄汁面', '酸的过瘾');
INSERT INTO `dish` VALUES ('16', '1', '面条类', '8', '炸酱面', '酱浓味美');
INSERT INTO `dish` VALUES ('17', '1', '面条类', '8', '担担面', '正宗四川味');
INSERT INTO `dish` VALUES ('18', '1', '面点类', '1', '馒头', '好吃');
INSERT INTO `dish` VALUES ('20', '1', '面点类', '3', '包子', '皮薄馅多');
INSERT INTO `dish` VALUES ('21', '1', '面点类', '2', '鸡蛋饼', '鸡蛋味美');
INSERT INTO `dish` VALUES ('22', '1', '面点类', '3', '鸡肉饼', '香辣有味');
INSERT INTO `dish` VALUES ('25', '2', '面条类', '8', '炸酱面', '酱浓味美');
INSERT INTO `dish` VALUES ('26', '2', '面条类', '8', '担担面', '正宗四川味');
INSERT INTO `dish` VALUES ('27', '2', '面点类', '1', '馒头', '好吃');
INSERT INTO `dish` VALUES ('29', '2', '饭食类', '8', '麻辣豆腐', '辣的过瘾');
INSERT INTO `dish` VALUES ('30', '2', '饭食类', '10', '红烧肉', '肉多味美');
INSERT INTO `dish` VALUES ('31', '2', '饭食类', '10', '宫保鸡丁', '香辣有味');
INSERT INTO `dish` VALUES ('32', '2', '饭食类', '8', '西红柿鸡蛋', '鸡蛋很多');
INSERT INTO `dish` VALUES ('34', '3', '面条类', '8', '炸酱面', '酱浓味美');
INSERT INTO `dish` VALUES ('35', '3', '面条类', '8', '担担面', '正宗四川味');
INSERT INTO `dish` VALUES ('36', '3', '面点类', '1', '馒头', '好吃');
INSERT INTO `dish` VALUES ('38', '3', '饭食类', '8', '麻辣豆腐', '辣的过瘾');
INSERT INTO `dish` VALUES ('39', '3', '饭食类', '10', '红烧肉', '肉多味美');
INSERT INTO `dish` VALUES ('40', '3', '饭食类', '10', '宫保鸡丁', '香辣有味');
INSERT INTO `dish` VALUES ('41', '3', '饭食类', '8', '西红柿鸡蛋', '鸡蛋很多');
INSERT INTO `dish` VALUES ('43', '4', '面条类', '8', '炸酱面', '酱浓味美');
INSERT INTO `dish` VALUES ('44', '4', '面条类', '8', '担担面', '正宗四川味');
INSERT INTO `dish` VALUES ('45', '4', '面点类', '1', '馒头', '好吃');
INSERT INTO `dish` VALUES ('48', '4', '饭食类', '8', '麻辣豆腐', '辣的过瘾');
INSERT INTO `dish` VALUES ('49', '4', '饭食类', '10', '红烧肉', '肉多味美');
INSERT INTO `dish` VALUES ('50', '4', '饭食类', '10', '宫保鸡丁', '香辣有味');
INSERT INTO `dish` VALUES ('51', '4', '饭食类', '8', '西红柿鸡蛋', '鸡蛋很多');
INSERT INTO `dish` VALUES ('53', '5', '面条类', '8', '炸酱面', '酱浓味美');
INSERT INTO `dish` VALUES ('54', '5', '面条类', '8', '担担面', '正宗四川味');
INSERT INTO `dish` VALUES ('55', '5', '面点类', '1', '馒头', '好吃');
INSERT INTO `dish` VALUES ('56', '5', '饭食类', '8', '麻辣豆腐', '辣的过瘾');
INSERT INTO `dish` VALUES ('57', '5', '饭食类', '10', '红烧肉', '肉多味美');
INSERT INTO `dish` VALUES ('58', '5', '饭食类', '10', '宫保鸡丁', '香辣有味');
INSERT INTO `dish` VALUES ('59', '5', '饭食类', '8', '西红柿鸡蛋', '鸡蛋很多');
INSERT INTO `dish` VALUES ('60', null, null, null, null, null);
INSERT INTO `dish` VALUES ('61', '1', '面点类', '1', '窝窝头', '好吃');

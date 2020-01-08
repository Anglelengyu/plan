/*
Navicat MySQL Data Transfer

Source Server         : 本地库
Source Server Version : 50720
Source Host           : localhost:3306
Source Database       : plandemo

Target Server Type    : MYSQL
Target Server Version : 50720
File Encoding         : 65001

Date: 2019-12-02 16:54:42
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for menu
-- ----------------------------
DROP TABLE IF EXISTS `menu`;
CREATE TABLE `menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `url` varchar(64) DEFAULT NULL,
  `path` varchar(64) DEFAULT NULL,
  `component` varchar(64) DEFAULT NULL,
  `name` varchar(64) DEFAULT NULL,
  `iconCls` varchar(64) DEFAULT NULL,
  `keepAlive` tinyint(1) DEFAULT NULL,
  `requireAuth` tinyint(1) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `enabled` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  KEY `parentId` (`parentId`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of menu
-- ----------------------------
INSERT INTO `menu` VALUES ('1', '/', null, null, '所有', null, null, null, null, '1');
INSERT INTO `menu` VALUES ('2', '/', '/home', 'Home', '员工资料', 'fa fa-user-circle-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('3', '/', '/home', 'Home', '人事管理', 'fa fa-address-card-o', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('4', '/', '/home', 'Home', '薪资管理', 'fa fa-money', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('5', '/', '/home', 'Home', '统计管理', 'fa fa-bar-chart', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('6', '/', '/home', 'Home', '系统管理', 'fa fa-windows', null, '1', '1', '1');
INSERT INTO `menu` VALUES ('7', '/employee/basic/**', '/emp/basic', 'EmpBasic', '基本资料', null, null, '1', '2', '1');
INSERT INTO `menu` VALUES ('8', '/employee/advanced/**', '/emp/adv', 'EmpAdv', '高级资料', null, null, '1', '2', '0');
INSERT INTO `menu` VALUES ('9', '/personnel/emp/**', '/per/emp', 'PerEmp', '员工资料', null, null, '1', '3', '0');
INSERT INTO `menu` VALUES ('10', '/personnel/ec/**', '/per/ec', 'PerEc', '员工奖惩', null, null, '1', '3', '1');
INSERT INTO `menu` VALUES ('11', '/personnel/train/**', '/per/train', 'PerTrain', '员工培训', null, null, '1', '3', '1');
INSERT INTO `menu` VALUES ('12', '/personnel/salary/**', '/per/salary', 'PerSalary', '员工调薪', null, null, '1', '3', '1');
INSERT INTO `menu` VALUES ('13', '/personnel/remove/**', '/per/mv', 'PerMv', '员工调动', null, null, '1', '3', '1');
INSERT INTO `menu` VALUES ('14', '/salary/sob/**', '/sal/sob', 'SalSob', '工资账套管理', null, null, '1', '4', '1');
INSERT INTO `menu` VALUES ('15', '/salary/sobcfg/**', '/sal/sobcfg', 'SalSobCfg', '员工账套设置', null, null, '1', '4', '1');
INSERT INTO `menu` VALUES ('16', '/salary/table/**', '/sal/table', 'SalTable', '工资表管理', null, null, '1', '4', '1');
INSERT INTO `menu` VALUES ('17', '/salary/month/**', '/sal/month', 'SalMonth', '月末处理', null, null, '1', '4', '1');
INSERT INTO `menu` VALUES ('18', '/salary/search/**', '/sal/search', 'SalSearch', '工资表查询', null, null, '1', '4', '1');
INSERT INTO `menu` VALUES ('19', '/statistics/all/**', '/sta/all', 'StaAll', '综合信息统计', null, null, '1', '5', '1');
INSERT INTO `menu` VALUES ('20', '/statistics/score/**', '/sta/score', 'StaScore', '员工积分统计', null, null, '1', '5', '1');
INSERT INTO `menu` VALUES ('21', '/statistics/personnel/**', '/sta/pers', 'StaPers', '人事信息统计', null, null, '1', '5', '1');
INSERT INTO `menu` VALUES ('22', '/statistics/recored/**', '/sta/record', 'StaRecord', '人事记录统计', null, null, '1', '5', '1');
INSERT INTO `menu` VALUES ('23', '/system/basic/**', '/sys/basic', 'SysBasic', '基础信息设置', null, null, '1', '6', '1');
INSERT INTO `menu` VALUES ('24', '/system/cfg/**', '/sys/cfg', 'SysCfg', '系统管理', null, null, '1', '6', '1');
INSERT INTO `menu` VALUES ('25', '/system/log/**', '/sys/log', 'SysLog', '操作日志管理', null, null, '1', '6', '1');
INSERT INTO `menu` VALUES ('26', '/system/hr/**', '/sys/hr', 'SysHr', '操作员管理', null, null, '1', '6', '1');
INSERT INTO `menu` VALUES ('27', '/system/data/**', '/sys/data', 'SysData', '备份恢复数据库', null, null, '1', '6', '1');
INSERT INTO `menu` VALUES ('28', '/system/init/**', '/sys/init', 'SysInit', '初始化数据库', null, null, '1', '6', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL COMMENT '角色名',
  `description` varchar(20) DEFAULT NULL COMMENT '角色描述',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'ROLE_admin', '系统管理员');
INSERT INTO `role` VALUES ('2', 'ROLE_test', '测试');

-- ----------------------------
-- Table structure for role_menu
-- ----------------------------
DROP TABLE IF EXISTS `role_menu`;
CREATE TABLE `role_menu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  `menu_id` bigint(20) NOT NULL COMMENT '接口id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of role_menu
-- ----------------------------
INSERT INTO `role_menu` VALUES ('1', '1', '7');
INSERT INTO `role_menu` VALUES ('2', '1', '8');
INSERT INTO `role_menu` VALUES ('3', '1', '9');
INSERT INTO `role_menu` VALUES ('4', '1', '10');
INSERT INTO `role_menu` VALUES ('5', '1', '11');
INSERT INTO `role_menu` VALUES ('6', '1', '12');
INSERT INTO `role_menu` VALUES ('7', '1', '13');
INSERT INTO `role_menu` VALUES ('8', '1', '14');
INSERT INTO `role_menu` VALUES ('9', '1', '15');
INSERT INTO `role_menu` VALUES ('10', '1', '16');
INSERT INTO `role_menu` VALUES ('11', '1', '17');
INSERT INTO `role_menu` VALUES ('12', '1', '18');
INSERT INTO `role_menu` VALUES ('13', '1', '19');
INSERT INTO `role_menu` VALUES ('14', '1', '20');
INSERT INTO `role_menu` VALUES ('15', '1', '21');
INSERT INTO `role_menu` VALUES ('16', '1', '22');
INSERT INTO `role_menu` VALUES ('17', '1', '23');
INSERT INTO `role_menu` VALUES ('18', '1', '24');
INSERT INTO `role_menu` VALUES ('19', '1', '25');
INSERT INTO `role_menu` VALUES ('20', '1', '26');
INSERT INTO `role_menu` VALUES ('21', '1', '27');
INSERT INTO `role_menu` VALUES ('22', '1', '28');
INSERT INTO `role_menu` VALUES ('23', '2', '7');
INSERT INTO `role_menu` VALUES ('24', '2', '8');
INSERT INTO `role_menu` VALUES ('25', '2', '9');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `name` varchar(20) DEFAULT NULL COMMENT '昵称',
  `username` varchar(20) DEFAULT NULL COMMENT '用户名',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  `pwd` varchar(20) DEFAULT NULL COMMENT '真实密码',
  `gender` tinyint(2) DEFAULT NULL COMMENT '性别  1-男 2-女',
  `email` varchar(50) DEFAULT NULL COMMENT '邮件',
  `birth_date` date DEFAULT NULL COMMENT '出生日期',
  `icon` varchar(255) DEFAULT NULL COMMENT '头像',
  `telephone` varchar(20) DEFAULT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `register_date` date DEFAULT NULL COMMENT '注册时间',
  `is_delete` tinyint(1) DEFAULT '0' COMMENT '是否禁用 0没禁用 1 禁用  默认0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8;


-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '系统管理员', 'admin', '$2a$10$ySG2lkvjFHY5O0./CPIE1OI8VJsuKYEzOYzqIa7AJR6sEgSzUFOAm', null, 'http://bpic.588ku.com/element_pic/01/40/00/64573ce2edc0728.jpg', '18568887789', '深圳南山', null, '0');
INSERT INTO `user` VALUES ('2', '李白', 'libai', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', null, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1514093920321&di=913e88c23f382933ef430024afd9128a&imgtype=0&src=http%3A%2F%2Fp.3761.com%2Fpic%2F9771429316733.jpg', '18568123489', '海口美兰', null, '0');
INSERT INTO `user` VALUES ('3', '韩愈', 'hanyu', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', null, 'https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=1406745149,1563524794&fm=27&gp=0.jpg', '18568123666', '广州番禺', null, '0');
INSERT INTO `user` VALUES ('4', '柳宗元', 'liuzongyuan', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', null, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1515233756&di=0856d923a0a37a87fd26604a2c871370&imgtype=jpg&er=1&src=http%3A%2F%2Fwww.qqzhi.com%2Fuploadpic%2F2014-09-27%2F041716704.jpg', '18568123377', '广州天河', null, '0');
INSERT INTO `user` VALUES ('5', '曾巩', 'zenggong', '$2a$10$oE39aG10kB/rFu2vQeCJTu/V/v4n6DRR0f8WyXRiAYvBpmadoOBE.', null, 'https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1517070040185&di=be0375e0c3db6c311b837b28c208f318&imgtype=0&src=http%3A%2F%2Fimg2.soyoung.com%2Fpost%2F20150213%2F6%2F20150213141918532.jpg', '18568128888', '广州越秀', null, '0');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) NOT NULL COMMENT '用户id',
  `role_id` bigint(20) NOT NULL COMMENT '角色id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

DROP TABLE IF EXISTS `login_info`;
CREATE TABLE `login_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `user_id` bigint(20) DEFAULT NULL,
  `name` varchar(50) DEFAULT NULL COMMENT '用户名',
  `ip` varchar(50) DEFAULT NULL COMMENT 'ip',
  `login_time` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8 COMMENT='登录记录';


-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1', '1');
INSERT INTO `user_role` VALUES ('2', '2', '2');

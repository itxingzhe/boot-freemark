/*
Navicat MySQL Data Transfer

Source Server         : mysql
Source Server Version : 50558
Source Host           : localhost:3306
Source Database       : mydata

Target Server Type    : MYSQL
Target Server Version : 50558
File Encoding         : 65001

Date: 2018-07-25 19:07:27
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for demo_info
-- ----------------------------
DROP TABLE IF EXISTS `demo_info`;
CREATE TABLE `demo_info` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_info
-- ----------------------------

-- ----------------------------
-- Table structure for hibernate_sequence
-- ----------------------------
DROP TABLE IF EXISTS `hibernate_sequence`;
CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of hibernate_sequence
-- ----------------------------
INSERT INTO `hibernate_sequence` VALUES ('1');

-- ----------------------------
-- Table structure for login_user_information
-- ----------------------------
DROP TABLE IF EXISTS `login_user_information`;
CREATE TABLE `login_user_information` (
  `id` int(30) NOT NULL AUTO_INCREMENT COMMENT '自增主键',
  `user_id` bigint(30) NOT NULL COMMENT '独立标记',
  `account` varchar(255) NOT NULL COMMENT '用户名(登录名)',
  `password` varchar(255) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of login_user_information
-- ----------------------------
INSERT INTO `login_user_information` VALUES ('1', '1868219932', 'llixin', 'lx54321');

-- ----------------------------
-- Table structure for module
-- ----------------------------
DROP TABLE IF EXISTS `module`;
CREATE TABLE `module` (
  `mid` int(11) NOT NULL AUTO_INCREMENT,
  `mname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`mid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module
-- ----------------------------
INSERT INTO `module` VALUES ('1', 'add');
INSERT INTO `module` VALUES ('2', 'delete');
INSERT INTO `module` VALUES ('3', 'query');
INSERT INTO `module` VALUES ('4', 'update');

-- ----------------------------
-- Table structure for module_role
-- ----------------------------
DROP TABLE IF EXISTS `module_role`;
CREATE TABLE `module_role` (
  `rid` int(11) DEFAULT NULL,
  `mid` int(11) DEFAULT NULL,
  KEY `rid` (`rid`),
  KEY `mid` (`mid`),
  CONSTRAINT `mid` FOREIGN KEY (`mid`) REFERENCES `module` (`mid`),
  CONSTRAINT `rid` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of module_role
-- ----------------------------
INSERT INTO `module_role` VALUES ('1', '1');
INSERT INTO `module_role` VALUES ('1', '2');
INSERT INTO `module_role` VALUES ('1', '3');
INSERT INTO `module_role` VALUES ('1', '4');
INSERT INTO `module_role` VALUES ('2', '1');
INSERT INTO `module_role` VALUES ('2', '3');

-- ----------------------------
-- Table structure for mycat_seqlg
-- ----------------------------
DROP TABLE IF EXISTS `mycat_seqlg`;
CREATE TABLE `mycat_seqlg` (
  `name` varchar(50) NOT NULL,
  `current_value` int(11) NOT NULL DEFAULT '0',
  `increment` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of mycat_seqlg
-- ----------------------------
INSERT INTO `mycat_seqlg` VALUES ('t_user', '6', '1');

-- ----------------------------
-- Table structure for portal_resource
-- ----------------------------
DROP TABLE IF EXISTS `portal_resource`;
CREATE TABLE `portal_resource` (
  `id` bigint(30) NOT NULL AUTO_INCREMENT,
  `shelf_user_id` bigint(30) DEFAULT NULL,
  `shelf_user_name` varchar(33) NOT NULL DEFAULT '' COMMENT '上架人名称',
  `portal_name` varchar(30) DEFAULT NULL,
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of portal_resource
-- ----------------------------
INSERT INTO `portal_resource` VALUES ('1', '1', 'uname', 'pname', '2018-04-08 15:20:17');
INSERT INTO `portal_resource` VALUES ('2', '2', 'xiaoming', 'yizhong', '2018-04-08 15:21:22');
INSERT INTO `portal_resource` VALUES ('3', '3', 'LeiLi', 'JumoreYun', '2018-04-08 15:22:05');

-- ----------------------------
-- Table structure for product
-- ----------------------------
DROP TABLE IF EXISTS `product`;
CREATE TABLE `product` (
  `id` bigint(30) DEFAULT NULL COMMENT 'id',
  `productName` varchar(255) DEFAULT '' COMMENT '产品名称',
  `productContent` varchar(255) DEFAULT '' COMMENT '产品内容',
  `price` varchar(255) DEFAULT NULL COMMENT '价格',
  `sort` int(10) DEFAULT '9' COMMENT '排序'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of product
-- ----------------------------
INSERT INTO `product` VALUES ('1', '哈哈', '呵呵', '10', '9');

-- ----------------------------
-- Table structure for p_user
-- ----------------------------
DROP TABLE IF EXISTS `p_user`;
CREATE TABLE `p_user` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(30) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `uname` varchar(30) DEFAULT NULL,
  `age` int(3) DEFAULT NULL,
  `sex` varchar(8) DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_user
-- ----------------------------
INSERT INTO `p_user` VALUES ('1', 'hlhdidi', '123', '张三', '23', '男', '求是路3号');
INSERT INTO `p_user` VALUES ('2', 'xyycici', '1992', null, null, null, null);
INSERT INTO `p_user` VALUES ('5', '13024333333', '29', '李四', null, '男', '半山出口');
INSERT INTO `p_user` VALUES ('6', '13024333332', '18', '散散', null, '女', '西溪');
INSERT INTO `p_user` VALUES ('7', 'lisi', '111111', '李四', '99', '男', '二弄口');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `rid` int(11) NOT NULL AUTO_INCREMENT,
  `rname` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`rid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', 'admin');
INSERT INTO `role` VALUES ('2', 'customer');

-- ----------------------------
-- Table structure for user_role
-- ----------------------------
DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `uid` int(11) DEFAULT NULL,
  `rid` int(11) DEFAULT NULL,
  KEY `u_fk` (`uid`),
  KEY `r_fk` (`rid`),
  CONSTRAINT `r_fk` FOREIGN KEY (`rid`) REFERENCES `role` (`rid`),
  CONSTRAINT `u_fk` FOREIGN KEY (`uid`) REFERENCES `p_user` (`uid`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_role
-- ----------------------------
INSERT INTO `user_role` VALUES ('1', '1');
INSERT INTO `user_role` VALUES ('2', '2');

-- ----------------------------
-- Function structure for mycat_seq_currval
-- ----------------------------
DROP FUNCTION IF EXISTS `mycat_seq_currval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `mycat_seq_currval`(seq_name VARCHAR(50)) RETURNS varchar(64) CHARSET utf8
    DETERMINISTIC
BEGIN DECLARE retval VARCHAR(64);
        SET retval="-999999999,null";  
        SELECT CONCAT(CAST(current_value AS CHAR),",",CAST(increment AS CHAR)) INTO retval 
          FROM mycat_seqlg WHERE `name` = seq_name;  
        RETURN retval ; 
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for mycat_seq_nextval
-- ----------------------------
DROP FUNCTION IF EXISTS `mycat_seq_nextval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `mycat_seq_nextval`(seq_name VARCHAR(50)) RETURNS varchar(64) CHARSET utf8
    DETERMINISTIC
BEGIN UPDATE mycat_seqlg  
                 SET current_value = current_value + increment 
                  WHERE `name` = seq_name;  
         RETURN mycat_seq_currval(seq_name);  
END
;;
DELIMITER ;

-- ----------------------------
-- Function structure for mycat_seq_setval
-- ----------------------------
DROP FUNCTION IF EXISTS `mycat_seq_setval`;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `mycat_seq_setval`(seq_name VARCHAR(50), VALUE INTEGER) RETURNS varchar(64) CHARSET utf8
    DETERMINISTIC
BEGIN UPDATE mycat_seqlg  
                   SET current_value = VALUE  
                   WHERE `name` = seq_name;  
         RETURN mycat_seq_currval(seq_name);  
END
;;
DELIMITER ;

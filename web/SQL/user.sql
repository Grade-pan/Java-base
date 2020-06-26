/*
Navicat MySQL Data Transfer

Source Server         : MySQL
Source Server Version : 80019
Source Host           : localhost:3306
Source Database       : java

Target Server Type    : MYSQL
Target Server Version : 80019
File Encoding         : 65001

Date: 2020-06-26 10:24:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `username` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL,
  `email` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `password` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `age` varchar(11) DEFAULT NULL,
  `Tel` varchar(255) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `ip` varchar(255) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  `superRoot` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL,
  PRIMARY KEY (`username`),
  UNIQUE KEY `username` (`username`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('为何是炮', '158620322@qq.com', '123456789sdp', '22', '17569856325', '河南省商丘市睢县', '223.88.168.160', '男', '否');
INSERT INTO `user` VALUES ('北京大学生', '18659632032@qq.com', 'empwU3hbkTjZZnD', '56', '15896325089', '石家庄', '223.88.168.128', '男', '是');
INSERT INTO `user` VALUES ('北方', '78956412@qq.com', '4578pkkddfddf', '32', '11589652325', '河南省商丘市', '223.88.168.51', '女', '否');
INSERT INTO `user` VALUES ('千古', 'huaqiangu@163.com', 'huaqiangu', '20', '16658952365', '上海', '223.88.168.51', '女', '否');
INSERT INTO `user` VALUES ('千山鸟', '12323213@163.com', 'Tindffd2522', '18', '15898564721', '湖北省武汉市', '223.88.168.128', '其他', '是');
INSERT INTO `user` VALUES ('千山鸟人', '12323213@163.com', 'daadjhsfsdf52', '23', '14895623695', '河南省周口市', '223.88.168.128', '男', '是');
INSERT INTO `user` VALUES ('千山鸟鸟', '12345623@163.com', 'sfsfdsd45', '65', '15985623069', '河南省周口市', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('千言万语', '158654241@qq.com', 'SFsef2525', '19', '15986523695', '新疆和田', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('大中华', 'asdhhs@163.com', 'asdasdasd78', '20', '15678654345', '河南省信阳市', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('孙大炮', '1256982@qq.com', 'sfsfsd120', '22', '15896325968', '河南省睢县', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('小明', 'ssfsfsf@163.com', '2323', '23', '15269852698', '河南省郑州市', '223.91.247.49', '女', '否');
INSERT INTO `user` VALUES ('小红', '1817865125@qq.com', 'sfdsdfsdf', '32', '14785965258', '河南省洛阳市', '223.91.247.49', '女', '是');
INSERT INTO `user` VALUES ('清北大', '1258963@qq.com', '45682Red', '19', '15036985269', '浙江省杭州市', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('王浩哲', '58956985214@qq.com', 'afsfsfsdf220', '24', '14758698548', '河南省汝州市', '223.88.168.51', '男', '是');
INSERT INTO `user` VALUES ('王浩哲是个管', '766520360@qq.com', 'whzsgg123', '21', '18737327283', '河南省新乡市封丘县', '223.88.168.160', '其他', '是');
INSERT INTO `user` VALUES ('王浩哲没', '123@163.com', 'Uhdgfydfgfd022', '19', '15269852471', '青海省西宁市', '223.88.168.128', '女', '否');
INSERT INTO `user` VALUES ('王浩哲的', '123@163.com', 'Gyunhdfhdf025', '19', '15269852471', '青海省西宁市', '223.88.168.128', '女', '否');
INSERT INTO `user` VALUES ('王浩哲的毛', '1817865166@qq.com', 'pxyyoona123456', '29', '15698523658', '河南省信阳市', '223.88.168.128', '其他', '是');
INSERT INTO `user` VALUES ('王浩哲的鸟', 'hsdgfhsd@163.com', 'sdhfghdf256', '25', '15036758962', '河北大名', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('王者之锤', '1817865166@qq.com', 'pxyyoona123456', '26', '1526985247', '河南省巩义市', '223.88.168.51', '男', '否');
INSERT INTO `user` VALUES ('阿斯顿发生', '12323213@163.com', 'sddsdsd34', '19', '14858956324', '河南省巩义市', '223.88.168.128', '男', '否');
INSERT INTO `user` VALUES ('霸王峰龙', 'sdhfgfd2@163.com', 'sdghshgf25200', '29', '15285696352', '河南省周口市', '223.88.168.128', '女', '是');
INSERT INTO `user` VALUES ('青花瓷', 'jinhuadu@163.com', '251252222Erd', '56', '18956236958', '深圳市', '223.88.168.128', '男', '否');

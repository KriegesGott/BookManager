# ************************************************************
# Sequel Ace SQL dump
# 版本号： 20019
#
# https://sequel-ace.com/
# https://github.com/Sequel-Ace/Sequel-Ace
#
# 主机: 127.0.0.1 (MySQL 8.0.27)
# 数据库: library_manager_web
# 生成时间: 2022-02-22 05:16:50 +0000
# ************************************************************


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
SET NAMES utf8mb4;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE='NO_AUTO_VALUE_ON_ZERO', SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;


# 转储表 book
# ------------------------------------------------------------

DROP TABLE IF EXISTS `book`;

CREATE TABLE `book` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `author` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `publisher` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `type` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `state` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '在馆',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `modifier` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `book` WRITE;
/*!40000 ALTER TABLE `book` DISABLE KEYS */;

INSERT INTO `book` (`id`, `name`, `author`, `publisher`, `type`, `state`, `creator`, `modifier`, `create_time`, `update_time`)
VALUES
	(100001,'数据结构（C++语言版·第3版）','邓俊辉','清华大学出版社','计算机','借出','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100002,'数据结构与算法分析','Mark Allen Weiss','机械工业出版社','计算机','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100003,'计算机组成原理','艾伦·克莱门茨','机械工业出版社','计算机','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100004,'机械工程史','张策','清华大学出版社','机械','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100005,'机械工程导论','威克特','西安交通大学出版社','机械','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100006,'结构力学','李廉锟','高等教育出版社','土木','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100007,'中国政治思想史','萧公权','商务印书馆','政治','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100008,' 微观经济学','哈尔·R.范里安','格致出版社','商务','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100009,'高等数学·上册','同济大学数学系','高等教育出版社','数学','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100010,'狭义与广义相对论浅说','[美] 阿尔伯特·爱因斯坦 ','商务印书馆','物理','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100011,' 公共管理学','张成福 党秀云','中国人民大学','管理','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100012,' 中国文学史','林庚','清华大学出版社','文学','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100013,'日本语','[日]金田一春彦','华东理工大学出版社','外语','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100014,' 有机化学','K.彼得','化学工业出版社','化学','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100015,'材料科学基础','胡赓祥','上海交通大学出版社','材料','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100016,'环境工程学','蒋展鹏','高等教育出版社','环境','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100017,'数字集成电路','拉贝','清华大学出版社','电子','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100018,'德国民法典','台湾大学法律学院','北京大学出版社 ','法律','在馆','admin','admin','2022-02-11 17:10:44','2022-02-11 17:10:44'),
	(100019,'test','test','test1','其他','在馆','admin_test','admin_test','2022-02-11 17:28:53','2022-02-11 17:29:12');

/*!40000 ALTER TABLE `book` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 borrow
# ------------------------------------------------------------

DROP TABLE IF EXISTS `borrow`;

CREATE TABLE `borrow` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint NOT NULL,
  `book_id` bigint NOT NULL,
  `start_time` timestamp NOT NULL,
  `end_time` timestamp NOT NULL,
  `state` varchar(2) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '未还',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `borrow` WRITE;
/*!40000 ALTER TABLE `borrow` DISABLE KEYS */;

INSERT INTO `borrow` (`id`, `user_id`, `book_id`, `start_time`, `end_time`, `state`)
VALUES
	(100002,100014,100001,'2022-02-10 19:13:14','2022-03-12 19:13:14','已还'),
	(100003,100014,100002,'2022-02-10 19:35:50','2022-03-12 19:35:50','已还'),
	(100004,100014,100001,'2022-02-10 19:56:56','2022-03-12 19:56:56','已还'),
	(100005,100014,100001,'2022-02-10 21:55:41','2022-03-12 21:55:41','已还'),
	(100006,100014,100001,'2022-02-10 00:00:00','2022-03-12 00:00:00','未还');

/*!40000 ALTER TABLE `borrow` ENABLE KEYS */;
UNLOCK TABLES;


# 转储表 user
# ------------------------------------------------------------

DROP TABLE IF EXISTS `user`;

CREATE TABLE `user` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '' COMMENT '名字',
  `password` varchar(20) NOT NULL DEFAULT '',
  `student_code` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `department` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `role` varchar(3) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '用户',
  `creator` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `modifier` varchar(20) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci NOT NULL DEFAULT '',
  `create_time` timestamp NOT NULL,
  `update_time` timestamp NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;

INSERT INTO `user` (`id`, `name`, `password`, `student_code`, `department`, `role`, `creator`, `modifier`, `create_time`, `update_time`)
VALUES
	(100002,'admin','admin','','','管理员','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100004,'张三','zhangsan','201805551115','计算机学院·网络空间安全学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100005,'李四','lisi','201805552222','商学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100006,'王五','wangwu','201805553333','法学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100007,'赵六','zhaoliu','201805554444','商学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100008,'user','user','222222222222','商学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100009,'admin_test','test','333333333333','机械工程学院','管理员','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100010,'王一','wangyi','201705551730','法学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100011,'张一','201605551723','201605551723','自动化与电子信息学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100012,'addTest','123222222222','123222222222','机械工程学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100013,'addTest1','111111111111','111111111112','机械工程学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100014,'戚熙元','201805551730','201805551730','计算机学院·网络空间安全学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100017,'testt','333333333333','333333333333','马克思主义学院','用户','admin','admin','2022-02-11 17:10:24','2022-02-11 17:10:24'),
	(100018,'2-11','201805434567','201805434568','公共管理学院','用户','admin_test','admin_test','2022-02-11 17:27:54','2022-02-11 17:28:36');

/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;



/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;

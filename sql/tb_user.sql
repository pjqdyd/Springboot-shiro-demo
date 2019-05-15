-- MySQL dump 10.13  Distrib 8.0.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: db_shiro
-- ------------------------------------------------------
-- Server version	8.0.12


DROP TABLE IF EXISTS `tb_user`;

 SET character_set_client = utf8mb4 ;

CREATE TABLE `tb_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(20) DEFAULT NULL,
  `password` varchar(50) DEFAULT NULL,
  `perms` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


INSERT INTO `tb_user` VALUES (1,'pjqdyd','123456','user:add'),(2,'test','1','user:update');
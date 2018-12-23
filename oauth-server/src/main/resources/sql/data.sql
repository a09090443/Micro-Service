-- MySQL dump 10.13  Distrib 5.7.24, for Linux (x86_64)
--
-- Host: 192.168.1.151    Database: dev_oauth
-- ------------------------------------------------------
-- Server version	5.5.60-MariaDB

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Dumping data for table `personal_title`
--

LOCK TABLES `personal_title` WRITE;
/*!40000 ALTER TABLE `personal_title` DISABLE KEYS */;
INSERT INTO `personal_title` VALUES ('01','ADMIN'),('02','MANAGER');
/*!40000 ALTER TABLE `personal_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_title`
--

LOCK TABLES `user_title` WRITE;
/*!40000 ALTER TABLE `user_title` DISABLE KEYS */;
INSERT INTO `user_title` VALUES ('000001','01'),('000001','02'),('000002','02');
/*!40000 ALTER TABLE `user_title` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `authority`
--

LOCK TABLES `authority` WRITE;
/*!40000 ALTER TABLE `authority` DISABLE KEYS */;
INSERT INTO `authority` VALUES ('01','ROLE_ADMIN'),('02','ROLE_USER');
/*!40000 ALTER TABLE `authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_authority`
--

LOCK TABLES `user_authority` WRITE;
/*!40000 ALTER TABLE `user_authority` DISABLE KEYS */;
INSERT INTO `user_authority` VALUES ('000001','01'),('000001','02'),('000002','02');
/*!40000 ALTER TABLE `user_authority` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `oauth_client_details`
--

LOCK TABLES `oauth_client_details` WRITE;
/*!40000 ALTER TABLE `oauth_client_details` DISABLE KEYS */;
INSERT INTO `oauth_client_details` VALUES ('api_service','oauth2-resource','secret','read,write','client_credentials',NULL,NULL,NULL,NULL,NULL,'true'),('auth_test','oauth2-resource','secret','any,read,write','authorization_code,refresh_token','',NULL,NULL,NULL,NULL,'true'),('client_test','oauth2-resource','secret','read,write','client_credentials',NULL,NULL,NULL,NULL,NULL,'true'),('implicit_test','oauth2-resource','secret','read,write','implicit',NULL,NULL,NULL,NULL,NULL,'true'),('password_test','oauth2-resource','secret','read,write','password,refresh_token',NULL,NULL,NULL,NULL,NULL,'true'),('sso_service','oauth2-resource','secret','read,write,user_info','authorization_code,implicit',NULL,NULL,NULL,NULL,NULL,'true'),('zuul_service','oauth2-resource','secret','read,write','client_credentials',NULL,NULL,NULL,NULL,NULL,'true');
/*!40000 ALTER TABLE `oauth_client_details` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping data for table `user_info`
--

LOCK TABLES `user_info` WRITE;
/*!40000 ALTER TABLE `user_info` DISABLE KEYS */;
INSERT INTO `user_info` VALUES (1,'000001','admin','$2a$10$edhXzouCrFzV/8WhlT4pn.V.fro7J4rvAMFfjpFTxHmQj7.N4meDa','admin','admin','admin@localhost.com','','Test','1992-01-01',NULL,_binary '',NULL),(5,'000002','test','$2a$10$UEIJGi/pLTvx8U3a.9rlEeCLYy3Xd7/8MaHvkAf6A0/..Ye8cfhge','test','test','test@localhost.com','1234567890','test','2000-02-11','000002.jpg',_binary '','2018-09-28 10:58:43');
/*!40000 ALTER TABLE `user_info` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2018-12-23 14:25:34
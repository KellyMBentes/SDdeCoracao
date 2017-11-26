CREATE DATABASE  IF NOT EXISTS `db_middleware` /*!40100 DEFAULT CHARACTER SET latin1 */;
USE `db_middleware`;
-- MySQL dump 10.13  Distrib 5.7.18, for Linux (x86_64)
--
-- Host: localhost    Database: db_middleware
-- ------------------------------------------------------
-- Server version	5.7.18-1

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
-- Table structure for table `client`
--

DROP TABLE IF EXISTS `client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `client` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `address` varchar(45) NOT NULL,
  `tag_A` int(4) DEFAULT '0',
  `tag_B` int(4) DEFAULT '0',
  `tag_C` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `client`
--

LOCK TABLES `client` WRITE;
/*!40000 ALTER TABLE `client` DISABLE KEYS */;
/*!40000 ALTER TABLE `client` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `db_middleware`.`client_AFTER_INSERT` AFTER INSERT ON `client` FOR EACH ROW
BEGIN
if (NEW.tag_A = 1) then
	if exists (select id from message where tag_A = new.tag_A and 
    not exists (select * from message_client, message 
    where message_client.id_client = new.id and 
    message_client.id_message = message.id)) then 
		insert into message_client (id_client, id_message, received) 
        select new.id, id, 0 from message where tag_A = new.tag_A;
	end if;
end if;

if (NEW.tag_B = 1) then
    if exists (select id from message where tag_B = new.tag_B and 
    not exists (select * from message_client, message 
    where message_client.id_client = new.id and 
    message_client.id_message = message.id)) then 
		insert into message_client (id_client, id_message, received) 
        select new.id, id, 0 from message where tag_B = new.tag_B;
	end if;
end if;

if (NEW.tag_C = 1) then
    if exists (select id from message where tag_C = new.tag_C and 
    not exists (select * from message_client, message 
    where message_client.id_client = new.id and 
    message_client.id_message = message.id)) then 
		insert into message_client (id_client, id_message, received) 
        select new.id, id, 0 from message where tag_C = new.tag_C;
	end if;
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message`
--

DROP TABLE IF EXISTS `message`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `content` varchar(45) DEFAULT '',
  `tag_A` int(4) DEFAULT '0',
  `tag_B` int(4) DEFAULT '0',
  `tag_C` int(4) DEFAULT '0',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message`
--

LOCK TABLES `message` WRITE;
/*!40000 ALTER TABLE `message` DISABLE KEYS */;
/*!40000 ALTER TABLE `message` ENABLE KEYS */;
UNLOCK TABLES;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8 */ ;
/*!50003 SET character_set_results = utf8 */ ;
/*!50003 SET collation_connection  = utf8_general_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
/*!50003 CREATE*/ /*!50017 DEFINER=`root`@`localhost`*/ /*!50003 TRIGGER `db_middleware`.`message_AFTER_INSERT` AFTER INSERT ON `message` FOR EACH ROW
BEGIN
if (NEW.tag_A = 1) then
	if exists (select id from client where tag_A = new.tag_A and 
    not exists (select * from message_client, client 
    where message_client.id_client = client.id and 
    message_client.id_message = new.id)) then 
		insert into message_client (id_client, id_message, received) 
        select id, new.id, 0 from client where tag_A = new.tag_A;
	end if;
end if;

if (NEW.tag_B = 1) then
	if exists (select id from client where tag_B = new.tag_B and 
    not exists (select * from message_client, client 
    where message_client.id_client = client.id and 
    message_client.id_message = new.id)) then 
		insert into message_client (id_client, id_message, received) 
        select id, new.id, 0 from client where tag_B = new.tag_B;
	end if;
end if;

if (NEW.tag_C = 1) then
    if exists (select id from client where tag_C = new.tag_C and 
    not exists (select * from message_client, client 
    where message_client.id_client = client.id and 
    message_client.id_message = new.id)) then
        insert into message_client (id_client, id_message, received) 
        select id, new.id, 0 from client where tag_C = new.tag_C;
	end if;
end if;
END */;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Table structure for table `message_client`
--

DROP TABLE IF EXISTS `message_client`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `message_client` (
  `id` int(4) NOT NULL AUTO_INCREMENT,
  `id_client` int(4) NOT NULL,
  `id_message` int(4) NOT NULL,
  `received` int(4) NOT NULL DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uq_message_client` (`id_client`,`id_message`),
  KEY `fk_client_idx` (`id_client`),
  KEY `fk_message_idx` (`id_message`),
  CONSTRAINT `fk_client` FOREIGN KEY (`id_client`) REFERENCES `client` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_message` FOREIGN KEY (`id_message`) REFERENCES `message` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `message_client`
--

LOCK TABLES `message_client` WRITE;
/*!40000 ALTER TABLE `message_client` DISABLE KEYS */;
/*!40000 ALTER TABLE `message_client` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'db_middleware'
--

--
-- Dumping routines for database 'db_middleware'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-11-25 22:40:37

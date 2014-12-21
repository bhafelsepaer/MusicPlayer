-- MySQL dump 10.13  Distrib 5.5.37, for debian-linux-gnu (x86_64)
--
-- Host: localhost    Database: musicStore
-- ------------------------------------------------------
-- Server version	5.5.37-0ubuntu0.13.10.1

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
-- Current Database: `musicStore`
--
CREATE DATABASE  IF NOT EXISTS `musicStore` /*!40100 DEFAULT CHARACTER SET latin1 */;

USE `musicStore`;

--
-- Table structure for table `ALBUM`
--

DROP TABLE IF EXISTS `ALBUM`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ALBUM` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(255) NOT NULL,
  `picture_metadate` varchar(255) NOT NULL,
  `release_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  UNIQUE KEY `album_name` (`album_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ALBUM`
--

LOCK TABLES `ALBUM` WRITE;
/*!40000 ALTER TABLE `ALBUM` DISABLE KEYS */;
INSERT INTO `ALBUM` VALUES (1,'ELZA-New_World','/resources/images/album_picture/byAlbum/Elza-NewWorld.jpg',2014),(2,'The_Fighters-Never_Surrender','/resources/images/album_picture/byAlbum/The_Fighters-Never_Surrender.jpg',2003),(3,'Pikachu-I_AM_FREE','/resources/images/album_picture/byAlbum/Pikachu-I_AM_FREE.jpg',2000),(4,'Stupid_Cow-Jingle_Jingle','/resources/images/album_picture/byAlbum/Stupid_Cow-Jingle_Jingle.jpg',1871),(5,'Baka-Triple_Baka','/resources/images/album_picture/byAlbum/Baka-Triple_Baka.jpg',2203);
/*!40000 ALTER TABLE `ALBUM` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `ARTIST`
--

DROP TABLE IF EXISTS `ARTIST`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `ARTIST` (
  `artist_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `years_active` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artist_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `ARTIST`
--

LOCK TABLES `ARTIST` WRITE;
/*!40000 ALTER TABLE `ARTIST` DISABLE KEYS */;
INSERT INTO `ARTIST` VALUES (1,'ELZA',2012,'ELZA FROM MOVIE FROZEN'),(2,'Stupid_Cow',2000,'Stupid Cow create inteligent song about a love, drugs, sex and peace'),(3,'Baka',1888,'Artist Baka who like banana'),(4,'The_Fighters',1212,'The_Fighters song about fights'),(5,'Tritiana',2014,'Tritiana is new vocal artist who win the show Mam Talent'),(6,'Pikachu',2012,'Pikachu who escaped from Ash Startet to filfil him dream about sing');
/*!40000 ALTER TABLE `ARTIST` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `GENRES`
--

DROP TABLE IF EXISTS `GENRES`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `GENRES` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `GENRES`
--

LOCK TABLES `GENRES` WRITE;
/*!40000 ALTER TABLE `GENRES` DISABLE KEYS */;
INSERT INTO `GENRES` VALUES (2,'nightcore-pop','nice song pop'),(3,'nightcore-metal','BLACK POWER'),(4,'nightcore-HipHop','Best Rap'),(5,'nightcore-Rock','Rock Band can share own music here'),(6,'Trance','Music Create by independent Artist'),(7,'Epic Music','Music From Game Movie itp.');
/*!40000 ALTER TABLE `GENRES` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `SONG`
--

DROP TABLE IF EXISTS `SONG`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `SONG` (
  `song_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `filename` varchar(255) NOT NULL,
  `genre_id` int(11) DEFAULT NULL,
  `album_id` int(11) DEFAULT NULL,
  `artist_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`song_id`),
  KEY `fk_SONG_GENRE` (`genre_id`),
  KEY `fk_SongAlbum` (`album_id`),
  KEY `fk_SongArtist` (`artist_id`),
  CONSTRAINT `fk_SongAlbum` FOREIGN KEY (`album_id`) REFERENCES `ALBUM` (`album_id`),
  CONSTRAINT `fk_SongArtist` FOREIGN KEY (`artist_id`) REFERENCES `ARTIST` (`artist_id`),
  CONSTRAINT `fk_SONG_GENRE` FOREIGN KEY (`genre_id`) REFERENCES `GENRES` (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `SONG`
--

LOCK TABLES `SONG` WRITE;
/*!40000 ALTER TABLE `SONG` DISABLE KEYS */;
INSERT INTO `SONG` VALUES (4,'My_First_Kiss_Nightcore','resources/music/nightcore/POP/My_First_Kiss_Nightcore.mp3',2,2,2),(5,'Nightcore-A Pirate','/resources/music/nightcore/POP/Nightcore-A Pirate.mp3',2,3,1),(6,'Nightcore - Mad World','/resources/music/nightcore/POP/Nightcore - Mad World.mp3',2,4,2),(7,'Nightcore - In Your Arms','/resources/music/nightcore/POP/Nightcore - In Your Arms.mp3',2,3,3),(8,'Nightcore-Flying high','/resources/music/nightcore/POP/Nightcore-Flying high.mp3',2,1,4),(9,'Nightcore - Milkshake','/resources/music/nightcore/Metal/Nightcore - Milkshake.mp3',3,5,4),(10,'Cocaine','/resources/music/nightcore/Metal/Cocaine.mp3',3,5,1),(11,'Nightcore - War Of Change','/resources/music/nightcore/Metal/Nightcore - War Of Change.mp3',3,2,2),(12,'Nightcore - Such Horrible Things','/resources/music/nightcore/Metal/Nightcore - Such Horrible Things.mp3',3,1,4),(13,'Nightcore- Sick','/resources/music/nightcore/Metal/Nightcore- Sick.mp3',3,4,2),(14,'Nightcore - CrushCrushCrush','/resources/music/nightcore/HipHop/Nightcore - CrushCrushCrush.mp3',4,3,4),(15,'Nightcore - Fuck U Betta','/resources/music/nightcore/HipHop/Nightcore - Fuck U Betta.mp3',4,1,1),(16,'Nightcore - The Boys','/resources/music/nightcore/HipHop/Nightcore - The Boys.mp3',4,2,5),(18,'Nightcore-Smooth Criminal','/resources/music/nightcore/HipHop/Nightcore-Smooth Criminal.mp3',4,1,6),(19,'NightCore-The_next_episode','/resources/music/nightcore/HipHop/NightCore-The_next_episode.mp3',4,2,5),(20,'Welcome to the Masquerade','/resources/music/nightcore/Rock/Welcome to the Masquerade.mp3',5,3,1),(21,'Five Nights At Freddy-Hide and Seek','/resources/music/nightcore/Rock/Five Nights At Freddy-Hide and Seek.mp3',5,4,4),(22,'Nightcore - Murder!.mp3','/resources/music/nightcore/Rock/Nightcore - Murder!.mp3',5,2,2),(23,'Nightcore-Welcome To The Family','/resources/music/nightcore/Rock/Nightcore-Welcome To The Family.mp3',5,5,5),(24,'Shallow Grave.mp3','/resources/music/nightcore/Rock/Shallow Grave.mp3',5,5,6),(25,'Trance - What The F-ck Is Jumpstyle','/resources/music/Trance/Trance - What The F-ck Is Jumpstyle.mp3',6,1,1),(26,'Six Little Eggs - Jakarta','/resources/music/Trance/Six Little Eggs - Jakarta.mp3',6,5,6),(27,'Singularity Brainchild.mp3','/resources/music/Trance/Singularity Brainchild.mp3',6,4,5),(28,'Primary Desease.mp3','/resources/music/Trance/Primary Desease.mp3',6,2,6),(29,'Trance - Doubledecker','/resources/music/Trance/Trance - Doubledecker.mp3',6,4,3),(30,'Audiomachine - Above and Beyond Remix','resources/music/EpicMusic/Audiomachine - Above and Beyond Remix.mp3',7,3,1),(31,'Kyueko - Dempsey Roll.mp3','resources/music/EpicMusic/Kyueko - Dempsey Roll.mp3',7,1,2),(32,'Mirko Garofalo - The Enchanted Valley','resources/music/EpicMusic/Mirko Garofalo - The Enchanted Valley.mp3',7,3,2),(33,'Nightcore - MKAlie','resources/music/EpicMusic/Nightcore - MKAlieZ.mp3',7,1,6),(34,'Thomas-Goodbye Miss Pudding','resources/music/EpicMusic/Thomas-Goodbye Miss Pudding.mp3',7,2,5);
/*!40000 ALTER TABLE `SONG` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist`
--

DROP TABLE IF EXISTS `playlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`playlist_id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_PlayUser` (`user_id`),
  CONSTRAINT `fk_PlayUser` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist`
--

LOCK TABLES `playlist` WRITE;
/*!40000 ALTER TABLE `playlist` DISABLE KEYS */;
INSERT INTO `playlist` VALUES (28,29,'xd');
/*!40000 ALTER TABLE `playlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `playlist_song`
--

DROP TABLE IF EXISTS `playlist_song`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `playlist_song` (
  `playlist_song_id` int(11) NOT NULL AUTO_INCREMENT,
  `playlist_id` int(11) DEFAULT NULL,
  `song_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`playlist_song_id`),
  KEY `fk_play_songPlaylist` (`playlist_id`),
  KEY `fk_play_SongSong` (`song_id`),
  CONSTRAINT `fk_play_songPlaylist` FOREIGN KEY (`playlist_id`) REFERENCES `playlist` (`playlist_id`),
  CONSTRAINT `fk_play_SongSong` FOREIGN KEY (`song_id`) REFERENCES `SONG` (`song_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `playlist_song`
--

LOCK TABLES `playlist_song` WRITE;
/*!40000 ALTER TABLE `playlist_song` DISABLE KEYS */;
/*!40000 ALTER TABLE `playlist_song` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `enabled` tinyint(1) DEFAULT '0',
  `active_cod` int(5) NOT NULL,
  `firstName` varchar(20) NOT NULL,
  `address` varchar(50) NOT NULL,
  `email` varchar(40) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login` (`login`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (29,'TestLogin','$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK',1,10716,'TestLogin','TestAddress','TestEmail@Test.com');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_information`
--

DROP TABLE IF EXISTS `user_information`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_information` (
  `user_information_id` int(11) NOT NULL,
  `age` int(11) DEFAULT NULL,
  `surname` varchar(20) DEFAULT NULL,
  `interest` varchar(255) DEFAULT NULL,
  `sex` varchar(100) DEFAULT 'no',
  `programming_skill` varchar(255) DEFAULT 'JAVA',
  PRIMARY KEY (`user_information_id`),
  CONSTRAINT `FK_CONSTRAINT_USER_IDD` FOREIGN KEY (`user_information_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_information`
--

LOCK TABLES `user_information` WRITE;
/*!40000 ALTER TABLE `user_information` DISABLE KEYS */;
INSERT INTO `user_information` VALUES (29,23,'TestSurname','Football,Video Game,Travel','yes','JAVA,JAVAEE');
/*!40000 ALTER TABLE `user_information` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_authority` (`login`,`authority`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (29,'TestLogin','ROLE_USER',29),(30,'TestLogin','ROLE_ADMIN',29);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-12-21 19:16:40

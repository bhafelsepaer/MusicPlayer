--Create schema musicStore; 

CREATE DATABASE IF NOT EXISTS musicStore2;
USE musicStore2;

-- Definition of table 'user'

DROP TABLE IF EXISTS `user`;
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



-- Definition of table 'user_role'

DROP TABLE IF EXISTS `user_role`;
CREATE TABLE `user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `login` varchar(20) NOT NULL,
  `authority` varchar(20) NOT NULL,
  `user_id` int(11) NOT NULL,
  PRIMARY KEY (`user_role_id`),
  UNIQUE KEY `uni_username_authority` (`login`,`authority`),
  KEY `fk_user_id` (`user_id`),
  CONSTRAINT `fk_user_id` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Definition of table 'user_information'

DROP TABLE IF EXISTS `user_information`;
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

-- Definition of table 'GENRES'

DROP TABLE IF EXISTS `GENRES`; 
CREATE TABLE `GENRES` (
  `genre_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`genre_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- Definition of table 'ALBUM'

DROP TABLE IF EXISTS `ALBUM`;
CREATE TABLE `ALBUM` (
  `album_id` int(11) NOT NULL AUTO_INCREMENT,
  `album_name` varchar(255) NOT NULL,
  `picture_metadate` varchar(255) NOT NULL,
  `release_date` int(11) DEFAULT NULL,
  PRIMARY KEY (`album_id`),
  UNIQUE KEY `album_name` (`album_name`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=latin1;

-- Definition of table 'ARTIST'

DROP TABLE IF EXISTS `ARTIST`;
CREATE TABLE `ARTIST` (
  `artist_id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL,
  `years_active` int(11) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`artist_id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=latin1;

-- Definition of table 'playlist'

DROP TABLE IF EXISTS `playlist`;
CREATE TABLE `playlist` (
  `playlist_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  PRIMARY KEY (`playlist_id`),
  UNIQUE KEY `name` (`name`),
  KEY `fk_PlayUser` (`user_id`),
  CONSTRAINT `fk_PlayUser` FOREIGN KEY (`user_id`) REFERENCES `user` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=latin1;

-- Definition of table 'playlist_song'

DROP TABLE IF EXISTS `playlist_song`;
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

-- Definition of table 'SONG'

DROP TABLE IF EXISTS `SONG`;
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

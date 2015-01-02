INSERT INTO user VALUES (DEFAULT, 12345, 'TestAddress', 'TestEmail@Test.com', 0, 'TestFirstName', 'TestLogin', '$2a$10$hhBN0WC0n.uVnFk5hqfTsOu4bnJ2UlCogeqELgib.jXqMj4dC6MsK');

INSERT INTO user_information VALUES (1, 23, 'Football, Video Game, Travel', 'JAVA, JAVAEE', 'yes', 'TestSurname');

INSERT INTO user_role VALUES (1, 'ROLE_USER', 'TestLogin', 1);

INSERT INTO ALBUM VALUES (DEFAULT, 'TestAlbum', '/TestLocation', 2000);

INSERT INTO ARTIST VALUES (DEFAULT, 'TestDescription', 'TestArtist', 1999);

INSERT INTO GENRES VALUES (DEFAULT, 'TestDescription', 'TestGenres');

INSERT INTO SONG VALUES(DEFAULT, 'TestFileNameSong', 'TestNameSong', 1 ,1 ,1);

INSERT INTO SONG VALUES(DEFAULT, 'TestFileNameSong2', 'TestNameSong2', 1 ,1 ,1);

INSERT INTO SONG VALUES(DEFAULT, 'TestFileNameSong3', 'TestNameSong3', 1 ,1 ,1);

INSERT INTO playlist VALUES (DEFAULT, 'TestPlaylist', 1);

INSERT INTO playlist VALUES (DEFAULT, 'TestPlaylist2', 1);

INSERT INTO playlist VALUES (DEFAULT, 'TestPlaylist3', 1);

INSERT INTO playlist_song VALUES(DEFAULT, 1, 1);

INSERT INTO playlist_song VALUES(DEFAULT, 1, 2);

INSERT INTO playlist_song VALUES(DEFAULT, 1, 3);


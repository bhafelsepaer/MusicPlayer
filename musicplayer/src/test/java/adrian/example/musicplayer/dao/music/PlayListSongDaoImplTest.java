package adrian.example.musicplayer.dao.music;

import static org.junit.Assert.*;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class PlayListSongDaoImplTest {

	@Autowired
	PlayListSongDao playListSongDao;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_savePlaylistSong() {
		this.playListSongDao.savePlaylistSong(1, 2);
		
		PlaylistSong playlistSong = 
				this.playListSongDao.getPlaylistSongById(2);
		
		assertEquals(2, playlistSong.getPlaylistSong_id());
		
		assertEquals("TestNameSong", playlistSong.getSong().getName());
		
		assertEquals("TestPlaylist2", playlistSong.getPlaylist().getName());
	}
	
	@Test
	public void test_getPlaylistById() {
		PlaylistSong playlistSong = 
				this.playListSongDao.getPlaylistSongById(1);
		
        assertEquals(1, playlistSong.getPlaylistSong_id());
		
		assertEquals("TestNameSong", playlistSong.getSong().getName());
		
		assertEquals("TestPlaylist", playlistSong.getPlaylist().getName());
		
	}
	
	

}

package adrian.example.musicplayer.dao.music;

import static org.junit.Assert.*;

import java.util.List;

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
		this.playListSongDao.savePlaylistSong(1, 1);
		List<PlaylistSong> playlistSong = 
				this.playListSongDao.getPlaylistSongById(1);
		
		assertEquals("Test Id from Entity PlayListSong", 
				4, playlistSong.get(3).getPlaylistSong_id());
		assertEquals("Test Song Name from Entity PlayListSong", 
				"TestNameSong", playlistSong.get(3).getSong().getName());
		assertEquals("Test Playlist Name from PlayListSong",
				"TestPlaylist", playlistSong.get(3).getPlaylist().getName());
	}
	
	@Test
	public void test_getPlaylistById() {
		List<PlaylistSong> playlistSong = 
				this.playListSongDao.getPlaylistSongById(1);
		
		assertEquals("Test Size of List playlistSong", 
				3, playlistSong.size());
        assertEquals("Test Id From PlayListSong", 
        		2, playlistSong.get(1).getPlaylistSong_id());
		assertEquals("Test Song Name from Entity PlayListSong", 
				"TestNameSong2", playlistSong.get(1).getSong().getName());
		assertEquals("Test Playlist Name from Entity PlayListSong",
				"TestPlaylist", playlistSong.get(1).getPlaylist().getName());
	}
}

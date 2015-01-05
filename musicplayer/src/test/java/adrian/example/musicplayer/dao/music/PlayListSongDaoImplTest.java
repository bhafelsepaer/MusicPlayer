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
	public void setUp() throws Exception {}

	@Test
	public void test_savePlaylistSong() {
		this.playListSongDao.savePlaylistSong(1, 2);
		List<PlaylistSong> playlistSong = 
				this.playListSongDao.getListOfPlaylistSongById(2);
		
		assertEquals("Test Id from Entity PlayListSong", 
				4, playlistSong.get(0).getPlaylistSong_id());
		assertEquals("Test Song Name from Entity PlayListSong", 
				"TestNameSong", playlistSong.get(0).getSong().getName());
		assertEquals("Test Playlist Name from PlayListSong",
				"TestPlaylist2", playlistSong.get(0).getPlaylist().getName());
	}
	
	@Test
	public void test_getListOfPlaylistSongById() {
		List<PlaylistSong> playlistSong = 
				this.playListSongDao.getListOfPlaylistSongById(1);
		
		assertEquals("Test Size of List playlistSong", 
				3, playlistSong.size());
        assertEquals("Test Id From PlayListSong", 
        		2, playlistSong.get(1).getPlaylistSong_id());
		assertEquals("Test Song Name from Entity PlayListSong", 
				"TestNameSong2", playlistSong.get(1).getSong().getName());
		assertEquals("Test Playlist Name from Entity PlayListSong",
				"TestPlaylist", playlistSong.get(1).getPlaylist().getName());
	}
	
	@Test
	public void test_getPlaylistSong() {
		PlaylistSong playlistSong = 
				this.playListSongDao.getPlaylistSong(1, 1);
		
		assertEquals("Test PlaylistSong Id", 
				1, playlistSong.getPlaylistSong_id());
		assertEquals("Test Song Id", 
				1, playlistSong.getSong().getSong_id());
		assertEquals("Test Playlist Id", 
				1, playlistSong.getPlaylist().getPlaylist_id());
		assertEquals("Test Name of Song", 
				"TestNameSong", playlistSong.getSong().getName());
		assertEquals("Test Playlist Name", 
				"TestPlaylist", playlistSong.getPlaylist().getName());
	}
	
	@Test 
	public void test_deleteSongFromPlaylistSong() {
		List<PlaylistSong> listPlaylistSongBeforeDelate = 
				this.playListSongDao.getListOfPlaylistSongById(1);
	    
		assertEquals("Test Size list of playlistSong", 
				3, listPlaylistSongBeforeDelate.size());
		
	   this.playListSongDao.deleteSongFromPlaylistSong(1, 1);
	   
	   List<PlaylistSong> listPlaylistSongAfterDelate = 
				this.playListSongDao.getListOfPlaylistSongById(1);
	   
	   assertEquals(2, listPlaylistSongAfterDelate.size());
	}
}

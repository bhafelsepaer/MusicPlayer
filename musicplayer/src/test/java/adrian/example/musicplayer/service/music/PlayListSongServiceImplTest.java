package adrian.example.musicplayer.service.music;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Arrays;
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

import com.mchange.util.AssertException;

import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class PlayListSongServiceImplTest {

	@Autowired
	PlayListSongService playListSongServiceImpl;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void test_savePlaylistSong() {
		
		assertEquals("Test size PlaylistSong id = 2 Before Save", 0, 
				this.playListSongServiceImpl.getPlaylistSongById(2).size());
        assertEquals("Check if List of PlaylistSong is empty", Arrays.asList(),  
        		this.playListSongServiceImpl.getPlaylistSongById(2));
        
		this.playListSongServiceImpl.
		     savePlaylistSong(1, 2);
		
		List<PlaylistSong> playlistSongTest = 
				this.playListSongServiceImpl.getPlaylistSongById(2);
		
		assertEquals("Test size PlaylistSong id = 2 After Save", 
				1, playlistSongTest.size());
		assertEquals("Test PlaylistSong id", 
				4, playlistSongTest.get(0).getPlaylistSong_id());
		assertEquals("Test Song id ", 
				1, playlistSongTest.get(0).getSong().getSong_id());
		assertEquals("Test Song name", 
				"TestNameSong", playlistSongTest.get(0).getSong().getName());
		assertEquals("Test Playlist id" , 
				2, playlistSongTest.get(0).getPlaylist().getPlaylist_id());
		assertEquals("Test Playlist Name", 
				"TestPlaylist2", playlistSongTest.get(0).getPlaylist().getName());
	}
	
	@Test
	public void test_getPlaylistSongById() {
		List<PlaylistSong> playlistSongTest = 
				this.playListSongServiceImpl.getPlaylistSongById(1);
		
		assertEquals("Test size PlaylistSong id = 1", 
				3, playlistSongTest.size());
		assertEquals("Test PlaylistSong id", 
				1, playlistSongTest.get(0).getPlaylistSong_id());
		assertEquals("Test Song id ", 
				1, playlistSongTest.get(0).getSong().getSong_id());
		assertEquals("Test Song name", 
				"TestNameSong", playlistSongTest.get(0).getSong().getName());
		assertEquals("Test Playlist id" , 
				1, playlistSongTest.get(0).getPlaylist().getPlaylist_id());
		assertEquals("Test Playlist Name", 
				"TestPlaylist", playlistSongTest.get(0).getPlaylist().getName());
	}
}
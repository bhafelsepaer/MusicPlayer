package adrian.example.musicplayer.service.music;

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

import adrian.example.musicplayer.model.Music.playlist.Playlist;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class PlaylistServiceImplTest {
	
	@Autowired
	PlaylistServiceImpl playListServiceImpl;
	
	@Before
	public void setUp() throws Exception {}

	@Test
	public void test_getPlaylistById() {
	    Playlist testedPlaylist = (Playlist) this.playListServiceImpl.getPlaylistById(1);
	    assertEquals("Tested Playlist id", 1, testedPlaylist.getPlaylist_id());
	    assertEquals("Tested Playlist name", "TestPlaylist", testedPlaylist.getName());
	}
	
	@Test
	public void test_savePlaylist() {
		this.playListServiceImpl.savePlaylist(1, "TestPlaylist3");
		Playlist testedPlaylist = (Playlist) this.playListServiceImpl.getPlaylistById(3);
		assertEquals("Tested id", 3, testedPlaylist.getPlaylist_id());
		assertEquals("Tested name", "TestPlaylist3", testedPlaylist.getName());
	}
	
	@Test
	public void test_updatePlaylist() {
		Playlist testedPlaylist = (Playlist) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested id before update", 1, testedPlaylist.getPlaylist_id());
		assertEquals("Tested name before update", "TestPlaylist", testedPlaylist.getName());
		
		this.playListServiceImpl.updatePlaylist(1, "UpdateTestPlaylist");
		Playlist Update_testedPlaylist = (Playlist) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested id after update", 1, Update_testedPlaylist.getPlaylist_id());
		assertEquals("Tested name after update","UpdateTestPlaylist", Update_testedPlaylist.getName());
	}
	
	@Test
	public void test_deletePlaylist() {
		List<Playlist> testedListPlaylist = (List<Playlist>) this.playListServiceImpl
				        .getPlaylistByUserId(1);
		assertEquals("Tested size before delete", 2, testedListPlaylist.size());
		
		Playlist testedPlaylist = (Playlist) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested id of playlist", 1, testedPlaylist.getPlaylist_id());
		assertEquals("Tested name of playlist", "TestPlaylist", testedPlaylist.getName());
		
		this.playListServiceImpl.deletePlaylist(1);
		
		List<Playlist> deleted_testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistByUserId(1);
		assertEquals("Tested size after delete", 1, deleted_testedPlaylist.size());
	}
}
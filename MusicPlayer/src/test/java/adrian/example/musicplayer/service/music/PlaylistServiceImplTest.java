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

import adrian.example.musicplayer.dao.music.PlayListDao;
import adrian.example.musicplayer.model.Music.playlist.Playlist;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration(defaultRollback = false)
public class PlaylistServiceImplTest {

	private static boolean setUpIsDone = false;

	
	@Autowired
	PlayListDao playlistDao;
	
	@Autowired
	PlaylistServiceImpl playListServiceImpl;
	
	@Before
	public void setUp() throws Exception {
		if(setUpIsDone) {
			return;
		}
		
		this.playlistDao.savePlaylistForJunit();
		
		setUpIsDone = true;
	}

	@Test
	public void test_getPlaylistById() {
	    List<Playlist> testedPlaylist = this.playListServiceImpl.getPlaylistById(1);
	    assertEquals("Tested id", 1, testedPlaylist.get(0).getPlaylist_id());
	    assertEquals("Tested name", "TestPlaylist", testedPlaylist.get(0).getName());
	}
	
	@Test
	public void test_savePlaylist() {
		this.playListServiceImpl.savePlaylist(1, "TestPlaylist2");
		List<Playlist> testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested size", 2, testedPlaylist.size());
		assertEquals("Tested id", 2, testedPlaylist.get(1).getPlaylist_id());
		assertEquals("Tested name", "TestPlaylist2", testedPlaylist.get(1).getName());
	}
	
	@Test
	public void test_updatePlaylist() {
		List<Playlist> testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested id before update", 1, testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name before update", "TestPlaylist", testedPlaylist.get(0).getName());
		
		this.playListServiceImpl.updatePlaylist(1, "UpdateTestPlaylist");
		List<Playlist> Update_testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested id after update", 1, Update_testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name after update","UpdateTestPlaylist", Update_testedPlaylist.get(0).getName());
	}
	
	@Test
	public void test_deletePlaylist() {
		List<Playlist> testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested size before delete", 2, testedPlaylist.size());
		assertEquals("Tested id before delete", 1, testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name before delete", "UpdateTestPlaylist", testedPlaylist.get(0).getName());
		
		this.playListServiceImpl.deletePlaylist(1);
		
		List<Playlist> deleted_testedPlaylist = (List<Playlist>) this.playListServiceImpl.getPlaylistById(1);
		assertEquals("Tested size after delete", 1, deleted_testedPlaylist.size());
		assertEquals("Tested id after delete", 2, deleted_testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name after delete", "TestPlaylist2", deleted_testedPlaylist.get(0).getName());
	}
}































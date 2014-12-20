package adrian.example.musicplayer.dao.music;

import static org.junit.Assert.*;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.BeforeClass;
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
@TransactionConfiguration(defaultRollback = false)
public class PlayListImplDaoTest {

	@Autowired
	PlayListDao playlistDao;
	
	private static boolean setUpIsDone = false;
	
	@Before
	public  void setUp() throws Exception {
		if(setUpIsDone) {
			return;
		}
		this.playlistDao.savePlaylistForJunit();
		
		setUpIsDone = true;
	}
	@Test
	public void  test_getPlaylistById() {
	    List<Playlist> testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
	    assertEquals("Tested name", "TestPlaylist", testedPlaylist.get(0).getName());
	}
	
	@Test
	public void test_savePlaylist() {
		this.playlistDao.savePlaylist(1, "TestPlaylist2");
		List<Playlist> testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
		assertEquals("Tested size", 2, testedPlaylist.size());
	}
	
	@Test 
	public void test_updatePlaylist() {
		List<Playlist> testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
		assertEquals("Tested id before update", 1, testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name before update", "TestPlaylist", testedPlaylist.get(0).getName());
		
		this.playlistDao.updatePlaylist(1, "UpdateTestPlaylist");
		List<Playlist> Update_testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
		assertEquals("Tested id after update", 1, Update_testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name after update","UpdateTestPlaylist", Update_testedPlaylist.get(0).getName());
	}
	
	@Test
	public void test_deletePlaylist() {
		List<Playlist> testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
		assertEquals("Tested size before delete", 2, testedPlaylist.size());
		assertEquals("Tested id before delete", 1, testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name before delete", "UpdateTestPlaylist", testedPlaylist.get(0).getName());
		
		this.playlistDao.deletePlaylist(1);
		
		List<Playlist> deleted_testedPlaylist = (List<Playlist>) this.playlistDao.getPlaylistById(1);
		assertEquals("Tested size after delete", 1, deleted_testedPlaylist.size());
		assertEquals("Tested id after delete", 2, deleted_testedPlaylist.get(0).getPlaylist_id());
		assertEquals("Tested name after delete", "TestPlaylist2", deleted_testedPlaylist.get(0).getName());
	}

}

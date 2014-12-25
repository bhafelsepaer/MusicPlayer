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

import adrian.example.musicplayer.model.Music.Song;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class MusicPlayerDaoImplTest {

	@Autowired
	MusicPlayerDao musicPlayer;
	
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test_getSongByGenre() {
		List<Song> loadedSong = musicPlayer.getSongByGenre("TestGenres");
		assertEquals("Tested Name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested Genres", "TestGenres", loadedSong.get(0).getGenres().getGenresName());
		assertEquals("Tested GenresDescription", "TestDescription", loadedSong.get(0).getGenres().getGenresDescription());
	}
	
	@Test
	public void test_getSongByAlbum() {
		List<Song> loadedSong = musicPlayer.getSongByAlbum("TestAlbum");
		assertEquals("Tested Name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested Album Name", "TestAlbum", loadedSong.get(0).getAlbum().getAlbum_name());
		assertEquals("Tested Metadate", "/TestLocation", loadedSong.get(0).getAlbum().getPicture_metadate());
		assertEquals("Tested Realease Date", 2000, loadedSong.get(0).getAlbum().getRelease_date());
	}
	
	@Test
	public void test_getSongByArtist() {
		List<Song> loadedSong = musicPlayer.getSongByArtist("TestArtist");
		assertEquals("Tested Name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested Artist Name", "TestArtist", loadedSong.get(0).getArtist().getName());
		assertEquals("Tested Artist Description", "TestDescription", loadedSong.get(0).
				                              getArtist().getDescription());
		assertEquals("Tested Realse Date", 1999, loadedSong.get(0).getArtist().getYears_active());
	}
	
	@Test 
	public void testLoadSongById() {
		Song loadedSong = this.musicPlayer.loadSongById(1);
		assertEquals("Tested Song ID", 1, loadedSong.getSong_id());
		assertEquals("Tested  Name", "TestNameSong", loadedSong.getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.getFilename());
	}

}

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

import adrian.example.musicplayer.model.Music.Song;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class MusicServiceImplTest {

	@Autowired
	MusicServiceImpl musicServiceImpl;
	
	@Before
	public void setUp() throws Exception {}
	
	@Test
	public void getSongByGenre() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByGenre("TestGenres");
		assertEquals("Tested name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested filename", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested GenresName", "TestGenres", loadedSong.get(0).getGenres().getGenresName());
		assertEquals("Tested Description", "TestDescription", loadedSong.get(0).getGenres().getGenresDescription());
	}
	
	@Test
	public void getSongByAlbum() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByAlbum("TestAlbum");
		assertEquals("Tested name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested AlbumName", "TestAlbum", loadedSong.get(0).getAlbum().getAlbum_name());
		assertEquals("Tested Metadate", "/TestLocation", loadedSong.get(0).getAlbum().getPicture_metadate());
		assertEquals("Tested Release_Date", 2000, loadedSong.get(0).getAlbum().getRelease_date());
	}
	
	@Test
	public void getSongByArtist() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByArtist("TestArtist");
		assertEquals("Tested Name", "TestNameSong", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.get(0).getFilename());
		assertEquals("Tested Name", "TestArtist", loadedSong.get(0).getArtist().getName());
		assertEquals("Tested Description", "TestDescription", loadedSong.get(0).
				                              getArtist().getDescription());
		assertEquals("Tested YearsActive", 1999, loadedSong.get(0).getArtist().getYears_active());
	}
	
	@Test
	public void testloadSongById() {
		Song loadedSong = this.musicServiceImpl.getSongById(1);
		assertEquals("Tested SongId", 1, loadedSong.getSong_id());
		assertEquals("Tested Name", "TestNameSong", loadedSong.getName());
		assertEquals("Tested FileName", "TestFileNameSong", loadedSong.getFilename());
	}

}

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
	
	Song song = new Song();
	
	@Before
	public void setUp() throws Exception {
		song.setFilename("/location");
		song.setName("nightcore_batman");
		musicPlayer.saveSong(song);
	}

	@Test
	public void test_getSongByGenre() {
		List<Song> loadedSong = musicPlayer.getSongByGenre("pop");
		assertEquals("Tested Name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested Genres", "pop", loadedSong.get(0).getGenres().getGenresName());
		assertEquals("Tested GenresDescription", "nice song", loadedSong.get(0).getGenres().getGenresDescription());
	}
	
	@Test
	public void test_getSongByAlbum() {
		List<Song> loadedSong = musicPlayer.getSongByAlbum("AlbumTest");
		assertEquals("Tested Name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested Album Name", "AlbumTest", loadedSong.get(0).getAlbum().getAlbum_name());
		assertEquals("Tested Metadate", "/resources/a", loadedSong.get(0).getAlbum().getPicture_metadate());
		assertEquals("Tested Realease Date", 2012, loadedSong.get(0).getAlbum().getRelease_date());
	}
	
	@Test
	public void test_getSongByArtist() {
		List<Song> loadedSong = musicPlayer.getSongByArtist("ArtistTest");
		assertEquals("Tested Name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested Artist Name", "ArtistTest", loadedSong.get(0).getArtist().getName());
		assertEquals("Tested Artist Description", "ArtistTestDescription", loadedSong.get(0).
				                              getArtist().getDescription());
		assertEquals("Tested Realse Date", 2000, loadedSong.get(0).getArtist().getYears_active());
	}
	
	@Test 
	public void testLoadSongById() {
		Song loadedSong = this.musicPlayer.loadSongById(1);
		assertEquals("Tested Song ID", 1, loadedSong.getSong_id());
		assertEquals("Tested  Name", "nightcore_batman", loadedSong.getName());
		assertEquals("Tested FileName", "/location", loadedSong.getFilename());
	}

}

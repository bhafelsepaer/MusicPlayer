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

import adrian.example.musicplayer.dao.music.MusicPlayerDao;
import adrian.example.musicplayer.model.Music.Song;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class MusicServiceImplTest {

	@Autowired
	MusicServiceImpl musicServiceImpl;
	
	Song song = new Song();
	
	@Before
	public void setUp() throws Exception {
		song.setFilename("/location");
		song.setName("nightcore_batman");
		this.musicServiceImpl.saveSong(song);
	}

	@Test
	public void getSongByGenre() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByGenre("pop");
		assertEquals("nightcore_batman", loadedSong.get(0).getName());
		assertEquals("/location", loadedSong.get(0).getFilename());
		assertEquals("pop", loadedSong.get(0).getGenres().getGenresName());
		assertEquals("nice song", loadedSong.get(0).getGenres().getGenresDescription());
	}
	
	@Test
	public void getSongByAlbum() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByAlbum("AlbumTest");
		assertEquals("nightcore_batman", loadedSong.get(0).getName());
		assertEquals("/location", loadedSong.get(0).getFilename());
		assertEquals("AlbumTest", loadedSong.get(0).getAlbum().getAlbum_name());
		assertEquals("/resources/a", loadedSong.get(0).getAlbum().getPicture_metadate());
		assertEquals(2012, loadedSong.get(0).getAlbum().getRelease_date());
	}
	
	@Test
	public void getSongByArtist() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByArtist("ArtistTest");
		assertEquals("nightcore_batman", loadedSong.get(0).getName());
		assertEquals("/location", loadedSong.get(0).getFilename());
		assertEquals("ArtistTest", loadedSong.get(0).getArtist().getName());
		assertEquals("ArtistTestDescription", loadedSong.get(0).
				                              getArtist().getDescription());
		assertEquals(2000, loadedSong.get(0).getArtist().getYears_active());
	}
	
	@Test
	public void testloadSongById() {
		Song loadedSong = this.musicServiceImpl.loadSongById(1);
		assertEquals(1, loadedSong.getSong_id());
		assertEquals("nightcore_batman", loadedSong.getName());
		assertEquals("/location", loadedSong.getFilename());
	}

}

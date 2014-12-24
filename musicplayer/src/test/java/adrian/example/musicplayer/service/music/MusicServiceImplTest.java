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
@TransactionConfiguration(defaultRollback = false)
public class MusicServiceImplTest {

	@Autowired
	MusicServiceImpl musicServiceImpl;
	
	Song song = new Song();
	
	private static boolean setUpIsDone = false;
	
	@Before
	public void setUp() throws Exception {
		if(setUpIsDone){
			return;
		}
		song.setFilename("/location");
		song.setName("nightcore_batman");
		this.musicServiceImpl.saveSong(song);
		
		setUpIsDone = true;
	}

	@Test
	public void getSongByGenre() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByGenre("pop");
		assertEquals("Tested name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested filename", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested GenresName", "pop", loadedSong.get(0).getGenres().getGenresName());
		assertEquals("Tested Description", "nice song", loadedSong.get(0).getGenres().getGenresDescription());
	}
	
	@Test
	public void getSongByAlbum() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByAlbum("AlbumTest");
		assertEquals("Tested name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested AlbumName", "AlbumTest", loadedSong.get(0).getAlbum().getAlbum_name());
		assertEquals("Tested Metadate", "/resources/a", loadedSong.get(0).getAlbum().getPicture_metadate());
		assertEquals("Tested Release_Date", 2012, loadedSong.get(0).getAlbum().getRelease_date());
	}
	
	@Test
	public void getSongByArtist() {
		List<Song> loadedSong = this.musicServiceImpl.getSongByArtist("ArtistTest");
		assertEquals("Tested Name", "nightcore_batman", loadedSong.get(0).getName());
		assertEquals("Tested FileName", "/location", loadedSong.get(0).getFilename());
		assertEquals("Tested Name", "ArtistTest", loadedSong.get(0).getArtist().getName());
		assertEquals("Tested Description", "ArtistTestDescription", loadedSong.get(0).
				                              getArtist().getDescription());
		assertEquals("Tested YearsActive", 2000, loadedSong.get(0).getArtist().getYears_active());
	}
	
	@Test
	public void testloadSongById() {
		Song loadedSong = this.musicServiceImpl.loadSongById(1);
		assertEquals("Tested SongId", 1, loadedSong.getSong_id());
		assertEquals("Tested Name", "nightcore_batman", loadedSong.getName());
		assertEquals("Tested FileName", "/location", loadedSong.getFilename());
	}

}

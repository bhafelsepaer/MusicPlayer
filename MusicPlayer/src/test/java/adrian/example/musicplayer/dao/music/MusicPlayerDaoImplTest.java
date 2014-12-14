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

import adrian.example.musicplayer.model.Music.Genres;
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
		song.setName("nightcore-batman");
		musicPlayer.saveSong(song);
		
	}

	@Test
	public void test() {
		List<Song> loadedSong = musicPlayer.getSongByGenre("pop");
		assertEquals("nightcore_batman", loadedSong.get(0).getName());
	}

}

package adrian.example.musicplayer.web.Controller.Music;

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
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.context.WebApplicationContext;

import com.sun.mail.imap.protocol.Status;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.service.music.MusicServiceImpl;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@TransactionConfiguration
@Transactional
public class OverwiewMusicControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mockMvc;
	
	@Autowired
	MusicServiceImpl musicService;
	
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.wac).build();
		
		Song song = new Song();
		song.setFilename("/location");
		song.setName("nightcore_batman");
		this.musicService.saveSong(song);
	}

	@Test
	public void testGenrePage() throws Exception {
		
		List<Song> loadedSong = this.musicService.getSongByGenre("pop");
		
		this.mockMvc.perform(get("/genre/nightcore_genre")
		            .param("genre_name","pop")
		            .sessionAttr("loadedSong", loadedSong))
		            .andExpect(view().name("musicplayer/show_song"))
		            .andExpect(status().isOk())
		            .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void testAlbumPage() throws Exception {
		List<Song> loadedSong = this.musicService.getSongByAlbum("AlbumTest");
		
		this.mockMvc.perform(get("/album/nightcore_album")
				    .param("album_name", "AlbumTest")
				    .sessionAttr("loadedSong", loadedSong))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void testArtistPage() throws Exception {
		List<Song> loadedSong = this.musicService.getSongByArtist("ArtistTest");
		
		this.mockMvc.perform(get("/artist/nightcore_artist")
				    .param("artist_name", "ArtistTest")
				    .sessionAttr("loadedSong", loadedSong))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void testPlaySong() throws Exception {
		Song song = this.musicService.loadSongById(1);
		
		this.mockMvc.perform(get("/playSong")
				    .param("song_id", "1"))
				    .andExpect(model().attributeExists("location"))
				    .andExpect(model().attributeExists("SongName"))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}

}















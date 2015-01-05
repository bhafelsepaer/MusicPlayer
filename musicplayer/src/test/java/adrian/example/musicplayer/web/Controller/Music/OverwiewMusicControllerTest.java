package adrian.example.musicplayer.web.Controller.Music;

import java.util.List;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

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
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	MusicServiceImpl musicService;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}

	@Test
	public void testGenrePage() throws Exception {
		
		User user = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		List<Song> loadedSong = this.musicService.getSongByGenre("TestGenres");
		
		this.mockMvc.perform(get("/genre/nightcore_genre")
		            .param("genre_name","TestGenres")
		            .principal(authentication))
		            .andExpect(request().
		            		sessionAttribute("loadedSong", loadedSong))
		            .andExpect(view().name("musicplayer/show_song"))
		            .andExpect(status().isOk())
		            .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void testAlbumPage() throws Exception {
		User user = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		List<Song> loadedSong = this.musicService.getSongByAlbum("TestAlbum");
		
		this.mockMvc.perform(get("/album/nightcore_album")
				    .param("album_name", "TestAlbum")
				    .principal(authentication))
				    .andExpect(request().sessionAttribute("loadedSong", loadedSong))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void testArtistPage() throws Exception {
		User user = new User("TestLogin", "", AuthorityUtils.createAuthorityList("ROLE_USER"));
		Authentication authentication = 
				new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
		
		List<Song> loadedSong = this.musicService.getSongByArtist("TestArtist");
		
		this.mockMvc.perform(get("/artist/nightcore_artist")
				    .param("artist_name", "TestArtist")
				    .principal(authentication))
				    .andExpect(request().sessionAttribute("loadedSong", loadedSong))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
	
	@Test
	public void test_showeSong() throws Exception {
		Song song = this.musicService.loadSongById(1);
		
		this.mockMvc.perform(get("/playSong")
				    .param("song_id", "1"))
				    .andExpect(model().attribute("location", song.getFilename()))
				    .andExpect(model().attribute("SongName", song.getName()))
				    .andExpect(view().name("musicplayer/show_song"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/show_song.jsp"));
	}
}

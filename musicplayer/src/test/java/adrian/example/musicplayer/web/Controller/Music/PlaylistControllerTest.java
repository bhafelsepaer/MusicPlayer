package adrian.example.musicplayer.web.Controller.Music;

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
import org.springframework.web.context.WebApplicationContext;
import static org.mockito.Mockito.*;  

import adrian.example.musicplayer.dao.music.PlayListDao;
import adrian.example.musicplayer.model.Music.playlist.Playlist;
import adrian.example.musicplayer.service.music.PlaylistService;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class PlaylistControllerTest {

	@Autowired
	private WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Autowired
	PlaylistService playlistService;
	 
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}

	@Test
	public void test_showPlaylist() throws Exception {
		List<Playlist> testedPlaylist = this.playlistService.getPlaylistByUserId(1);
		
		this.mockMvc.perform(get("/playlist/1")
				    .param("user_id", "1"))
				    .andExpect(request().sessionAttribute("playlist", testedPlaylist))
				    .andExpect(view().name("musicplayer/playlist/show_playlist"))
				    .andExpect(status().isOk())
				    .andExpect(forwardedUrl("/WEB-INF/views/musicplayer/playlist/show_playlist.jsp"));
	}
	
	@Test
	public void test_savePlaylist() throws Exception {
		this.mockMvc.perform(get("/playlist/1/save_playlist")
				    .param("user_id", "1")
				    .param("playlistName", "TestedPlaylist"))
				    .andExpect(status().isOk())
				    .andExpect(content().string("saved_playlist TestedPlaylist"));
	}
	
	@Test
	public void test_updatePlaylist() throws Exception {
		this.mockMvc.perform(get("/playlist/update")
				    .param("playlist_id", "1")
				    .param("playlist_name", "UpdateTestPlaylist"))
				    .andExpect(status().isOk())
				    .andExpect(content().string("update_playlist UpdateTestPlaylist"));
	}
	
	@Test 
	public void test_deletePlaylist() throws Exception {
		this.mockMvc.perform(get("/playlist/delete")
				    .param("playlist_id", "1")
				    .param("playlist_name", "UpdateTestPlaylist"))
				    .andExpect(status().isOk())
				    .andExpect(content().string("delete_playlist UpdateTestPlaylist"));
	}

}

package adrian.example.musicplayer.web.Controller.Music;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

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


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import adrian.example.musicplayer.service.music.PlayListSongService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration
@Transactional
@TransactionConfiguration
public class PlaylistSongControllerTest {

	@Autowired
	PlayListSongService playListSongService;
	
	@Autowired
	WebApplicationContext applicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
		
	}

	@Test
	public void test_saveSongToPlaylist() throws Exception {
		
		this.mockMvc.perform(get("/playlist/saveSongToPlaylist")
				    .param("song_id", "1")
				    .param("playlist_id", "2"))
				    .andExpect(status().isOk())
				    .andExpect(content().string("succed"));
	}
	
	@Test 
	public void test_getSongPlaylist() throws Exception {
		
		this.mockMvc.perform(get("/playlist/showPlaylistSong/1"))
		            .andExpect(model().attributeExists("SongInPlaylist"))
		            .andExpect(status().isOk())
		            .andExpect(view().name("musicplayer/playlist/showSongInPlaylist"))
		            .andExpect(forwardedUrl
		            		("/WEB-INF/views/musicplayer/playlist/showSongInPlaylist.jsp"));
	}

}

package adrian.example.musicplayer.web.Controller.Music;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import javax.transaction.Transactional;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.RateSongService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Transactional
@TransactionConfiguration
public class SongOperationControllerTest {
	
	@Autowired
	WebApplicationContext applicationContext;
	
	@Autowired
	MusicService musicService;
	
	@Autowired
	RateSongService rateSongService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
	}
	
	@Test
	public void test_userRatingSong() throws Exception {
		int song_id = 1;
		int user_id = 1;
		int ratingSong = 4;
		Song song = this.musicService.getSongById(song_id);
		
		this.mockMvc.perform(get("/song/rateSongByUser")
				    .param("user_id","" + user_id)
				    .param("song_id","" + song_id)
				    .param("ratingSong","" + ratingSong))
				    .andExpect(status().isOk())
				    .andExpect(content().string(
				    		"succed rate Song " + song.getName()));
	}
	
	@Test
	public void test_showRatingSongbyUser() throws Exception {
		int user_id = 1;
		int song_id = 1;
		
		double rating = 
				this.rateSongService
				.getRateSongByUser(user_id, song_id);
		
		this.mockMvc.perform(get("/song/showRatingSongbyUser")
				    .param("user_id", "" + user_id)
				    .param("song_id", "" + song_id))
				    .andExpect(status().isOk())
				    .andExpect(content().string(
				    		"" + rating));
	}
	
	@Test
	public void showAverageSongRating() throws Exception {
		int song_id = 1;
		
		double rating = this.rateSongService
				            .getAverageSongRating(song_id);
		
		this.mockMvc.perform(get("/song//showAverageRatingSong")
				    .param("song_id", "" + song_id))
				    .andExpect(status().isOk())
				    .andExpect(content().string(
				    		"" + rating));
		           
	}
	

}

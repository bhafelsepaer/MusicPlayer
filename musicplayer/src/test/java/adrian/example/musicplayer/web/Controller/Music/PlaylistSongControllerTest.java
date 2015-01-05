package adrian.example.musicplayer.web.Controller.Music;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

import java.util.ArrayList;
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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.model.Music.playlist.Playlist;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.PlayListSongService;
import adrian.example.musicplayer.service.music.PlaylistService;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
@WebAppConfiguration
@Transactional
@TransactionConfiguration
public class PlaylistSongControllerTest {

	@Autowired
	PlayListSongService playListSongService;
	
	@Autowired
	WebApplicationContext applicationContext;
	
	@Autowired
	private MusicService musicService;
	
	@Autowired
	private PlaylistService playlistService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setUp() throws Exception {
		this.mockMvc = webAppContextSetup(this.applicationContext).build();
    }

	@Test
	public void test_saveSongToPlaylist() throws Exception {
		
		int song_id = 1;
		int playlist_id = 2;
		
		Song song = 
				this.musicService.loadSongById(song_id);
		
		Playlist playlist = 
				this.playlistService.getPlaylistById(playlist_id);
		
		this.mockMvc.perform(get("/playlist/saveSongToPlaylist")
				    .param("song_id", "" + song_id)
				    .param("playlist_id", "" + playlist_id))
				    .andExpect(status().isOk())
				    .andExpect(content().string("succed save song: " + song.getName() + " to playlist: " + playlist.getName()));
	}
	
	@Test 
	public void test_getSongPlaylist() throws Exception {
		
		int playlist_id = 1;
		
		List<PlaylistSong> playlistSong = 
				(List<PlaylistSong>) this.playListSongService
				      .getListOfPlaylistSongById(playlist_id);
		
		List<Song> song = new ArrayList<Song>();
		
		
		for(int i = 0; i < playlistSong.size(); i++){
			song.add(playlistSong.get(i).getSong());
		}
		
		this.mockMvc.perform(get("/playlist/showPlaylistSong/" + playlist_id))
		            .andExpect(request().sessionAttribute("songInPlaylist", song))
		            .andExpect(status().isOk())
		            .andExpect(view().name("musicplayer/playlist/showSongInPlaylist"))
		            .andExpect(forwardedUrl
		            		("/WEB-INF/views/musicplayer/playlist/showSongInPlaylist.jsp"));
	}
	
	@Test
	public void test_playSongFromPlaylist() throws Exception {
		int song_id = 1;
		
		Song song = 
				this.musicService.loadSongById(song_id);
		
		this.mockMvc.perform(get("/playlist/playSongFromPlaylist")
				.param("song_id", "" + song_id))
				.andExpect(model().attribute("location", song.getFilename()))
				.andExpect(model().attribute("SongName", song.getName()))
				.andExpect(status().isOk())
				.andExpect(view().name("musicplayer/playlist/showSongInPlaylist"))
				.andExpect(forwardedUrl
						("/WEB-INF/views/musicplayer/playlist/showSongInPlaylist.jsp"));
	}
}

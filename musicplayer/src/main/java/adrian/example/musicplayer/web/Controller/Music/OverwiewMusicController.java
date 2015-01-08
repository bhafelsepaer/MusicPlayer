package adrian.example.musicplayer.web.Controller.Music;

import java.security.Principal;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.PlaylistService;
import adrian.example.musicplayer.service.user.UserService;

@Controller
@SessionAttributes("song")
public class OverwiewMusicController {

	@Autowired
	MusicService musicService;
	
	@Autowired
	PlaylistService playlistService;
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "/genre/nightcore_genre", method = RequestMethod.GET)
	public String genrePage(
			@RequestParam(value = "genre_name", required = true) String genre_name,
			HttpSession session, Principal principal) {
		
		session.setAttribute("loadedSong", this.musicService.getSongByGenre(genre_name));
		if(principal != null){
			User user = this.userService.findLogin(principal.getName());
		    session.setAttribute("loadedPlaylist", 
				this.playlistService.getPlaylistByUserId(user.getUser_id()));
		}
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/album/nightcore_album", method = RequestMethod.GET)
	public String albumPage(
			@RequestParam(value = "album_name", required = true) String album_name,
		    HttpSession session, Principal principal){
		
		session.setAttribute("loadedSong", this.musicService.getSongByAlbum(album_name));
		if(principal != null){
			User user = this.userService.findLogin(principal.getName());
		    session.setAttribute("loadedPlaylist", 
				this.playlistService.getPlaylistByUserId(user.getUser_id()));
		}
		
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/artist/nightcore_artist", method = RequestMethod.GET)
	public String artistPage(
			@RequestParam(value = "artist_name", required = true) String artist_name,
		    HttpSession session, Principal principal){
		
		session.setAttribute("loadedSong", this.musicService.getSongByArtist(artist_name));
		if(principal != null){
			User user = this.userService.findLogin(principal.getName());
		    session.setAttribute("loadedPlaylist", 
				this.playlistService.getPlaylistByUserId(user.getUser_id()));
		}
		
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/playSong", method = RequestMethod.GET)
	public String showSong(@RequestParam(value="song_id", required = true)
	                         int song_id, Model model) {
		
		Song song = this.musicService.getSongById(song_id);
		model.addAttribute("location", song.getFilename());
		model.addAttribute("SongName", song.getName());
		
		return "musicplayer/show_song";
	}
}

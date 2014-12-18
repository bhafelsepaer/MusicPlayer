package adrian.example.musicplayer.web.Controller.Music;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.PlaylistService;

@Controller
@SessionAttributes("song")
public class OverwiewMusicController {

	@Autowired
	MusicService musicService;
	
	
	@RequestMapping(value = "/genre/nightcore_genre", method = RequestMethod.GET)
	public String genrePage(
			@RequestParam(value = "genre_name", required = true) String genre_name,
			HttpSession session) {
		
		session.setAttribute("loadedSong", this.musicService.getSongByGenre(genre_name));
		
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/album/nightcore_album", method = RequestMethod.GET)
	public String albumPage(
			@RequestParam(value = "album_name", required = true) String album_name,
		    HttpSession session){
		
		session.setAttribute("loadedSong", this.musicService.getSongByAlbum(album_name));
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/artist/nightcore_artist", method = RequestMethod.GET)
	public String artistPage(
			@RequestParam(value = "artist_name", required = true) String artist_name,
		    HttpSession session){
		
		session.setAttribute("loadedSong", this.musicService.getSongByArtist(artist_name));
		return "musicplayer/show_song";
	}
	
	@RequestMapping(value = "/playSong", method = RequestMethod.GET)
	public String choiseSong(@RequestParam(value="song_id", required = true)
	                         int song_id, Model model){
		
		Song song = musicService.loadSongById(song_id);
		model.addAttribute("location",song.getFilename());
		model.addAttribute("SongName", song.getName());
		return "musicplayer/show_song";
	}
}

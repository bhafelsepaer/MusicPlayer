package adrian.example.musicplayer.web.Controller.Music;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.service.music.MusicService;

@Controller
@SessionAttributes("song")
public class OverwiewMusicController {

	@Autowired
	MusicService musicService;
	
	@RequestMapping(value = "/genre/nightcore_genre", method = RequestMethod.GET)
	public String overwiewHomePage(
			@RequestParam(value = "genre_name", required = true)String genre_name,
			Model model,HttpSession session) {
		
		List<Song> loadedSongByGenre = this.musicService.getSongByGenre(genre_name);
		session.setAttribute("loadedSongByGenre", loadedSongByGenre);
	/*	model.addAttribute("loadedSongByGenre",loadedSongByGenre);*/
		
		return "/musicplayer/show_song";
	}
	
	@RequestMapping(value = "/playSong", method = RequestMethod.GET)
	public String choiseSong(@RequestParam(value="song_id", required = true)
	                         int song_id,Model model, HttpSession session){
		
		Song song = musicService.loadSongById(song_id);
		model.addAttribute("location",song.getFilename());
		model.addAttribute("SongName", song.getName());
		
		return "/musicplayer/show_song";
	}
}

package adrian.example.musicplayer.web.Controller.Music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.RateSongService;

@Controller
@RequestMapping("/song")
@SessionAttributes("user")
public class SongOperationController {
     
	@Autowired
	RateSongService rateSongService;
	
	@Autowired MusicService musicService;
	
	@RequestMapping(value = "/rateSongByUser", method = RequestMethod.GET)
	@ResponseBody
	public String userRatingSong(
			@RequestParam(value = "user_id") int user_id,
			@RequestParam(value = "song_id") int song_id,
			@RequestParam(value = "ratingSong") int ratingSong){
		
		this.rateSongService.saveRatingSong(user_id, song_id, ratingSong);
		
		Song song = this.musicService.getSongById(song_id);
	
		return "succed rate Song " + song.getName();
	}
}

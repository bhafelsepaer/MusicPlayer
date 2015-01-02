package adrian.example.musicplayer.web.Controller.Music;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import adrian.example.musicplayer.service.music.PlayListSongService;

@Controller
@RequestMapping(value = "/playlist")
public class PlaylistSongController {
	
	@Autowired
	PlayListSongService playListSongService;
	
	@RequestMapping(value  = "/saveSongToPlaylist", method = RequestMethod.GET)
	@ResponseBody
	public String saveSongToPlaylist(
			@RequestParam(value = "song_id", required = true) int song_id,
			@RequestParam(value = "playlist_id", required = true) int playlist_id){
		
		this.playListSongService.savePlaylistSong(song_id, playlist_id);
		return "succed";
	}
	
	@RequestMapping(value = "/getSongPlaylist", method = RequestMethod.GET)
	public String getSongPlaylist(
			@RequestParam(value = "playlist_id", required = true) int  playlist_id){

		  
		return null;
	}

}

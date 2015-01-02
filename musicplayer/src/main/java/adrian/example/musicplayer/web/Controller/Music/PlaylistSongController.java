package adrian.example.musicplayer.web.Controller.Music;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import adrian.example.musicplayer.model.Music.Song;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;
import adrian.example.musicplayer.service.music.PlayListSongService;

@Controller
@RequestMapping(value = "/playlist")
@SessionAttributes("user")
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
	
	@RequestMapping(value = "/showPlaylistSong/{playlist_id}", method = RequestMethod.GET)
	public String getSongPlaylist(
			@PathVariable(value = "playlist_id") int  playlist_id,
			Model model){

		List<PlaylistSong> playlistSong = 
				(List<PlaylistSong>) this.playListSongService.getPlaylistSongById(playlist_id);
		List<Song> song = new ArrayList<Song>();
		
		for(int i = 0; i < playlistSong.size(); i++){
			song.add(playlistSong.get(i).getSong());
		}
		
		model.addAttribute("SongInPlaylist", song);
		
		return "musicplayer/playlist/showSongInPlaylist";
	}
}

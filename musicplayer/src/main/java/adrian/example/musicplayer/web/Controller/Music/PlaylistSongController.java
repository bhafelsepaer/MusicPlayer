package adrian.example.musicplayer.web.Controller.Music;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;

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
import adrian.example.musicplayer.model.Music.playlist.Playlist;
import adrian.example.musicplayer.model.Music.playlist.PlaylistSong;
import adrian.example.musicplayer.service.music.MusicService;
import adrian.example.musicplayer.service.music.PlayListSongService;
import adrian.example.musicplayer.service.music.PlaylistService;

@Controller
@RequestMapping(value = "/playlist")
@SessionAttributes({"user"})
public class PlaylistSongController {
	
	@Autowired
	PlayListSongService playListSongService;
	
	@Autowired
	MusicService musicService;
	
	@Autowired
	PlaylistService playlistService;
	
	@RequestMapping(value  = "/saveSongToPlaylist", method = RequestMethod.GET)
	@ResponseBody
	public String saveSongToPlaylist(
			@RequestParam(value = "song_id", required = true) int song_id,
			@RequestParam(value = "playlist_id", required = true) int playlist_id){
		
		this.playListSongService.savePlaylistSong(song_id, playlist_id);
        Song song = this.musicService.loadSongById(song_id);		
        Playlist playlist = this.playlistService.getPlaylistById(playlist_id);
        
		return "succed save song: " + song.getName() + " to playlist: " + playlist.getName();
	}
	
	@RequestMapping(value = "/showPlaylistSong/{playlist_id}", method = RequestMethod.GET)
	public String getSongPlaylist(@PathVariable(value = "playlist_id") int  playlist_id,
			HttpSession session){

		List<PlaylistSong> playlistSong = 
				(List<PlaylistSong>) this.playListSongService.getListOfPlaylistSongById(playlist_id);
		List<Song> song = new ArrayList<Song>();
		
		for(int i = 0; i < playlistSong.size(); i++){
			song.add(playlistSong.get(i).getSong());
		}
		
		session.setAttribute("songInPlaylist", song);
		
		return "musicplayer/playlist/showSongInPlaylist";
	}
	
	@RequestMapping(value = "/playSongFromPlaylist", method = RequestMethod.GET)
	public String playSongFromPlaylist(
			@RequestParam(value = "song_id", required = true) int song_id,
			Model model){
		
		Song song = this.musicService.loadSongById(song_id);
		model.addAttribute("location",song.getFilename());
		model.addAttribute("SongName", song.getName());
		
		return  "musicplayer/playlist/showSongInPlaylist";
	}
}

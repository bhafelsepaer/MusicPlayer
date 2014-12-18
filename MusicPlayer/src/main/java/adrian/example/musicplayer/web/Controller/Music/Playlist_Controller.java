package adrian.example.musicplayer.web.Controller.Music;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import adrian.example.musicplayer.service.music.PlaylistService;

@Controller
public class Playlist_Controller {
	
	@Autowired
	PlaylistService playListService;
	
	@RequestMapping(value = "/playlist/{user_id}", method = RequestMethod.GET)
	public String showPlaylist(@PathVariable int user_id, HttpSession session, 
			Model model) {
		
		session.setAttribute("playlist", this.playListService.getPlaylistById(user_id));
		//model.addAttribute("user_id",user_id);
		return "musicplayer/playlist/show_playlist";
	}
	
	@RequestMapping(value = "/playlist/{user_id}/save_playlist", 
			       method = RequestMethod.GET)  
	public String savePlaylist(@PathVariable("user_id") int user_id,
			      @RequestParam(value = "playlistName", required = true) String playlistName) {
		    
		this.playListService.savePlaylist(user_id, playlistName);
		
		return "musicplayer/playlist/show_playlist";
	}
	
	@RequestMapping(value = "/playlist/{playlist_id}/update", method = RequestMethod.GET)
	public String updatePlaylist(@PathVariable int playlist_id,
		   @RequestParam(value = "playlist_name", required = true) String playlistName_updatable){
		
		this.playListService.updatePlaylist(playlist_id, playlistName_updatable);
		return "musicplayer/playlist/show_playlist";
		
	}
	
	@RequestMapping(value = "/playlist/{playlist_id}/delete", method = RequestMethod.GET)
	public String delatePlaylist(@PathVariable int playlist_id){
		
		this.playListService.deletePlaylist(playlist_id);
		
		return "musicplayer/playlist/show_playlist";
	}
	

}

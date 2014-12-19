package adrian.example.musicplayer.web.Controller.Music;


import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
	@ResponseBody
	public String savePlaylist(@PathVariable("user_id") int user_id,
			      @RequestParam(value = "playlistName", required = true) String playlistName) {
		    
		this.playListService.savePlaylist(user_id, playlistName);
		
		return "saved_playlist " + playlistName;
	}
	
	@RequestMapping(value = "/playlist/update", method = RequestMethod.GET)
	@ResponseBody
	public String updatePlaylist(
		   @RequestParam(value = "playlist_id", required = true) int playlist_id,
		   @RequestParam(value = "playlist_name", required = true) String playlistName_updatable){
		
		this.playListService.updatePlaylist(playlist_id, playlistName_updatable);
		return "update_playlist " + playlistName_updatable;
	}
	
	@RequestMapping(value = "/playlist/delete", method = RequestMethod.GET)
	@ResponseBody
	public String delatePlaylist(
			@RequestParam(value = "playlist_id", required = true) int playlist_id,
			@RequestParam(value = "playlist_name", required = true) String playlist_name){
		
		this.playListService.deletePlaylist(playlist_id);
		
		return "delete_playlist " + playlist_name;
	}
}

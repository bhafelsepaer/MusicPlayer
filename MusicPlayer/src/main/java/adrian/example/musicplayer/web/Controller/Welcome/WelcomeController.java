package adrian.example.musicplayer.web.Controller.Welcome;

import java.security.Principal;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adrian.example.musicplayer.service.music.PlaylistService;
import adrian.example.musicplayer.service.user.UserService;

@Controller
public class WelcomeController {
	
	@Autowired
	UserService userService;
	
	@Autowired 
	PlaylistService playListService;
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String home(Model model, Principal principal, 
			HttpSession session) {
         
		if(principal != null){
			int user_id = userService.findLogin(principal.getName()).getUser_id();
			session.setAttribute("user_id", user_id);
			session.setAttribute("playlist", this.playListService.getPlaylistById(user_id));
			
		}
		
		return "home";
	}
}

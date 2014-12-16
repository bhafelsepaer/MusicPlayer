package adrian.example.musicplayer.web.Controller.Authorization;

import java.security.Principal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import adrian.example.musicplayer.model.User.User;

@Controller
public class LoginController {

	@RequestMapping(value="/login", method=RequestMethod.GET)
	public String loginFormGet(Principal principal){
		
		//check if user already loggin,
		//if true redirect to home page
		if(principal != null){
			return "redirect:/";
		}
		
		return "authorization/login";
	}
	
	@RequestMapping(value="/login", method=RequestMethod.POST)
	public String loginFormPost(@ModelAttribute("user") User user){
		return "redirect:/";
	}		
}

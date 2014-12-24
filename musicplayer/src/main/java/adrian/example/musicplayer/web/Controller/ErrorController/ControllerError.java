package adrian.example.musicplayer.web.Controller.ErrorController;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ControllerError {

	@RequestMapping(value = "/error403", method = RequestMethod.GET)
	public String handle403ErrorPage(){
		
		return "/error/403";
	}
}

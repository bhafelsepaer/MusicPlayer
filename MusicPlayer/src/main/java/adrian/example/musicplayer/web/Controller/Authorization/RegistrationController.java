package adrian.example.musicplayer.web.Controller.Authorization;

import java.security.Principal;

import javax.validation.Valid;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.User.DefaultValidation;
import adrian.example.musicplayer.model.User.User.MailValidation;
import adrian.example.musicplayer.model.User.User.PasswordValidation;
import adrian.example.musicplayer.service.user.UserService;
import adrian.example.musicplayer.service.user.ActiveAccount.ActiveAccount;

@Controller
@SessionAttributes("user")
public class RegistrationController {
	
	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;

	@Autowired
	BeanFactory beanFactory;
	
	@RequestMapping(value = "/registration", method = RequestMethod.GET)
	public String registrationPageGet(Principal principal, Model model){

		//check if user already loggin,
		//if true redirect to home page
		if(principal != null){
			return "redirect:/";
		}		
		
		User user = new User();
		model.addAttribute("user", user);
		
		return "authorization/registration";
	}
	
	@RequestMapping(value = "/registration", method = RequestMethod.POST)
	public String registrationPagePost(@RequestParam(value = "strategyPattern", required = true) 
			                            String strategyActivePattern,
                            			@ModelAttribute("user") @Validated({DefaultValidation.class, 
			                            PasswordValidation.class, MailValidation.class})
			                            User user,  BindingResult result, SessionStatus sessionStatus){
		
		if(result.hasErrors()){
			return "authorization/registration";
		}else{
		     userService.saveUser(user);
		     ActiveAccount activeAccountStrategy = beanFactory.getBean(strategyActivePattern, ActiveAccount.class);
		     activeAccountStrategy.activeAccount(user.getUser_id(), user.getActive_cod(), user.getEmail());
		     sessionStatus.setComplete();
		}
		
		return "redirect:/";
	}	
}

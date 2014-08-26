package adrian.example.musicplayer.web.Controller.UserAdministration;

import java.security.Principal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import adrian.example.musicplayer.model.User.User;
import adrian.example.musicplayer.model.User.UserInformation;
import adrian.example.musicplayer.model.User.User.FirstNameAndAddressValidation;
import adrian.example.musicplayer.model.User.User.MailMatchValidation;
import adrian.example.musicplayer.model.User.User.PasswordValidation;
import adrian.example.musicplayer.service.UserInformationServiceList;
import adrian.example.musicplayer.service.AdministrationAccount.UserAdministrationService;
import adrian.example.musicplayer.service.user.ActiveAccount.ActiveAccount;
import adrian.example.musicplayer.service.user.ActiveAccount.ActiveAccountByEmail;

@Controller
@SessionAttributes({"user","user_information"})
public class UserSettingsController {
	
	private String messageError = " you are not logged user";
	
	@Autowired
	@Qualifier("userAdministrationServiceImpl")
	UserAdministrationService userAdministrationService;
	
	@Autowired
	@Qualifier("userInformationServiceListImpl")
	UserInformationServiceList userInformationServiceList;
	
	@RequestMapping(value = "/verify", method = RequestMethod.GET)
	public String activeUserFromEmail(
			@RequestParam(value = "user_id" , required = true) int user_id,
			@RequestParam(value = "active_code", required = true) int active_code,
			RedirectAttributes redirectAttribustes){
		
		if(!(userAdministrationService.checkUserVerify(user_id, active_code))){    //redirect to error 403 if                                                          
			redirectAttribustes.addFlashAttribute("errorMessage",                  //user not found
					"User not found or active_code not match");
			return "redirect:/error403";                                           
		}
		
		userAdministrationService.setEnabledTrue(user_id);
		
		return "redirect:/";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/profile", method = RequestMethod.GET)
	public String settingsUserHomePage(@PathVariable("name") String login, 
			                           Model model, Principal principal,
			                           RedirectAttributes redirectAttributes){
		
		if(!(principal.getName().equals(login))){
			redirectAttributes.addFlashAttribute("errorMessage", 
					login + messageError);
			
			 return "redirect:/error403";
		}
		
		User user = userAdministrationService.findLogin(login);
		model.addAttribute("user", user);
		return "user_setting/user_settingProfil";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/profile", method = RequestMethod.POST)
	public String settingUserProfilePage(
			@ModelAttribute("user") @Validated(FirstNameAndAddressValidation.class) User user,
			BindingResult result){
		
		
		 if(result.hasErrors()){
			 return "user_setting/user_settingProfil";
		 }
		 
		userAdministrationService.updateUserInformation(
				                        user.getUser_id(), 
				                        user.getAddress(), 
			                        	user.getFirstName());
		
		return "redirect:/settings_account_user/" + user.getLogin() + "/profile";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/password", method = RequestMethod.GET)
	public String settingUserPassword(@PathVariable("name") String login,
			                          Model model,
			                          Principal principal,
			                          RedirectAttributes redirectAttributes){
		
		if(!(principal.getName().equals(login))){
			redirectAttributes.addFlashAttribute("errorMessage", 
					login + messageError);
			
			 return "redirect:/error403";
		}
		
		User user = userAdministrationService.findLogin(login);
		model.addAttribute("user", user);
		
		return "user_setting/user_settignPassword";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/password", method = RequestMethod.POST)
	public String settingUserPassword(@RequestParam(value = "user_password", required = true)
                                      String CurrentPassword,
			                          @ModelAttribute("user") 
	                                  @Validated(PasswordValidation.class) User user,
		                              BindingResult result, Model model){
		
		if(!(userAdministrationService.checkPassword(user.getUser_id(),CurrentPassword))){
			model.addAttribute("wrongCurrentPassword", "Wrong Password");
		}
		
		if(result.hasErrors()){
			return "user_setting/user_settignPassword";
		}
		
		userAdministrationService.updatePassword(user.getUser_id(), user.getPassword());
		
		return "redirect:/settings_account_user/" + user.getLogin() +  "/password";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/email", method = RequestMethod.GET)
	public String settingUserEmail(@PathVariable("name") String login,Model model,Principal principal,
            RedirectAttributes redirectAttributes){
		
		if(!(principal.getName().equals(login))){
			redirectAttributes.addFlashAttribute("errorMessage", 
					login + messageError);
			
			 return "redirect:/error403";
		}
		
		User user = userAdministrationService.findLogin(login);
		model.addAttribute("user", user);
		
		return "user_setting/user_settingEmail";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/email", method = RequestMethod.POST)
	public String settingUserEmail(@ModelAttribute("user") @Validated(
			                          MailMatchValidation.class) User user,
		                              BindingResult result, Model model){
		
		if(!(userAdministrationService.checkPassword(user.getUser_id(),user.getPassword()))){
			model.addAttribute("wrongCurrentPassword", "Wrong Password");
		}
		
		if(result.hasErrors()){
			return "user_setting/user_settingEmail";
		}
		
		userAdministrationService.updateEmail(user.getUser_id(), user.getEmail());
		
		return "redirect:/settings_account_user/" + user.getLogin() +  "/email";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/userInformation", method = RequestMethod.GET)
	public String settingUserInformation(@PathVariable("name") String login,
			                             Model model,Principal principal,
                                         RedirectAttributes redirectAttributes){
		
		if(!(principal.getName().equals(login))){
			redirectAttributes.addFlashAttribute("errorMessage", 
					login + messageError);
			
			 return "redirect:/error403";
		}
		
		UserInformation userInformation = new UserInformation();
		model.addAttribute("userInformation", userInformation);
		
		List<String> listInterest = userInformationServiceList.getInterest();
		List<String> listProgrammingSkill = userInformationServiceList.getProgrammingStyle();
		
		model.addAttribute("interestList", listInterest);
		model.addAttribute("programmingSkillList", listProgrammingSkill);
		
		return "user_setting/user_settingInformation";
	}
	
	@RequestMapping(value = "/settings_account_user/{name}/userInformation", method = RequestMethod.POST)
	public String settingUserEmail(@ModelAttribute("userInformation") UserInformation userInformation, 
			                      Model model,Principal principal,SessionStatus status){
		
		String loginUser = principal.getName();
		
		int user_id = userAdministrationService.getUser_id(loginUser);
		
		userAdministrationService.updateOrSaveUserInformation(user_id, userInformation);
		
		return "redirect:/settings_account_user/" + principal.getName() + "/userInformation";
	}
}

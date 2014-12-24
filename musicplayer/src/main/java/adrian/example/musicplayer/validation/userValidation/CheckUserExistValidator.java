package adrian.example.musicplayer.validation.userValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import adrian.example.musicplayer.service.user.UserService;

@Component
public class CheckUserExistValidator implements ConstraintValidator<CheckUserExist, String>{

	@Autowired
	@Qualifier("userServiceImpl")
	UserService userService;
	
	@Override
	public void initialize(CheckUserExist checkUserExist) {	
	}

	@Override
	public boolean isValid(String login, ConstraintValidatorContext context) {
	   		
		if(userService.findLogin(login) != null){
			return false;
		}
		
		return true;
	}
}

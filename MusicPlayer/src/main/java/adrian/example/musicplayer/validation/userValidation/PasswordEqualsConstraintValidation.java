package adrian.example.musicplayer.validation.userValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


import adrian.example.musicplayer.model.User.User;

public class PasswordEqualsConstraintValidation 
     implements ConstraintValidator<PasswordEqualsConstraint, Object>{

	@Override
	public void initialize(PasswordEqualsConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		
		User user = (User) value;
		
		if(!(user.getPassword().equals(user.getConfirmPassword()))){		
			return false;
		}
		
		return true;
	}
}

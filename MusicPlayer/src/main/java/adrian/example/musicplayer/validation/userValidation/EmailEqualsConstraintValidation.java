package adrian.example.musicplayer.validation.userValidation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import adrian.example.musicplayer.model.User.User;

public class EmailEqualsConstraintValidation implements ConstraintValidator<EmailEqualsConstraint, Object>{

	@Override
	public void initialize(EmailEqualsConstraint constraintAnnotation) {
	}

	@Override
	public boolean isValid(Object value, ConstraintValidatorContext context) {
		User user = (User) value;
		
		if(!(user.getEmail().equals(user.getConfirmEmail()))){
			return false;
		}
		
		return true;
	}

}

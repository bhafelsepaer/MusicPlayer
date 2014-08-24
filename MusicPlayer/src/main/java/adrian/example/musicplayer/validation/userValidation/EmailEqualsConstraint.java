package adrian.example.musicplayer.validation.userValidation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

@Documented
@Constraint(validatedBy = EmailEqualsConstraintValidation.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface EmailEqualsConstraint {

    String message() default "{Default EmailEqualsConstraint}";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
}

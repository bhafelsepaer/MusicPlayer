package adrian.example.musicplayer.web.Controller.ErrorController;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public ResponseEntity<String> handleConfilct(){
	        return new ResponseEntity<String>(
	                "This Song is already in this playlist",
	                HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(NullPointerException.class)
	 ResponseEntity<String> customHandler(Exception ex) {
        return new ResponseEntity<String>(
                "Sorry, you must rating first",
                HttpStatus.INTERNAL_SERVER_ERROR);
      } 
}

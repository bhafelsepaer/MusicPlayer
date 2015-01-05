package adrian.example.musicplayer.web.Controller.ErrorController;


import java.io.IOException;

import javax.servlet.http.HttpServletResponse;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControllerExceptionHandler {

	@ExceptionHandler(ConstraintViolationException.class)
	public void hadleConfilct(HttpServletResponse response) throws IOException{
       response.sendError(409);		
	}
}

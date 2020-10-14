package funShop.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class CustomExceptionHandler extends ResponseEntityExceptionHandler{
	
	@ExceptionHandler
	public final ResponseEntity<Object> handleExceptionEntity (ProductException productException, WebRequest request){
		var exceptionRes=new ExceptionRes(productException.getMessage());
		return new ResponseEntity<Object>(exceptionRes, HttpStatus.BAD_REQUEST);
	}

}

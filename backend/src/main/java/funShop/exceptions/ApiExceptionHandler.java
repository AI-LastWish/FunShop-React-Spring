package funShop.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@RestControllerAdvice
public class ApiExceptionHandler {

	
	@ExceptionHandler(NoSuchElementException.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
    public ErrorMessage handleProductNotFoundException(Exception ex, WebRequest request) {
        return new ErrorMessage("Product not found");
    }
	
	@ExceptionHandler(Exception.class)
	@ResponseStatus(value=HttpStatus.BAD_REQUEST)
	public ErrorMessage handleAllException(Exception ex, WebRequest request) {
		return new ErrorMessage(ex.getLocalizedMessage());
	}
}

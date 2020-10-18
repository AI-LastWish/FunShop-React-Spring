package funShop.exceptions;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;

import funShop.security.JwtAuthenticationEntryPoint;

@RestControllerAdvice
public class ApiExceptionHandler {

	@ExceptionHandler(BadCredentialsException.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public void handleWrongPasswordRequest(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
		jwtAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
	}
	
	@ExceptionHandler(Unauthorized.class)
	@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
	public void handleWrongUsernameRequest(HttpServletRequest httpServletRequest,
			HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
		JwtAuthenticationEntryPoint jwtAuthenticationEntryPoint = new JwtAuthenticationEntryPoint();
		jwtAuthenticationEntryPoint.commence(httpServletRequest, httpServletResponse, e);
	}

	@ExceptionHandler(Exception.class)
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	public ErrorMessage handleAllException(Exception ex, WebRequest request) {
		return new ErrorMessage(ex.getLocalizedMessage());
	}
}

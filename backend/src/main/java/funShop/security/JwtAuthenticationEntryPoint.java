package funShop.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;

import funShop.exceptions.InvalidLoginResponse;

//This interface is called whenever an exception is thrown because 
//a user is trying to access a resource that requires authentication(requires user to login).
//@Service gắn cho các Bean đảm nhiệm xử lý logic
//@Repository gắn cho các Bean đảm nhiệm giao tiếp với DB
//@Component gắn cho các Bean khác.
@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

	@Override
//	when an AuthenticationException is caught by the filter, an AuthenticationEntryPoint’s “commence” method is called with the HTTP request, the HTTP response, and the exception.
//	Decide what to do with the exception and, more importantly, what to do with the HTTP response
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {

		InvalidLoginResponse loginResponse = new InvalidLoginResponse();
		String jsonLoginResponse = new Gson().toJson(loginResponse);

		httpServletResponse.setContentType("application/json");
		httpServletResponse.setStatus(401);
		httpServletResponse.getWriter().print(jsonLoginResponse);

	}

}

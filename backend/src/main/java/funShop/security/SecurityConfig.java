package funShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(
		securedEnabled = true,
		jsr250Enabled = true,
		prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private JwtAuthenticationEntryPoint unauthorizedHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
//		DISABLE Cross-Origin Resource Sharing (CORS) 
//		DISABLE Cross-Site Request Forgery CSRF protection: because we use JWT to authenticate user
//		https://docs.spring.io/spring-security/site/docs/5.0.x/reference/html/csrf.html
		http.cors().and().csrf().disable()
//				customize the response in case of Unauthorized access
				.exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
//				setup to use StateLess REST API
				.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
//				Spring Security takes care of setting up a default login and logout process for you, including a login URL, 
//				login form, default URL after login, and some other options
				.authorizeRequests()
//				setup PUBLIC route
                .antMatchers(
                        "/",
                        "/favicon.ico",
                        "/**/*.png",
                        "/**/*.gif",
                        "/**/*.svg",
                        "/**/*.jpg",
                        "/**/*.html",
                        "/**/*.css",
                        "/**/*.js"
                ).permitAll()
//                ANYTHING other than the above Matchers NEED to be authenticated
                .anyRequest().authenticated()
                ;
		
	}

	
}

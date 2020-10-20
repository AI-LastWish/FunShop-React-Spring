package funShop.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;

import funShop.services.impl.CustomUserDetailsQueryService;

import static funShop.security.SecurityConstants.SIGN_UP_URLS;
import static funShop.security.SecurityConstants.LOGIN_URLS;
import static funShop.security.SecurityConstants.PRODUCTS_URL;
import static funShop.security.SecurityConstants.BRANDS_URL;
import static funShop.security.SecurityConstants.CATEGORIES_URL;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled = true, jsr250Enabled = true, prePostEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private CustomUserDetailsQueryService customUserDetailsQueryService;

	@Bean
	public JwtAuthenticationFilter jwtAuthenticationFilter() {
		return new JwtAuthenticationFilter();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(customUserDetailsQueryService) // Cung cáp userservice cho spring security
				.passwordEncoder(bCryptPasswordEncoder); // cung cấp password encoder
	}

//	return 1 of 3:
//	an Authentication with value authenticated=true if the input represents a valid principal and can be verified
//	Throw an AuthenticationException if the input represents an invalid principal
//	Return null if it can’t decide
	@Override
	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	protected AuthenticationManager authenticationManager() throws Exception {
		return super.authenticationManager();
	}

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
				.antMatchers("/", 
						"/favicon.ico", 
						"/**/*.png", 
						"/**/*.gif", 
						"/**/*.svg", 
						"/**/*.jpg", 
						"/**/*.html",
						"/**/*.css", 
						"/**/*.js")
				.permitAll()
				.antMatchers(HttpMethod.GET, PRODUCTS_URL).permitAll()
				.antMatchers(HttpMethod.GET, BRANDS_URL).permitAll()
				.antMatchers(HttpMethod.GET, CATEGORIES_URL).permitAll()
				.antMatchers(HttpMethod.POST, SIGN_UP_URLS).permitAll()
				.antMatchers(HttpMethod.POST, LOGIN_URLS).permitAll()
//                ANYTHING other than the above Matchers NEED to be authenticated
				.anyRequest().authenticated();

//		Add các filter vào ứng dụng của chúng ta, thứ mà sẽ hứng các request 
//		để xử lý trước khi tới các xử lý trong controllers.
		http.addFilterBefore(
				jwtAuthenticationFilter(), 
				UsernamePasswordAuthenticationFilter.class);

	}

}
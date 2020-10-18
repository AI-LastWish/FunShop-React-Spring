package funShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.User;
import funShop.payload.JWTLoginSucessReponse;
import funShop.payload.LoginRequest;
import funShop.security.JwtTokenProvider;
import funShop.services.impl.UserCommandService;
import funShop.services.mapValidation.MapValidationErrorService;
import funShop.validator.UserValidator;
import static funShop.security.SecurityConstants.TOKEN_PREFIX;

@RestController
@RequestMapping("/api/users")
public class UserController {

	@Autowired
	private MapValidationErrorService mapValidationErrorService;

	@Autowired
	private UserValidator userValidator;

	@Autowired
	private UserCommandService userCommandService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {

		// Validate passwords match
		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		User newUser = userCommandService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {
		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(
						loginRequest.getUsername(), 
						loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

		return ResponseEntity.ok(new JWTLoginSucessReponse(true, jwt));
	}

}

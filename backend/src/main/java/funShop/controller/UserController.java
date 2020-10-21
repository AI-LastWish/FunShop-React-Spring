package funShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.User;
import funShop.domain.dto.UserDTO;
import funShop.payload.JWTLoginSucessReponse;
import funShop.payload.LoginRequest;
import funShop.security.JwtTokenProvider;
import funShop.services.impl.UserCommandService;
import funShop.services.impl.UserQueryService;
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
	private UserQueryService userQueryService;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JwtTokenProvider tokenProvider;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		// Validate passwords match
		userValidator.validate(user, result);

		UserDTO newUserDto = userCommandService.saveUser(user);

		return new ResponseEntity<UserDTO>(newUserDto, HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest, BindingResult result) {

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);

		if (errorMap != null)
			return errorMap;

		try {
			Authentication authentication = authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

			SecurityContextHolder.getContext().setAuthentication(authentication);
			String jwt = TOKEN_PREFIX + tokenProvider.generateToken(authentication);

			UserDTO userDto = UserDTO.userToUserDTO(userQueryService.getUserByUsername(loginRequest.getUsername()));

			var jwtRes = new JWTLoginSucessReponse(userDto.getId(), userDto.getUsername(), userDto.getFullName(),
					userDto.isAdmin(), jwt);

			return new ResponseEntity<JWTLoginSucessReponse>(jwtRes, HttpStatus.OK);

		} catch (Exception e) {
			throw new BadCredentialsException(null);
		}

	}

	@PostMapping("/profile")
	public ResponseEntity<?> getUserProfile(@RequestBody Long request) throws Exception {

		System.out.println("id = " + request.toString());

		var user = userQueryService.getUserById(request);

		var userDto = UserDTO.userToUserDTO(user);

		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);
	}

	@PutMapping("/profile")
	public ResponseEntity<?> updateProfile(@RequestBody User request) throws Exception {

		var userDto = userCommandService.updateUser(request);
		
		System.out.println(userDto.toString());

		return new ResponseEntity<UserDTO>(userDto, HttpStatus.OK);

	}

}

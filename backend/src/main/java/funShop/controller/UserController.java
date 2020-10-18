package funShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import funShop.domain.User;
import funShop.services.impl.UserCommandService;
import funShop.services.mapValidation.MapValidationErrorService;

@RestController
@RequestMapping("/api/users")
public class UserController {
	
    @Autowired
    private MapValidationErrorService mapValidationErrorService;
	
//    @Autowired
//    private UserValidator userValidator;
    
    @Autowired
    private UserCommandService userCommandService;

	@PostMapping("/register")
	public ResponseEntity<?> registerUser(@Valid @RequestBody User user, BindingResult result) throws Exception {
		// Validate passwords match
//		userValidator.validate(user, result);

		ResponseEntity<?> errorMap = mapValidationErrorService.MapValidationService(result);
		if (errorMap != null)
			return errorMap;

		User newUser = userCommandService.saveUser(user);

		return new ResponseEntity<User>(newUser, HttpStatus.CREATED);
	}

}

package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import funShop.domain.User;
import funShop.repositories.UserRepository;
import funShop.services.IUserCommandService;

@Service
public class UserCommandService implements IUserCommandService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User saveUser(User newUser) throws Exception {

		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		// Username has to be unique (exception)
		newUser.setUsername(newUser.getUsername());
		// Make sure that password and confirmPassword match
		// We don't persist or show the confirmPassword
		newUser.setConfirmPassword("");
		
		try {
			return userRepository.save(newUser);

		} catch (Exception e) {
			var errMsg = "Username '" + newUser.getUsername() + "' already exists";
			throw new Exception(errMsg);
		}
	}

}

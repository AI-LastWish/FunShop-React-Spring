package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import funShop.domain.User;
import funShop.domain.dto.UserDTO;
import funShop.repositories.UserRepository;
import funShop.services.IUserCommandService;

@Service
public class UserCommandService implements IUserCommandService {

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private UserQueryService userQueryService;

	@Override
	public UserDTO saveUser(User newUser) throws Exception {

		newUser.setPassword(bCryptPasswordEncoder.encode(newUser.getPassword()));
		// Username has to be unique (exception)
		newUser.setUsername(newUser.getUsername());

		try {
			var user = userRepository.save(newUser);
			var userDto = UserDTO.userToUserDTO(user);

			return userDto;

		} catch (Exception e) {
			var errMsg = "Username '" + newUser.getUsername() + "' already exists";
			throw new Exception(errMsg);
		}
	}

	@Override
	public UserDTO updateUser(User newUser) throws Exception {

		var oldUser = userQueryService.getUserById(newUser.getId());

		System.out.println("full name = " + newUser.getFullName());

		oldUser.setFullName(newUser.getFullName() != null ? newUser.getFullName() : oldUser.getFullName());
		oldUser.setUsername(newUser.getUsername() != null ? newUser.getUsername() : oldUser.getUsername());
		oldUser.setPassword(newUser.getPassword() != null ? newUser.getPassword() : oldUser.getPassword());

		return saveUser(oldUser);
	}

}

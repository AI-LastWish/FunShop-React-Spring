package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import funShop.domain.User;
import funShop.repositories.UserRepository;
import funShop.services.IUserQueryService;

@Service
public class UserQueryService implements IUserQueryService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserByUsername(String username) throws Exception {

		User user;

		try {
			user = userRepository.findByUsername(username);

		} catch (Exception e) {
			var resMeg = "User with Username: '" + username + "' not found";
			throw new Exception(resMeg);
		}

		return user;
	}

}

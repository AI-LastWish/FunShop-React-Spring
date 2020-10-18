package funShop.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import funShop.repositories.UserRepository;
import funShop.services.IUserCommandService;

public class UserCommandService implements IUserCommandService {
	
	@Autowired
	private UserRepository userRepository;

}

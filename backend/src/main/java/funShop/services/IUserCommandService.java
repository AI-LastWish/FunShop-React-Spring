package funShop.services;

import funShop.domain.User;

public interface IUserCommandService {
	
	User saveUser(User newUser) throws Exception;

}

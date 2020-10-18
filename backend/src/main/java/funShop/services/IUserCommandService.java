package funShop.services;

import funShop.domain.User;
import funShop.domain.dto.UserDTO;

public interface IUserCommandService {
	
	UserDTO saveUser(User newUser) throws Exception;

}

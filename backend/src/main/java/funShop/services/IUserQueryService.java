package funShop.services;

import funShop.domain.User;

public interface IUserQueryService {

	User getUserByUsername(String username) throws Exception;
}

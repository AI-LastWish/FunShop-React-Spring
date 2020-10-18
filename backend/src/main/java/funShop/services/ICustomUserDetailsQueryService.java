package funShop.services;

import funShop.domain.User;

public interface ICustomUserDetailsQueryService {

	User loadUserById(Long id);
}

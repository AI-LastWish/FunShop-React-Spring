package funShop.services.impl;

import funShop.domain.User;
import funShop.repositories.UserRepository;
import funShop.services.ICustomUserDetailsQueryService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsQueryService implements UserDetailsService, ICustomUserDetailsQueryService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username);
		if (user == null)
			new UsernameNotFoundException("User not found");
		return user;
	}
	
	@Override
	public User loadUserById(Long id) {
		User user = userRepository.getById(id);
		if (user == null)
			new UsernameNotFoundException("User not found");
		return user;

	}
}

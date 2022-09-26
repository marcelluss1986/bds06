package com.devsuperior.movieflix.services;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.movieflix.dto.UserDTO;
import com.devsuperior.movieflix.entities.User;
import com.devsuperior.movieflix.repositories.UserRepository;
import com.devsuperior.movieflix.services.exceptions.ResourceNotFoundException;

@Service
public class UserService implements UserDetailsService {

private static Logger logger = LoggerFactory.getLogger(UserService.class);
	
	@Autowired
	private UserRepository repository;
	
	@Autowired
	private AuthService authService;
	
	
	/*@Transactional(readOnly = true)
	public List<UserDTO> getProfile() {
		authService.authenticated();
		List<User> list = repository.findAll();
		return list.stream().map(x-> new UserDTO(x)).collect(Collectors.toList());
	}*/
	
	@Transactional(readOnly = true)	
	public UserDTO findByid(Long id) {
		authService.authenticated();
		Optional<User> obj = repository.findById(id);
		User entity = obj.orElseThrow(()-> new ResourceNotFoundException("Entity not found"));
		return new UserDTO(entity);
	}
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = repository.findByEmail(username);
		if(user == null) {
			logger.error("User not found: " + username);
			throw new UsernameNotFoundException(username);
		}
		logger.info("User found: " + username);
		return user;
	}
	
	@Transactional
	public UserDTO getProfile(){
		User user = authService.authenticated();		
		user = repository.findByEmail(user.getEmail());
		return new UserDTO(user);
	}
}

package com.ga.service;

import com.ga.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
	
	public User signup(User user);
	
	public String login(User user);
	
	public User getUserById (Long id);
	
	public User updateUser(User user, Long id);
	
}

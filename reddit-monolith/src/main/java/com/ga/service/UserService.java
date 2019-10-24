package com.ga.service;

import com.ga.entity.Comment;
import com.ga.entity.User;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetailsService;


public interface UserService extends UserDetailsService {
	
	public String signup(User user);
	
	public String login(User user);
	
	public User getUserById (Long id);
	
	public User updateUser(User user, Long id);
	
	public User getCurrentUser();
	
	public void setCurrentUser(User user);
	
	public List<Comment> getCommentsByUser();
	
}

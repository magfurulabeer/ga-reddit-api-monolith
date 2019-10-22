package com.ga.dao;

import com.ga.entity.User;

public interface UserDao {
	public User signup(User user); //user passed user service
	public User login(User user);
	public User getUserById(Long id);
	public User updateUser(User user, Long id);
	public User getUserByUsername(String username);
}

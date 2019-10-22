package com.ga.service;

import com.ga.entity.User;

public interface UserService {
	public User signup(User user);
	public User login(User user);
	public User getUserById (Long id);
	public User updateUser(User user, Long id);
}

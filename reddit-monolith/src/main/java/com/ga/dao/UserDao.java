package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.User;

public interface UserDao {
	public User signup(User user); //user passed user service
	public User login(User user);
	public User getUserById(Long id);
	public User updateUser(User user, Long id);
	public User getUserByUsername(String username);
	public List<Comment> getCommentsByUser(User user);
}

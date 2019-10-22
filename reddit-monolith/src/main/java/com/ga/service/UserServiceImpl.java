package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserDao;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	UserDao userDao;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	public User signup(User user) {
		return userDao.signup(user);
	}
	
	public User login(User user) {
		User foundUser = userDao.login(user);
//		if (foundUser==null) {
//			//throw exeception
//			return null;
//		}
		if (!user.getPassword().equals(foundUser.getPassword())){
			//throw exeception
			return null;
		}
		return foundUser;
	}
	

	public User getUserById(Long id) {
		return userDao.getUserById(id);
	
	}
}

package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.UserProfileDao;
import com.ga.entity.UserProfile;

@Service
public class UserProfileServiceImpl implements UserProfileService {
	
	@Autowired
	UserProfileDao userProfileDao;
	
	@Autowired
	UserService userService;

	@Override
	public UserProfile createUserProfile(UserProfile newProfile) {
		String username = userService.getCurrentUser().getUsername();
		return userProfileDao.createUserProfile(username, newProfile);
	}

	@Override
	public UserProfile getUserProfile() {
		String username = userService.getCurrentUser().getUsername();
		return userProfileDao.getUserProfile(username);
	}	

}
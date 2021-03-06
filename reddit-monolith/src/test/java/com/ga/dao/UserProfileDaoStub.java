package com.ga.dao;

import com.ga.entity.UserProfile;

public class UserProfileDaoStub implements UserProfileDao {

	@Override
	public UserProfile createUserProfile(String username, UserProfile newProfile) {
		UserProfile userProfile = new UserProfile();

		userProfile.setAdditionalEmail("gilbert@gottfried.com");
		userProfile.setAddress("New York");
        userProfile.setMobile("1234567890");
        
		return userProfile;
	}

	@Override
	public UserProfile getUserProfile(String username) {
		UserProfile userProfile = new UserProfile();

		userProfile.setAdditionalEmail("gilbert@gottfried.com");
		userProfile.setAddress("New York");
        userProfile.setMobile("1234567890");
        
		return userProfile;
	}

	
}
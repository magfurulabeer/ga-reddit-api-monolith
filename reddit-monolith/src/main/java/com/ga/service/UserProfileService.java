package com.ga.service;

import com.ga.entity.UserProfile;

public interface UserProfileService {
	
	public UserProfile createUserProfile(UserProfile newProfile);
	
	public UserProfile getUserProfile();

}
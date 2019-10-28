package com.ga.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import com.ga.dao.UserProfileDao;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserProfileServiceTest {
	
	@InjectMocks
	UserProfileServiceImpl userProfileService;
	
	@Mock
	UserProfileDao userProfileDao;

	@Mock
	UserService userService;
	
	private UserProfile userProfile;
	private User user;
	
	@Before
	public void init() {
		userProfile = new UserProfile();
		userProfile.setAddress("Gotham");
		user = new User();
		user.setUsername("Batman");
	}
	
	
	@Test
	public void createUserProfile_UserProfile_Success() {
		when(userService.getCurrentUser()).thenReturn(user);
		when(userProfileDao.createUserProfile(any(), any())).thenReturn(userProfile);
		
		UserProfile profile = userProfileService.createUserProfile(userProfile);
		
		Assert.assertNotNull(profile);
		Assert.assertEquals(profile.getAddress(), userProfile.getAddress());
	}
	
	@Test
	public void getUserProfile_UserProfile_success() {
		when(userService.getCurrentUser()).thenReturn(user);
		when(userProfileDao.getUserProfile(any())).thenReturn(userProfile);
		
		UserProfile profile = userProfileService.getUserProfile();

		Assert.assertNotNull(profile);
		Assert.assertEquals(profile.getAddress(), userProfile.getAddress());
	}
	
	
	
	
	
}

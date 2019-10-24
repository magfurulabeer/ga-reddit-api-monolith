package com.ga.service;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {
	
	@InjectMocks
	UserServiceImpl userService;
	
	@InjectMocks
	User user;
	
	@Mock
	UserDao userDao;
	
	@Mock
	PasswordEncoder bCryptPasswordEncoder;
	
	@Mock
	JwtUtil jwtUtil;
	
	
	
	
	@Before
	public void initializeUser() {
		user.setUserId(1L);
		user.setUsername("John");
		user.setEmail("abc@abc.com");
		user.setPassword("abc");
	}
	
	@Test
	public void getUserById_UserId_Success() {
		
		when(userDao.getUserById(any())).thenReturn(user);
		
		User tempUser = userService.getUserById(user.getUserId());
		
		Assert.assertNotNull(tempUser);
		Assert.assertEquals(tempUser.getUserId(), user.getUserId());
	}
	
	@Test
	public void updateUser_User_Success() {
		when(userDao.updateUser(any(), any())).thenReturn(user);
		
		User tempUser=userService.updateUser(user, user.getUserId());
		
		Assert.assertNotNull(tempUser);
		Assert.assertEquals(tempUser.getUserId(), user.getUserId());
		
	}
	@Test
	public void signup_User_Success() {
		
		when(bCryptPasswordEncoder.encode(any())).thenReturn(user.getPassword());
		when(userDao.signup(any())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn("Dummy Value");
		when(userDao.getUserByUsername(any())).thenReturn(user);
		
		String tempSignup=userService.signup(user);
		
		Assert.assertNotNull(tempSignup);
		Assert.assertEquals(tempSignup, "Dummy Value");
		
		
	}
}


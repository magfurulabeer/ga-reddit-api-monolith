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
import com.ga.entity.Comment;
import com.ga.entity.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

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
		user.setId(1L);
		user.setUsername("John");
		user.setEmail("abc@abc.com");
		user.setPassword("abc");
	}
	
	@Test
	public void getUser_User_Success() {
		userService.setCurrentUser(user);
		User tempUser = userService.getUser();
		
		Assert.assertNotNull(tempUser);
		Assert.assertEquals(tempUser.getId(), user.getId());
	}
	
	@Test
	public void updateUser_User_Success() {
		when(userDao.updateUser(any(), any())).thenReturn(user);
		
		userService.setCurrentUser(user);
		User updatedUser = userService.updateUser(user);
		
		Assert.assertNotNull(updatedUser);
		Assert.assertEquals(updatedUser.getId(), user.getId());
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
	
	@Test
	public void signup_User_Failure() {
		user.setId(null);
		when(bCryptPasswordEncoder.encode(any())).thenReturn(user.getPassword());
		when(userDao.signup(any())).thenReturn(user);
		when(jwtUtil.generateToken(any())).thenReturn("Dummy Value");
		when(userDao.getUserByUsername(any())).thenReturn(user);
		
		String tempSignup = userService.signup(user);
		
		Assert.assertNull(tempSignup);	
	}
	
	@Test
	public void login_User_Success() {
		when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(true);
		when (userDao.login(any())).thenReturn(user);
		when (jwtUtil.generateToken(any())).thenReturn("Dummy Value");
		when (userDao.getUserByUsername(any())).thenReturn(user);
		when(userDao.getUserByUsername(any())).thenReturn(user);
		
		String tempLogin = userService.login(user);
		
		Assert.assertNotNull(tempLogin);
		Assert.assertEquals(tempLogin, "Dummy Value");
	}
	
	@Test
	public void login_User_Failure() {
		when(bCryptPasswordEncoder.matches(any(),any())).thenReturn(true);
		when (userDao.login(any())).thenReturn(null);
		when (jwtUtil.generateToken(any())).thenReturn("Dummy Value");
		when (userDao.getUserByUsername(any())).thenReturn(user);
		
		String tempLogin = userService.login(user);
		
		Assert.assertNull(tempLogin);	
	}

	@Test
	public void getCommentsByUser_CommentList_Success() {
		List<Comment> list = new ArrayList<>();
		
		when (userDao.getCommentsByUser(any())).thenReturn(list);

		
		List<Comment> comments = userService.getCommentsByUser();
		
		Assert.assertNotNull(comments);
		Assert.assertEquals(list, comments);
	}
	
	
}


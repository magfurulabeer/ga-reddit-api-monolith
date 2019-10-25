package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;
import com.ga.entity.UserProfile;

public class UserProfileDaoTest {
	@Rule
    public MockitoRule rule = MockitoJUnit.rule();
	
	@Mock
    private SessionFactory sessionFactory;
    
    @Mock
    Session session;
    
    @Mock
    Transaction transaction;
    
    @Mock
    Query<User> query;
    
    @Mock
    Query<Comment> q1;
    
    @Mock
    Query<Post>q2;
    
	@InjectMocks  //b/c call new on it
    private User user;
	
	@InjectMocks
	private UserProfile userProfile;
	
	@InjectMocks
    private PostDaoImpl postDao;
	
	@InjectMocks
    private UserProfileDaoImpl userProfileDao;
	
	@InjectMocks
	private Comment comment;
	
	@InjectMocks
	private Post post;
	
	@Mock
	UserDao userDao;
	
	@Before
    public void init() {
        
        user.setId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        
        comment.setText("My first comment");
        
        post.setId(1L);
        post.setTitle("first title");
        
        userProfile.setAdditionalEmail("John@yahoo.com");
        
        user.setUserProfile(userProfile);  //method returns 
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
	}
	@Test
	public void createUserProfile_UserProfile_Success() {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		UserProfile savedProfile=userProfileDao.createUserProfile(user.getUsername(),userProfile);
		assertNotNull("Test returned null object, expected non-null", savedProfile);
        assertEquals(savedProfile, userProfile);
	}
	
	@Test
	public void getUserProfile_UserProfile_Success() {
		when(userDao.getUserByUsername(any())).thenReturn(user);
		UserProfile savedProfile =userProfileDao.getUserProfile(user.getUsername());
		assertNotNull("Test returned null object, expected non-null", savedProfile);
        assertEquals(savedProfile, userProfile);
	}
		
	
}

package com.ga.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
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
import com.ga.entity.User;

public class UserDaoTest {
	
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
	
	@InjectMocks  //b/c call new on it
    private User user;
	
	@InjectMocks
    private UserDaoImpl userDao;
	
	@InjectMocks
	private Comment comment;
	
	@Before
    public void init() {
        
        user.setId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        
        comment.setText("My first comment");
        
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
}
	@Test
    public void signup_User_Success() {
		User savedUser = userDao.signup(user);
		
		assertNotNull("Test returned null object, expected non-null", savedUser);
        assertEquals(savedUser, user);
    }
	@Test
	public void login_User_Success() {
		
		when(query.uniqueResult()).thenReturn(user);
		
		User savedUser=userDao.login(user);
		
		assertNotNull("Test returned null object, expected non-null", user);
        assertEquals(savedUser, user);
	}
	@Test
	public void getCommentsByUser_User_Success() {
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);
		
		when(session.createQuery(anyString())).thenReturn(q1);
		when(q1.getResultList()).thenReturn(comments);
		
		List<Comment> savedComment=userDao.getCommentsByUser(user);
		assertNotNull("Test returned null object, expected non-null", comment);
        assertEquals(savedComment, comments);
	}
	
}
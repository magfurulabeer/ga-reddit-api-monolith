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
import com.ga.entity.Post;
import com.ga.entity.User;

public class CommentDaoTest {
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
    private PostDaoImpl postDao;
	
	@InjectMocks
    private CommentDaoImpl commentDao;
	
	@InjectMocks
	private Comment comment;
	
	@InjectMocks
	private Post post;
	
	@Before
    public void init() {
        
        user.setId(1L);
        user.setUsername("batman");
        user.setPassword("robin");
        
        comment.setText("My first comment");
        
        post.setId(1L);
        post.setTitle("first title");
        
        when(sessionFactory.getCurrentSession()).thenReturn(session);
        when(session.getTransaction()).thenReturn(transaction);
        when(session.createQuery(anyString())).thenReturn(query);
	}
	@Test
	public void createComment_Comment_Success(){
		Comment addComment= commentDao.createComment(comment, user, post);
		
		assertNotNull("Test returned null object, expected non-null", post);
        assertEquals(addComment, comment);
	}
	
	@Test
	public void deleteCommentById_Comment_Success(){
		Comment deleteComment= commentDao.deleteCommentById(comment.getId());
		assertNotNull("Test returned null object, expected non-null", comment);
        assertEquals(deleteComment, null);
	}
}

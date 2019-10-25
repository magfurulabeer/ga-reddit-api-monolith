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

public class PostDaoTest {
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
	public void createPost_Post_Success(){
		
		Post savedPost=postDao.createPost(post,user);
		
		assertNotNull("Test returned null object, expected non-null", savedPost);
        assertEquals(savedPost, post);
	}
	
	@Test
	public void deletePost_Post_Success(){
		Post deletedPost=postDao.deletePost(post.getId());
		
		assertNotNull("Test returned null object, expected non-null", post);
        assertEquals(deletedPost, null);}
	
	@Test
	public void getPostById_Post_Success() {
		Post foundPost=postDao.getPostById(post.getId());
		
		assertNotNull("Test returned null object, expected non-null", post);
        assertEquals(foundPost, null);
	}
	@Test
	public void getAllPosts_Post_Success() {
		List<Post> posts = new ArrayList<Post>();
		posts.add(post);
		
		when(session.createQuery(anyString())).thenReturn(q2);
		when(q2.getResultList()).thenReturn(posts);
		
		List<Post> savedPost = postDao.getAllPosts();
		assertNotNull("Test returned null object, expected non-null", post);
		assertEquals(savedPost, posts);
	}
	@Test
	public void getCommentsByPostId_Post_Success() {
		List<Comment> comments = new ArrayList<Comment>();
		comments.add(comment);
		
		when(session.createQuery(anyString())).thenReturn(q1);
		when(q1.getResultList()).thenReturn(comments);
		
		List<Comment> savedComment=postDao.getCommentsByPostId(post.getId());
		assertNotNull("Test returned null object, expected non-null", comment);
        assertEquals(savedComment, comments);
	}
	
	
	
}

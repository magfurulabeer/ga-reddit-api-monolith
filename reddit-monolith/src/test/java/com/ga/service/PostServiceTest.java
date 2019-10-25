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
import com.ga.dao.PostDao;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

@RunWith(MockitoJUnitRunner.class)
public class PostServiceTest {
	
	@InjectMocks
	PostServiceImpl postService;
	
	@InjectMocks
	User user;
	
	@InjectMocks
	Post post;
	
	private List<Post> posts;    //???????
	
	@Mock
	UserService userService;
	

	@Mock
	PostDao postDao;

	
	@Before
	public void initializeUser() {
		user.setId(1L);
		user.setUsername("John");
		user.setEmail("abc@abc.com");
		user.setPassword("abc");
		
		post.setId(1L);
		post.setTitle("New Title");
		post.setDescription("This is a new description");
		
		posts=new ArrayList<Post>();
		posts.add(post);
	}
	
	
	@Test
	public void createPost_Post_Success() {
		when(userService.getUser()).thenReturn(user);
		when(postDao.createPost(any(),any())).thenReturn(post);
		
		Post tempPost = postService.createPost(post);
		
		Assert.assertNotNull(tempPost);
		Assert.assertEquals(tempPost.getTitle(), post.getTitle());
	}
	
	@Test
	public void deletePost_Post_Success() {
		when(postDao.deletePost(any())).thenReturn(post);
		
		Post tempPost = postService.deletePost(post.getId());
		
		Assert.assertNotNull(tempPost);
		Assert.assertEquals(tempPost.getTitle(), post.getTitle());
	}
	
	@Test
	public void getAllPosts_Post_Success() {
		when(postDao.getAllPosts()).thenReturn(posts);
		
		List <Post> tempPosts = postService.getAllPosts();
		
		Assert.assertNotNull(tempPosts);
		Assert.assertEquals(tempPosts, posts);
	}
	
	@Test
	public void getPostById_Post_success() {
		when(postDao.getPostById(any())).thenReturn(post);
		Post tempPost = postService.getPostById(post.getId());
		Assert.assertNotNull(tempPost);
		Assert.assertEquals(tempPost, post);
	}
	
	@Test
	public void getCommentsById_CommentList_success() {
		List<Comment> list = new ArrayList<>();
		
		when (postDao.getCommentsByPostId(any())).thenReturn(list);

		List<Comment> comments = postService.getCommentsByPostId(1L);
		
		Assert.assertNotNull(comments);
		Assert.assertEquals(list, comments);
	}
	
	
	
	
}

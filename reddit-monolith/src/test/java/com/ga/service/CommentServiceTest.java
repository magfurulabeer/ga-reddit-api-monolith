package com.ga.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@RunWith(MockitoJUnitRunner.class)
public class CommentServiceTest {
	
	@InjectMocks
	CommentServiceImpl commentService;
	
	@InjectMocks
	Comment comment;
	
	@InjectMocks
	User user;
	
	@InjectMocks
	Post post;
	
	@Mock
	CommentDao commentDao;
	
	@Mock
	UserService userService;
	
	@Mock 
	PostService postService;
	
	@Before
	public void initializeComment() {
		comment.setId(1L);
		comment.setText("Comment Text");
		
		user.setId(1L);
		user.setUsername("John");
		user.setEmail("abc@abc.com");
		user.setPassword("abc");
		
		post.setId(1L);
		post.setTitle("New title");
		post.setDescription("New Description");
	}
	
	@Test
	public void createComment_Comment_Success(){
		when(userService.getUser()).thenReturn(user);
		when(postService.getPostById(any())).thenReturn(post);
		when(commentDao.createComment(any(), any(), any())).thenReturn(comment);
		
		Comment tempComment = commentService.createComment(comment, post.getId());
		
		Assert.assertNotNull(tempComment);
		Assert.assertEquals(tempComment.getId(), comment.getId());
	}
	
	@Test
	public void deleteCommentById_Comment_Success() {
		when(commentDao.deleteCommentById(any())).thenReturn(comment);
		String tempComment = commentService.deleteCommentById(comment.getId());
		Assert.assertNotNull(tempComment);
		Assert.assertEquals(tempComment, "success");
		
	}
}

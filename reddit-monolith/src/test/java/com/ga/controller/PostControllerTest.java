package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.UserProfile;
import com.ga.service.PostService;
import com.ga.service.UserProfileService;

@RunWith(MockitoJUnitRunner.class) 
public class PostControllerTest {
	
	@InjectMocks
	PostController postController;
	
	@Mock
	PostService postService;
	
	private MockMvc mockMvc;
	private Post post;
	
	@Before
	public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(postController).build();
        post = new Post();
        post.setTitle("Post");
        post.setDescription("Description");
    }
	
	@Test
	public void createPost_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/post")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content(postJSON());
		
		when(postService.createPost(any())).thenReturn(post);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json(postJSON()));
	}
	
	@Test
	public void deletePostById_Post_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/post/{postId}", 1)
                .accept(MediaType.APPLICATION_JSON);
		
		when(postService.deletePost(any())).thenReturn(post);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json(postJSON()));
	}
	
	@Test
	public void getAllPosts_PostList_Success() throws Exception {
		List<Post> list = new ArrayList<>();
		list.add(post);
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/list")
                .accept(MediaType.APPLICATION_JSON);
		
		when(postService.getAllPosts()).thenReturn(list);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("[" + postJSON() + "]"));
	}
	
	@Test
	public void getAllCommentsByPostId_CommentList_Success() throws Exception {
		List<Comment> list = new ArrayList<>();
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/post/{postId}/comment", 1)
                .accept(MediaType.APPLICATION_JSON);
		
		when(postService.getCommentsByPostId(any())).thenReturn(list);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("[]"));
	}
	
	private String postJSON() {
		return "{ \"title\": \"" + post.getTitle() + "\", " + "\"description\": \"" + post.getDescription() + "\"}";
	}
}
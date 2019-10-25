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
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.ga.entity.Comment;
import com.ga.entity.User;
import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	UserController userController;
	
	@Mock
	UserService userService;

	private User user;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(userController).build();
        user = new User(1L, "e", "p", "u");
    }

	@Test
	public void getUser_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user")
                .accept(MediaType.APPLICATION_JSON);
		
		when(userService.getUser()).thenReturn(user);
		
		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("{}"));
	}
	
	@Test
	public void updateUser_User_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .put("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"email\": \"email\"}");
		
		user.setEmail("email");
		when(userService.updateUser(any())).thenReturn(user);
		
		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("{}"));
	}
	
	@Test
	public void getCommentsByUser_CommentList_Success() throws Exception {
		List<Comment> list = new ArrayList<>();
		
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/user/comment")
                .accept(MediaType.APPLICATION_JSON);
		
		when(userService.getCommentsByUser()).thenReturn(list);
		
		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("[]"));
	}
}

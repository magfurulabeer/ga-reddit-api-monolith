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
import com.ga.service.CommentService;
import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class CommentControllerTest {
	
	private MockMvc mockMvc;

	@InjectMocks
	CommentController commentController;
	
	@Mock
	CommentService commentService;

	private Comment comment;
	
	@Before
	public void init() {
		mockMvc = MockMvcBuilders.standaloneSetup(commentController).build();
        comment = new Comment();
        comment.setText("Comment");
    }

	@Test
	public void createComment_Comment_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/comment/{postId}", 1)
                .accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
		        .content("{ \"text\": \"Comment\"}");
		when(commentService.createComment(any(), any())).thenReturn(comment);
		
		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("{ text: \"Comment\"}"));
	}

	@Test
	public void deleteCommentById_String_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .delete("/comment/{id}", 1)
                .accept(MediaType.APPLICATION_JSON);
		
		when(commentService.deleteCommentById(any())).thenReturn("success");

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().string("success"));
	}
}

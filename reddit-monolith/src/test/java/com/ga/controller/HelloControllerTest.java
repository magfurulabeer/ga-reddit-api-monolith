package com.ga.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.ga.service.UserService;

@RunWith(MockitoJUnitRunner.class)
public class HelloControllerTest {

    private MockMvc mockMvc;
    
    //@InjectMocks
	//UserControllerTest userController;
    
    @InjectMocks
    HelloController helloController;
    
    @Mock
	UserService userService;
    
    @Before
    public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(helloController).build();
    }
    @Test
    public void HelloWorld_HelloWorld_Success() throws Exception{
    	RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/hello")
                .accept(MediaType.APPLICATION_JSON);
        mockMvc.perform(requestBuilder)
       .andExpect(status().isOk())
       .andExpect(content().string("Hello World"));
    } 
}

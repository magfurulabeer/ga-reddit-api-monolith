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
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.mockito.junit.MockitoJUnitRunner;

import com.ga.entity.UserProfile;
import com.ga.service.UserProfileService;

@RunWith(MockitoJUnitRunner.class) 
public class UserProfileControllerTest {
	
	@InjectMocks
	UserProfileController userProfileController;
	
	@Mock
	UserProfileService userProfileService;
	
	private MockMvc mockMvc;
	private UserProfile userProfile;
	
	@Before
	public void init() {
        mockMvc = MockMvcBuilders.standaloneSetup(userProfileController).build();
        userProfile = new UserProfile();
//        userProfile.setId((long) 1);
//        userProfile.setAdditionalEmail("batman@wayneindustries.com");
//        userProfile.setMobile("1234567890");
        userProfile.setAddress("Gotham City");
    }

	@Test
	public void getUserProfile_UserProfile_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .get("/profile/{username}", "batman")
                .accept(MediaType.APPLICATION_JSON);
//                .contentType(MediaType.APPLICATION_JSON);
//                .content("batman");
		
		// "{ \"address\": \"" + userProfile.getAddress() + "}"
		when(userProfileService.getUserProfile(any())).thenReturn(userProfile);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("{ \"address\": \"" + userProfile.getAddress() + "\"}"));
	}
	
	@Test
	public void createUserProfile_UserProfile_Success() throws Exception {
		RequestBuilder requestBuilder = MockMvcRequestBuilders
                .post("/profile/{username}", "batman")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{ \"address\": \"" + userProfile.getAddress() + "\"}");
		
		// "{ \"address\": \"" + userProfile.getAddress() + "}"
		when(userProfileService.createUserProfile(any(), any())).thenReturn(userProfile);

		mockMvc.perform(requestBuilder)
		   .andExpect(status().isOk())
		   .andExpect(content().json("{ \"address\": \"" + userProfile.getAddress() + "\"}"));
	}
}
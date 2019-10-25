package com.ga.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {
	UserService userService;
	
	@Autowired  //to make UserService available
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping
	public User getUser() {
		return userService.getUser();
	}
	
	@PutMapping
	public User updateUser(@RequestBody User user) {
		return userService.updateUser(user);
	}
	
	@GetMapping("/comment")
	public List<Comment> getCommentsByUser(){
		return userService.getCommentsByUser();
	}
	
	
	
}

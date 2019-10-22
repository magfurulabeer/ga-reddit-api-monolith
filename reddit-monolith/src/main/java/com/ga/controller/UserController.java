package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
	@PostMapping("/signup")
	public User signup(@RequestBody User user) {  //user will have the body
		//return "post/signup - Username is " + user.getUsername() + " and Password is " + user.getPassword();
		return userService.signup(user);
	}
	@PostMapping("/login")
	public User login(@RequestBody User user) {  //user will have the body
		return userService.login(user);
	}
	@GetMapping("/{id}")
	public User getUserById(@PathVariable Long id) {
		return userService.getUserById(id);
	}
	
}

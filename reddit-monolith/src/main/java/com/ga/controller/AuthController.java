package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.JwtResponse;
import com.ga.entity.User;
import com.ga.service.UserService;

@RestController
@RequestMapping("/")
public class AuthController {
UserService userService;
	
	@Autowired  //to make UserService available
	public void setUserService(UserService userService) {
		this.userService=userService;
	}
	
	@GetMapping("/hello")
	public String hello() {
		return "Hello! Reddit Monolith server is running.";
	}
	
	@PostMapping("/signup")
	public ResponseEntity<?> signup( @RequestBody User user) {  //@Valid
    	return ResponseEntity.ok(new JwtResponse(userService.signup(user)));
	}
	
	@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User user) {
        return ResponseEntity.ok(new JwtResponse(userService.login(user)));
    }
}

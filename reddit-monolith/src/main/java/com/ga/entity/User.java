package com.ga.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name="users")
public class User {
	@Id
	@Column(name="user_id")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long userId;
	
	@Column(unique = true, nullable=false)
	private String email;
	
	//@NotBlank(message ="Password cannot be blank")
	@Column(nullable = false)
	private String password;
	
	//@NotBlank(message="Username cannot be blank")
	@Column(unique = true, nullable=false)
	private String username;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_profile_id")
	private UserProfile userProfile;
	
	//Join user to post
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Post> posts;
	
	@OneToMany(mappedBy="user", cascade=CascadeType.ALL)
	private List<Comment> comments;

	public User(Long userId,String email,String password, String username) {
		this.userId=userId;
		this.email=email;
		this.password=password;
		this.username=username;
	}
		
	@SuppressWarnings("unused")
	private User() {}
	
	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public UserProfile getUserProfile() {
		return userProfile;
	}

	public void setUserProfile(UserProfile userProfile) {
		this.userProfile = userProfile;
	}
}

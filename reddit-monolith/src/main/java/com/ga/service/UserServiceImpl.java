package com.ga.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ga.config.JwtUtil;
import com.ga.dao.UserDao;
import com.ga.entity.Comment;
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	UserDao userDao;
	
	JwtUtil jwtUtil;
	
	private User currentUser;
	
	@Autowired
    @Qualifier("encoder")
    PasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	@Autowired
	public void setJwtUtil(JwtUtil jwtUtil) {
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public String signup(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		
    	if(userDao.signup(user).getUserId() != null) {
        		UserDetails userDetails = loadUserByUsername(user.getUsername());
        		return jwtUtil.generateToken(userDetails);
    	}
        	
		return null;
	}
	
	public String login(User user) {
		User foundUser = userDao.login(user);
		if (foundUser != null && 
			foundUser.getUserId() != null && 
			bCryptPasswordEncoder.matches(user.getPassword(), foundUser.getPassword())) {
			
		    UserDetails userDetails = loadUserByUsername(foundUser.getUsername());
		    return jwtUtil.generateToken(userDetails);
		}
        	
		return null;
	}
	

	public User getUser() {
		return currentUser;
	}

	public User updateUser(User user) {
		Long id = currentUser.getUserId();
		return userDao.updateUser(user, id);
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);
        this.setCurrentUser(user);

        if(user==null)
            throw new UsernameNotFoundException("Unknown user: " +username);

        return new org.springframework.security.core.userdetails.User(user.getUsername(), bCryptPasswordEncoder.encode(user.getPassword()),
                true, true, true, true, getGrantedAuthorities(user));
    }
    
    private List<GrantedAuthority> getGrantedAuthorities(User user) {
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
//        authorities.add(new SimpleGrantedAuthority(user.getUserRole().getName()));
        return authorities;
    }

	@Override
	public User getCurrentUser() {
		return this.currentUser;
	}

	@Override
	public void setCurrentUser(User user) {
		this.currentUser = user;
	}

	@Override
	public List<Comment> getCommentsByUser() {
		User currentUser =this.currentUser;
		return userDao.getCommentsByUser(currentUser);
	}
    
}

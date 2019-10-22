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
import com.ga.entity.User;

@Service
public class UserServiceImpl implements UserService{
	
	UserDao userDao;
	
	JwtUtil jwtUtil;
	
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
	
	public User signup(User user) {
		return userDao.signup(user);
	}
	
	public String login(User user) {
		if(userDao.login(user) != null) {
    		UserDetails userDetails = loadUserByUsername(user.getUsername());
	    		
    		return jwtUtil.generateToken(userDetails);
		}

		return null;

//		User foundUser = userDao.login(user);
////		if (foundUser==null) {
////			//throw exeception
////			return null;
////		}
//		if (!user.getPassword().equals(foundUser.getPassword())){
//			//throw exeception
//			return null;
//		}
	}
	

	public User getUserById(Long id) {
		return userDao.getUserById(id);
	
	}

	public User updateUser(User user, Long id) {
		return userDao.updateUser(user, id);
	}
	
	@Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userDao.getUserByUsername(username);

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
}

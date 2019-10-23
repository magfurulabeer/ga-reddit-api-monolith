package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Post;
import com.ga.entity.User;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private UserService userService;

	@Override
	public Post createPost(Post post) {
		System.out.println("CREATING POST IN POST SERVICE");
		String username = userService.getUsername();
		System.out.println(username);
		return postDao.createPost(post, username);
	}

	@Override
	public Post deletePost(Long postId) {
		return postDao.deletePost(postId);
	}
	@Override
	public List<Post> getAllPosts() {
		return postDao.getAllPosts();
	}
}

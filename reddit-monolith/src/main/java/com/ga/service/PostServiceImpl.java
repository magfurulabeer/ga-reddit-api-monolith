package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Post;

@Service
public class PostServiceImpl implements PostService{
	@Autowired
	  PostDao postDao;

	  @Override
	  public Post createPost(Post post) {
	    return postDao.createPost(post);
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

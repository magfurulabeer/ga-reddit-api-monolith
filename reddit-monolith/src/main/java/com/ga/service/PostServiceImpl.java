package com.ga.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.PostDao;
import com.ga.entity.Comment;
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
		User user = userService.getUser();
		return postDao.createPost(post, user);
	}

	@Override
	public Post deletePost(Long postId) {
		return postDao.deletePost(postId);
	}
	
	@Override
	public List<Post> getAllPosts() {
		return postDao.getAllPosts();
	}

	@Override
	public Post getPostById(Long postId) {
		return postDao.getPostById(postId);
	}

	@Override
	public List<Comment> getCommentsByPostId(Long postId) {
		Post post = getPostById(postId);
		return postDao.getCommentsByPostId(postId);
	}
}

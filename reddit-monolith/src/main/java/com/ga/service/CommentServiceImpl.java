package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Autowired
	UserService userService;
	
	@Autowired
	PostService postService;
	
	@Override
	public Comment createComment(Comment comment, Long postId) {
		User currentUser = userService.getCurrentUser();
		Post currentPost = postService.getPostById(postId);
		
		return commentDao.createComment(comment, currentUser, currentPost);
	}

	@Override
	public Comment deleteCommentById(Long id) {
		return commentDao.deleteCommentById(id);
	}

}

package com.ga.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ga.dao.CommentDao;
import com.ga.entity.Comment;

@Service
public class CommentServiceImpl implements CommentService {

	@Autowired
	CommentDao commentDao;
	
	@Override
	public Comment createComment(Comment comment) {
		return commentDao.createComment(comment);
	}

	@Override
	public Comment deleteCommentById(Long id) {
		return commentDao.deleteCommentById(id);
	}

}

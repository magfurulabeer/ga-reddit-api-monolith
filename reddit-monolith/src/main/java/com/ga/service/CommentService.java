package com.ga.service;

import com.ga.entity.Comment;

public interface CommentService {
	public Comment createComment(Comment comment, Long postId);
	public String deleteCommentById(Long id);
}
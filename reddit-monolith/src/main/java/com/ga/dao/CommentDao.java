package com.ga.dao;

import com.ga.entity.Comment;

public interface CommentDao {
	public Comment createComment(Comment comment);
	public Comment deleteCommentById(Long id);
}

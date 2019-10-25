package com.ga.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ga.entity.Comment;
import com.ga.service.CommentService;

@RestController
@RequestMapping("/comment")
public class CommentController {
	
	@Autowired
	CommentService commentService;
	
	@PostMapping("/{postId}")
	public Comment createComment(@RequestBody Comment comment, @PathVariable Long postId) {
		return commentService.createComment(comment, postId);
	}
	
	@DeleteMapping("/{id}")
	public String deleteCommentById(@PathVariable Long id) {
		return commentService.deleteCommentById(id);
	}
}

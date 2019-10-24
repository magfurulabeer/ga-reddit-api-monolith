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
	
	@PostMapping
	public Comment createComment(@RequestBody Comment comment) {
		return commentService.createComment(comment);
	}
	
	@DeleteMapping("/{id}")
	public Comment deleteCommentById(@PathVariable Long id) {
		return commentService.deleteCommentById(id);
	}
}

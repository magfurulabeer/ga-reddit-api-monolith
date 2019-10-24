package com.ga.service;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.Post;

public interface PostService {
	public Post createPost(Post post);
	public Post getPostById(Long postId);
	public Post deletePost(Long postId);
	public List<Post> getAllPosts();
	public List<Comment> getCommentsByPostId(Long postId);
}

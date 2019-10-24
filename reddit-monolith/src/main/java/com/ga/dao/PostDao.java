package com.ga.dao;

import java.util.List;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

public interface PostDao {
	public Post createPost(Post post, User user);
	public Post deletePost(Long postId);
	public List<Post> getAllPosts();
	public Post getPostById(Long postId);
	public List<Comment> getCommentsByPostId(Long postId);
}

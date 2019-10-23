package com.ga.dao;

import java.util.List;

import com.ga.entity.Post;
import com.ga.entity.User;

public interface PostDao {
	public Post createPost(Post post, String username);
	public Post deletePost(Long postId);
	public List<Post> getAllPosts();
}

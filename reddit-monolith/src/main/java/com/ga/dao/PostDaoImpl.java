package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;

@Repository
public class PostDaoImpl implements PostDao{  
    
	@Autowired
	  SessionFactory sessionFactory;
	
	
	@Override
	public Post createPost(Post post) {
    	try(Session session = sessionFactory.getCurrentSession();) {
    		session.beginTransaction();
    		session.save(post);
    		session.getTransaction().commit();
    	}
    return post;
  }
	 @Override
	  public Post deletePost(Long postId) {
	    Post post = null;
	    try(Session session = sessionFactory.getCurrentSession();) {
	      session.beginTransaction();
	      post = session.get(Post.class, postId);
	      session.delete(post);
	      session.getTransaction().commit();
	    }
	    return post;
	  }
	 
	 @SuppressWarnings("unchecked")
	  @Override
	  public List<Post> getAllPosts() {
	   
	    List<Post> postList = null;
	    try(Session session = sessionFactory.getCurrentSession();) {
	      session.beginTransaction();
	      postList = session.createQuery("From Post").getResultList();
	    }
	    return postList;
	  }
}

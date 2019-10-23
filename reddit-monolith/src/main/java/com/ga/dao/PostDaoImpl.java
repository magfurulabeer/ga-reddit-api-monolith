package com.ga.dao;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class PostDaoImpl implements PostDao{  
    
	@Autowired
	  SessionFactory sessionFactory;
	
	@Autowired
	UserDao userDao;
	
	
	@Override
	public Post createPost(Post post, String username) {
		

		User user = userDao.getUserByUsername(username);
		
		System.out.println(user.toString());
		
		Session session = sessionFactory.getCurrentSession();
		
    	try {
    		session.beginTransaction();
  
    		post.setUser(user);
    		
    		session.save(post);
    		
    		session.getTransaction().commit();
    	} finally {
    		session.close();
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

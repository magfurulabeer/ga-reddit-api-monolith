package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;
import com.ga.entity.Post;
import com.ga.entity.User;

@Repository
public class CommentDaoImpl implements CommentDao {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Comment createComment(Comment comment, User user, Post post) {		
		Session session = sessionFactory.getCurrentSession();
		
    	try {
    		session.beginTransaction();
    		
    		comment.setUser(user);
    		comment.setPost(post);
    		
    		session.save(comment);
    		
    		session.getTransaction().commit();
    	} finally {
    		session.close();
    	}
    	
    	return comment;
	}

	@Override
	public Comment deleteCommentById(Long id) {
		Comment comment = null;

		Session session = sessionFactory.getCurrentSession();

		try {
			session.beginTransaction();

			comment = session.get(Comment.class, id);

			session.delete(comment);

			session.getTransaction().commit();

		} finally {
			session.close();
		}
	    
	    return comment;
	}

}

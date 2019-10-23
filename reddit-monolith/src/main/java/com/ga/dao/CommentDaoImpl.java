package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.Comment;

@Repository
public class CommentDaoImpl implements CommentDao {
	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public Comment createComment(Comment comment) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(comment);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
		return comment;
	}

	@Override
	public Comment deleteCommentById(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Comment comment;
		try {
			session.beginTransaction();
			comment = session.get(Comment.class, id);
			session.delete(comment);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
		return comment;
	}

}

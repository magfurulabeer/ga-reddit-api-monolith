package com.ga.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.ga.entity.User;

@Repository
public class UserDaoImpl implements UserDao{
	@Autowired
	SessionFactory sessionFactory;   //used for transactions
	
	public User signup(User user) {
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			session.save(user);
			session.getTransaction().commit();
		}
		finally {
			session.close();
		}
		return user;
	}

}

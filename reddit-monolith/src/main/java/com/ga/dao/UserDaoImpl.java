package com.ga.dao;

import javax.persistence.NoResultException;

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

	public User login(User user) {
		Session session = sessionFactory.getCurrentSession();
		User foundUser;
		try {
			session.beginTransaction();
			foundUser = (User) session.createQuery("FROM User u WHERE u.username =  '" + user.getUsername() + "'").getSingleResult();

//			String query = "from User u where u.username='" + user.getUsername() + "'";
//			System.out.println(query);
//			foundUser = (User) session.createQuery(query).getSingleResult();
		}
		catch (NoResultException e) {
			//e.printStackTrace();
			System.out.println("<><><><><><<>>");
			return null;
			
		}
		finally {
			session.close();
		}
		return  foundUser;
	}
	
	public User getUserById(Long id) {
		Session session=sessionFactory.getCurrentSession();
		User foundUser;
		try {
			session.beginTransaction();
			foundUser=session.get(User.class, id);
		}
		finally {
			session.close();
		}
		return foundUser;
		
	}

	// TODO: Does this have business that should be in the User Service
	public User updateUser(User user, Long id) {
		User foundUser;
		Session session = sessionFactory.getCurrentSession();
		try {
			session.beginTransaction();
			foundUser = session.get(User.class, id);
			
			String username = user.getUsername();
			String password = user.getPassword();
			String email = user.getEmail();
			
			if(password != null) {
				foundUser.setPassword(password);
			}
			
			if(username != null) {
				foundUser.setUsername(username);
			}
			
			if(email != null) {
				foundUser.setEmail(email);
			}
			
			session.update(foundUser);
			session.getTransaction().commit();

		}
		finally {
			session.close();
		}
		
		return foundUser;		
	}
}

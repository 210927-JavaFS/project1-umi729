package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.UserClass;
import com.revature.utils.HibernateUtil;

public class UserClassDAOImpl implements UserClassDAO {

	@Override
	public List<UserClass> getAllUser() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM UserClass").list();
	}

	@Override
	public UserClass getUserById(int usr) {
		Session session = HibernateUtil.getSession();
		return session.get(UserClass.class, usr);
	}

	@Override
	public boolean insert(UserClass usr) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(usr);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(UserClass usr) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(usr);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean delete(UserClass usr) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(usr);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

}

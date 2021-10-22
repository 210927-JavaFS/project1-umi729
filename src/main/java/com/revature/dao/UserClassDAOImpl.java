package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.UserClass;
import com.revature.utils.HibernateUtil;

public class UserClassDAOImpl implements UserClassDAO {
	private static Logger Log = LoggerFactory.getLogger(UserClassDAOImpl.class);
	@Override
	public List<UserClass> getAllUser() {
		Log.debug("UserClassDAOImpl >  getAllUser()");
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM UserClass").list();
	}

	@Override
	public UserClass getUserById(int usr) {
		Log.debug("UserClassDAOImpl >  getUserById()");
		Session session = HibernateUtil.getSession();
		return session.get(UserClass.class, usr);
	}

	@Override
	public boolean insert(UserClass usr) {
		Log.debug("UserClassDAOImpl >  insert()");
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
		Log.debug("UserClassDAOImpl >  update()");
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
		Log.debug("UserClassDAOImpl >  delete()");
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

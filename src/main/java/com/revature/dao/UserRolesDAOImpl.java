package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.UserRoles;
import com.revature.utils.HibernateUtil;

public class UserRolesDAOImpl implements UserRolesDAO {

	@Override
	public List<UserRoles> getAllUser() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM UserRoles").list();
	}

	@Override
	public UserRoles getUserById(int roleId) {
		Session session = HibernateUtil.getSession();
		return session.get(UserRoles.class, roleId);
	}

	@Override
	public boolean insert(UserRoles urole) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(urole);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

	@Override
	public boolean update(UserRoles urole) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(urole);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(UserRoles urole) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(urole);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}

	}

}

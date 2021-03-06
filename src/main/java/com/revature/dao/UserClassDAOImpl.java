package com.revature.dao;

import java.util.List;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.UserClass;
import com.revature.services.AES256;
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
	public UserClass getUserByUser(String usr) {
		Log.debug("UserClassDAOImpl >  getUserByUser()");
		Transaction transaction = null;
		try {
			Session session = HibernateUtil.getSession();
			transaction = session.beginTransaction();

			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserClass> query = builder.createQuery(UserClass.class);
			Root<UserClass> root = query.from(UserClass.class);
			query.select(root).where(builder.equal(root.get("username"), usr));
			Query<UserClass> q = session.createQuery(query);
			UserClass uc = q.getSingleResult();
			System.out.println(uc.toString());

			transaction.commit();
			return uc;
		} catch (Exception e) {
			e.printStackTrace();
			if (transaction != null) {
				//transaction.rollback();
			}
			return null;
		}
	}

	@Override
	public boolean insert(UserClass usr) {
		Log.debug("UserClassDAOImpl >  insert()");
		Transaction tx=null;
		try {
			Session session = HibernateUtil.getSession();
			 tx = session.beginTransaction();
			
			 session.save(usr);
			// session.clear();
			tx.commit();
			
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			
			
			  if (tx != null) { tx.rollback();
			  
			  }
			 
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

	@Override
	public UserClass login(UserClass usr) {
		Transaction tx = null;
		try {
			AES256 ae = new AES256();
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<UserClass> query = builder.createQuery(UserClass.class);
			Root<UserClass> root = query.from(UserClass.class);
			query.select(root).where(builder.equal(root.get("username"), usr.getUsername()));
			Query<UserClass> q = session.createQuery(query);
			System.out.println(usr.getUsername());
			UserClass uc = q.getSingleResult();

			if (usr.getUsername().equals(uc.getUsername())) {
				if (usr.getPassword().equals(uc.getPassword())) {

					tx.commit();
					return uc;
				}
			} else {
				if (tx != null) {
					tx.rollback();
					return null;
				}
				
			}
			tx=null;
			return null;

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return null;
		}
	}

}

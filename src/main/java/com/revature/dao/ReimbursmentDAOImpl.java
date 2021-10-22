package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.models.Reimbursment;
import com.revature.utils.HibernateUtil;

public class ReimbursmentDAOImpl implements ReimbursmentDAO {
	private static Logger Log = LoggerFactory.getLogger(ReimbursmentDAOImpl.class);
	@Override
	public List<Reimbursment> findAllRec() {
		Log.debug("ReimbursmentDAOImpl >  findAllRec()");
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Reimbursment").list();
	}

	@Override
	public Reimbursment findById(int id) {
		Log.debug("ReimbursmentDAOImpl >  findById()");
		Session session = HibernateUtil.getSession();
		return session.get(Reimbursment.class, id);
	}

	@Override
	public Reimbursment filterByStatus(String status) {
		Log.debug("ReimbursmentDAOImpl >  filterByStatus()");
		Session session = HibernateUtil.getSession();
		return session.get(Reimbursment.class, status);
	}

	@Override
	public boolean addNewRec(Reimbursment rec) {
		Log.debug("ReimbursmentDAOImpl >  addNewRec()");
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(rec);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateRec(Reimbursment rec) {
		Log.debug("ReimbursmentDAOImpl >  updateRec()");
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.merge(rec);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(Reimbursment rec) {
		Log.debug("ReimbursmentDAOImpl >  delete()");
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.delete(rec);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

}

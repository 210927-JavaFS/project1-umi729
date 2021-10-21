package com.revature.dao;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.revature.models.Reimbursment;
import com.revature.utils.HibernateUtil;

public class ReimbursmentDAOImpl implements ReimbursmentDAO {

	@Override
	public List<Reimbursment> findAllRec() {
		Session session = HibernateUtil.getSession();
		return session.createQuery("FROM Reimbursment").list();
	}

	@Override
	public Reimbursment findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Reimbursment filterByStatus(String status) {
		Session session = HibernateUtil.getSession();
		return session.get(Reimbursment.class, status);
	}

	@Override
	public boolean addNewRec(Reimbursment rec) {
		try {
			Session session = HibernateUtil.getSession();
			Transaction tx = session.beginTransaction();
			session.save(rec);
			tx.commit();
			//HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateRec(Reimbursment rec) {
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

}

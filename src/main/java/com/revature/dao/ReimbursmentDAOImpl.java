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

import com.revature.models.Reimbursment;
import com.revature.utils.HibernateUtil;
import java.util.Collections;
import java.util.Date;

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
	public List<Reimbursment> filterByStatus(String status) {
		Log.debug("ReimbursmentDAOImpl >  filterByStatus()");
		Transaction tx = null;
		try {
			
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			CriteriaBuilder builder = session.getCriteriaBuilder();
			CriteriaQuery<Reimbursment> query = builder.createQuery(Reimbursment.class);
			Root<Reimbursment> root = query.from(Reimbursment.class);
			query.select(root).where(builder.equal(root.get("rstatus"), status));
			
			Query<Reimbursment> q = session.createQuery(query);
			List<Reimbursment> uc = q.getResultList();
			System.out.println("\n\n\n\nhello\n\n\n\n\n");
			System.out.println(status);
			tx.commit();
			return uc;
			

		} catch (Exception e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();
			}
			return Collections.emptyList();
		}

		
	}

	@Override
	public boolean addNewRec(Reimbursment rec) {
		Log.debug("ReimbursmentDAOImpl >  addNewRec()");
		Transaction tx = null;
		try {
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			System.out.println(rec);
			
			session.save(rec);
			
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
			return false;
		}
	}

	@Override
	public boolean updateRec(int id, String status) {
		Log.debug("ReimbursmentDAOImpl >  updateRec()");
		Transaction tx = null;
		try {
			Date date=new Date();
			Session session = HibernateUtil.getSession();
			tx = session.beginTransaction();
			Reimbursment reimUp = session.get(Reimbursment.class, id); 
			reimUp.setRstatus(status);
			reimUp.setDateOfResolve(date);
			session.merge(reimUp);
			tx.commit();
			HibernateUtil.closeSession();
			return true;
		} catch (HibernateException e) {
			e.printStackTrace();
			if (tx != null) {
				tx.rollback();

			}
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

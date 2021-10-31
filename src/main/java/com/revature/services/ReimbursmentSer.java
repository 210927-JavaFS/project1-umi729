package com.revature.services;

import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.revature.dao.ReimbursmentDAO;
import com.revature.dao.ReimbursmentDAOImpl;
import com.revature.dao.UserClassDAO;
import com.revature.dao.UserClassDAOImpl;
import com.revature.dao.UserRolesDAO;
import com.revature.dao.UserRolesDAOImpl;
import com.revature.models.Reimbursment;
import com.revature.models.UserClass;
import com.revature.models.UserRoles;

public class ReimbursmentSer {
	private Date date=new Date();
	private ReimbursmentDAO rd = new ReimbursmentDAOImpl();
	private static Logger Log = LoggerFactory.getLogger(ReimbursmentSer.class);
	
	UserRoles logRole=UserClassSer.getUserRoles();
	UserClass logUser=UserClassSer.getUser();
	
	public List<Reimbursment> getAllRec() {
		Log.info("ReimbursmentSer >  getAllRec()");
		return rd.findAllRec();
	}

	public Reimbursment getReimById(int id) {
		Log.info("ReimbursmentSer >  getReimById()");
		Reimbursment rec = rd.findById(id);
		if (rec != null) {
			return rec;
		} else {
			return new Reimbursment();
		}
	}

	public List<Reimbursment> getReimByStatus(String status) {
		Log.info("ReimbursmentSer >  getReimByStatus()");
		return rd.filterByStatus(status);
		
	}

	public boolean addReim(Reimbursment rec) {
		Log.info("ReimbursmentSer >  addReim()");
		rec.setDateOfSubmit(date);
		return rd.addNewRec(rec);
	}

	public boolean updateRem(int id, String status) {
		Log.info("ReimbursmentSer >  updateRem()");
		return rd.updateRec(id, status);
	}

	public boolean deleteReim(int id) {
		Log.info("ReimbursmentSer >  deleteReim()");
		Reimbursment rec = getReimById(id);
		return rd.delete(rec);
	}

}

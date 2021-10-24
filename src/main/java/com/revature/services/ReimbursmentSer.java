package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursmentDAO;
import com.revature.dao.ReimbursmentDAOImpl;
import com.revature.models.Reimbursment;
import com.revature.models.UserClass;
import com.revature.models.UserRoles;

public class ReimbursmentSer {

	private ReimbursmentDAO rd = new ReimbursmentDAOImpl();

	UserRoles logRole=UserClassSer.getUserRoles();
	UserClass logUser=UserClassSer.getUser();
	
	public List<Reimbursment> getAllRec() {
		return rd.findAllRec();
	}

	public Reimbursment getReimById(int id) {
		Reimbursment rec = rd.findById(id);
		if (rec != null) {
			return rec;
		} else {
			return new Reimbursment();
		}
	}

	public Reimbursment getReimByStatus(String status) {
		Reimbursment rec = rd.filterByStatus(status);
		if (rec != null) {
			return rec;
		} else {
			return new Reimbursment();
		}
	}

	public boolean addReim(Reimbursment rec) {
		rec.setRole(logRole);
		rec.setUsr(logUser);
		return rd.addNewRec(rec);
	}

	public boolean updateRem(Reimbursment rec) {
		return rd.updateRec(rec);
	}

	public boolean deleteReim(int id) {
		Reimbursment rec = getReimById(id);
		return rd.delete(rec);
	}

}

package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursmentDAO;
import com.revature.dao.ReimbursmentDAOImpl;
import com.revature.models.Reimbursment;

public class UserRolesSer {
private ReimbursmentDAO rd = new ReimbursmentDAOImpl();
	
	public List<Reimbursment> getAllRec() {
		return rd.findAllRec();
	}
	
	public Reimbursment getReimbursment(int id) {
		Reimbursment rec = rd.findById(id);
		if (rec != null) {
			return rec;
		}else {
			return new Reimbursment();
		}
	}
	
	public boolean addReim(Reimbursment rec) {
		return rd.addNewRec(rec);
	}
	
	public boolean update(Reimbursment rec) {
		return rd.updateRec(rec);
	}
	
	public boolean deleteReim(int id) {
		Reimbursment rec = getReimbursment(id);
		return rd.delete(rec);
	}

}

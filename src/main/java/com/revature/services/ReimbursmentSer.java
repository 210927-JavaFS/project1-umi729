package com.revature.services;

import java.util.List;

import com.revature.dao.ReimbursmentDAO;
import com.revature.dao.ReimbursmentDAOImpl;
import com.revature.models.Reimbursment;

public class ReimbursmentSer {
	
	private ReimbursmentDAO rd = new ReimbursmentDAOImpl();
	
	public List<Reimbursment> getAllRec() {
		return rd.findAllRec();
	}
}

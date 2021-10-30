package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursment;
import com.revature.models.UserClass;

public interface ReimbursmentDAO {
	List<Reimbursment> findAllRec();
	Reimbursment findById(int id);
	List<Reimbursment> filterByStatus(String status);
	boolean addNewRec(Reimbursment rec);
	boolean updateRec(int id, String status);
	public boolean delete(Reimbursment rec);
	
}

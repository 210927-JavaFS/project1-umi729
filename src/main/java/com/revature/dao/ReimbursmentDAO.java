package com.revature.dao;

import java.util.List;

import com.revature.models.Reimbursment;

public interface ReimbursmentDAO {
	List<Reimbursment> findAllRec();
	Reimbursment findById(int id);
	Reimbursment filterByStatus(String status);
	boolean addNewRec(Reimbursment rec);
	boolean updateRec(Reimbursment rec);
	
}

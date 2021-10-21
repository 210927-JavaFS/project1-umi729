package com.revature;

import java.util.List;

import com.revature.models.Reimbursment;
import com.revature.services.ReimbursmentSer;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReimbursmentSer rb= new ReimbursmentSer();
		
		List<Reimbursment> list=rb.getAllRec();
		
		System.out.println(list);
		

	}

}

package com.revature;

import java.util.List;

import com.revature.dao.UserClassDAOImpl;
import com.revature.models.Reimbursment;
import com.revature.models.UserClass;
import com.revature.models.UserRoles;
import com.revature.services.ReimbursmentSer;

public class App {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ReimbursmentSer rb= new ReimbursmentSer();
		
		
		
		UserClass uc=new UserClass("umerzahid",  "myPassword".hashCode() , "Umer", "Zahid", "umi729@gmail.com", new UserRoles("Manager", null), null);
		UserClassDAOImpl ud=new UserClassDAOImpl();
		ud.insert(uc);
		
		List<UserClass> list1=ud.getAllUser();
		
		List<Reimbursment> list=rb.getAllRec();
		
		System.out.println(list);
		

	}

}

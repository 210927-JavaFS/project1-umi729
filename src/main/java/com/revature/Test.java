package com.revature;

import java.util.List;

import com.revature.models.UserClass;
import com.revature.models.UserRoles;
import com.revature.services.UserClassSer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserClassSer userSer=new UserClassSer();
		UserRoles ur=new UserRoles("Manager");
		UserRoles em=new UserRoles("Employee");
		userSer.addUser(new UserClass(1,"umerzahid", "mypassword", "Umer", "Zahid", "umi729@gmail.com", ur));
		userSer.addUser(new UserClass(2, "employee", "mypassword", "IT", "Manager", "u9@gmail.com", em));
		userSer.addUser(new UserClass(3, "employee2", "mypassword", "Software", "Designer", "u9dd@gmail.com", em));
		//boolean list = userSer.login(new UserClass("umerzahid", "mypassword"));
		//System.out.println(list);
	}

}

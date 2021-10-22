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
		userSer.addUser(new UserClass("umeaddhd", "mypassword", "Umer", "Zahid", "u9d@gmail.com", ur));
		userSer.addUser(new UserClass("umeahd", "mypassword", "Umer", "Zahid", "u9@gmail.com", ur));
		userSer.addUser(new UserClass("umedaddhd", "mypassword", "Umer", "Zahid", "u9dd@gmail.com", ur));
		//boolean list = userSer.login(new UserClass("umerzahid", "mypassword"));
		//System.out.println(list);
	}

}

package com.revature;

import java.util.List;

import com.revature.models.UserClass;
import com.revature.services.UserClassSer;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		UserClassSer userSer=new UserClassSer();
		userSer.addUser(new UserClass("umerzahid", "mypassword", "Umer", "Zahid", "umi729@gmail.com", null, null));
		boolean list = userSer.login(new UserClass("umerzahid", "mypassword"));
		System.out.println(list);
	}

}

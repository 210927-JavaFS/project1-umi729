package com.revature.services;

import java.util.List;

import com.revature.dao.UserClassDAO;
import com.revature.dao.UserClassDAOImpl;
import com.revature.dao.UserRolesDAO;
import com.revature.dao.UserRolesDAOImpl;
import com.revature.models.UserClass;
import com.revature.models.UserRoles;

public class UserClassSer {
	private static UserClassDAO uc = new UserClassDAOImpl();
	private static UserClass logUser;
	private static UserRolesDAO urdao=new UserRolesDAOImpl();
	private static AES256 ae=new AES256();
	public List<UserClass> getAllRec() {
		return uc.getAllUser();
	}
	
	 static UserRoles getUserRoles() {
		
		if(logUser!=null) {
			return urdao.getUserById(logUser.getRole().getRoleId());
			
		}
		return null;
	}
	 static UserClass getUser() {
			
			if(logUser!=null) {
				return logUser;
			}
			return null;
		}
	 

	public UserClass getUserClass(int id) {
		UserClass rec = uc.getUserById(id);
		if (rec != null) {
			return rec;
		} else {
			return new UserClass();
		}
	}

	public boolean addUser(UserClass rec) {
	
		rec.setPassword(ae.encrypt(rec.getPassword()));
	
		return uc.insert(rec);
	}

	public boolean updateUser(UserClass rec) {
		return uc.update(rec);
	}

	public boolean deleteUser(int id) {
		UserClass rec = getUserClass(id);
		return uc.delete(rec);
	}
	public static UserClass login(UserClass rec) {
		
		rec.setPassword(ae.encrypt(rec.getPassword()));
		if(uc.login(rec)!=null) {
			
			logUser=uc.login(rec);
			
			return logUser;
		}
		
		return null;
	}

}

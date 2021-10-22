package com.revature.services;

import java.util.List;

import com.revature.dao.UserClassDAO;
import com.revature.dao.UserClassDAOImpl;
import com.revature.models.UserClass;

public class UserClassSer {
	private UserClassDAO uc = new UserClassDAOImpl();

	public List<UserClass> getAllRec() {
		return uc.getAllUser();
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
		rec.setPassword(String.valueOf(rec.getPassword()).hashCode());
		return uc.insert(rec);
	}

	public boolean updateUser(UserClass rec) {
		return uc.update(rec);
	}

	public boolean deleteUser(int id) {
		UserClass rec = getUserClass(id);
		return uc.delete(rec);
	}
	public boolean login(UserClass rec) {
		
		return uc.login(rec);
	}

}

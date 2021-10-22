package com.revature.services;

import java.util.List;

import com.revature.dao.UserRolesDAO;
import com.revature.dao.UserRolesDAOImpl;
import com.revature.models.UserRoles;

public class UserRolesSer {
	private UserRolesDAO rd = new UserRolesDAOImpl();

	public List<UserRoles> getAllRec() {
		return rd.getAllUser();
	}

	public UserRoles getUserRoles(int id) {
		UserRoles rec = rd.getUserById(id);
		if (rec != null) {
			return rec;
		} else {
			return new UserRoles();
		}
	}

	public boolean addRole(UserRoles rec) {
		return rd.insert(rec);
	}

	public boolean updateRole(UserRoles rec) {
		return rd.update(rec);
	}

	public boolean deleteRole(int id) {
		UserRoles rec = getUserRoles(id);
		return rd.delete(rec);
	}

}

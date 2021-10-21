package com.revature.dao;

import java.util.List;

import com.revature.models.UserRoles;

public interface UserRolesDAO {
	public List<UserRoles> getAllUser();
	public UserRoles getUserById(int roleId);
	public boolean insert(UserRoles urole);
	public boolean update(UserRoles urole);
	public boolean delete(UserRoles urole);

}

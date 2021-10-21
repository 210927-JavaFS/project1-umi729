package com.revature.dao;

import java.util.List;

import com.revature.models.UserClass;

public interface UserClassDAO {
	public List<UserClass> getAllUser();
	public UserClass getUserById(int usr);
	public boolean insert(UserClass usr);
	public boolean update(UserClass usr);
	public boolean delete(UserClass usr);
}

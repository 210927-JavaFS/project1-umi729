package com.revature.dao;

import java.util.List;

import com.revature.models.UserClass;

public interface UserClassDAO {
	public List<UserClass> getAllUser();
	public UserClass getUserById(int usr);
	public void insert(UserClass usr);
	public void update(UserClass usr);
	public void delete(UserClass usr);
}

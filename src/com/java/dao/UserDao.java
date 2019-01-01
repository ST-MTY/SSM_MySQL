package com.java.dao;

import com.java.entity.User;

public interface UserDao {

	public User login(User user);
	
	public User add(User user);

	public User getUserFromId(int id);
}

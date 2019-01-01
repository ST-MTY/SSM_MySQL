package com.java.service;

import com.java.entity.User;

public interface UserService {

	public User login(User user);
	public User add(User user);
	public User getUserFromId(int id);
}

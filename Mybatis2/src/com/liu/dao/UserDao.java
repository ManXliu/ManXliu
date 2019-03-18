package com.liu.dao;

import java.util.List;

import com.liu.domain.User;

public interface UserDao {
	User getUserById(Integer id);
	List<User> getUserByUserName(String name);
	void insertUser(User user);
}

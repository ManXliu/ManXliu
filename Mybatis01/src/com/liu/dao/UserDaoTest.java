package com.liu.dao;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;

import org.junit.Test;

import com.liu.dao.impl.UserDaoImpl;
import com.liu.domain.User;

public class UserDaoTest {

	@Test
	public void testGetUserById() {
		UserDao userDao=new UserDaoImpl();
		User user=userDao.getUserById(25);
		System.out.println(user);
	}

	@Test
	public void testGetUserByUserName() {
		UserDao userDao=new UserDaoImpl();
		List<User> list=userDao.getUserByUserName("ÕÅ");
		for (User user : list) {
			System.out.println(user);

		}
		
	}

	@Test
	public void testInsertUser() {
		UserDao userDao=new UserDaoImpl();
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("ÒÁÀç");
		user.setSex("ÄÐ");
		user.setUsername("¹·×Ó");
		userDao.insertUser(user);
		
	}

}

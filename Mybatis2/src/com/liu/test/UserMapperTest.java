package com.liu.test;

import static org.junit.Assert.*;

import java.util.Date;
import java.util.List;


import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import com.liu.domain.User;
import com.liu.mapper.UserMapper;
import com.liu.utils.SqlSessionFactoryUtils;
/*
 * ע��   sqlSession.getMapper �õ��ĽӿڵĶ��� 
 * ʹ��ʱ�� ��������õ��Ķ���
 * namespace��·����дcom....
 * resource :com..
 */
public class UserMapperTest {

	@Test
	public void testQueryUserById() {
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		User user=userMapper.queryUserById(10);
		System.out.println(user);
		sqlSession.close();
		
	}

	@Test
	public void testSaveUser() {
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("����");
		user.setSex("��");
		user.setUsername("����");
		userMapper.saveUser(user);
		sqlSession.commit();
		sqlSession.close();
	
	}

	@Test
	public void testQuerUserByname1() {
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> list=userMapper.querUserByname1("��");
		for (User user : list) {
			System.out.println(user);
		}
		sqlSession.close();
	}

}

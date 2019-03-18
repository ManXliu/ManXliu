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
 * 注意   sqlSession.getMapper 得到的接口的对象 
 * 使用时： 调用上面得到的对象
 * namespace：路径名写com....
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
		user.setAddress("伊犁");
		user.setSex("男");
		user.setUsername("西亚");
		userMapper.saveUser(user);
		sqlSession.commit();
		sqlSession.close();
	
	}

	@Test
	public void testQuerUserByname1() {
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		UserMapper userMapper=sqlSession.getMapper(UserMapper.class);
		List<User> list=userMapper.querUserByname1("张");
		for (User user : list) {
			System.out.println(user);
		}
		sqlSession.close();
	}

}

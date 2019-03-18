package com.liu.dao.impl;


import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.liu.dao.UserDao;
import com.liu.domain.User;
import com.liu.utils.SqlSessionFactoryUtils;

public class UserDaoImpl implements UserDao {



	@Override
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		User user=sqlSession.selectOne("queryUserById", id);
		sqlSession.close();
		return user;
	}

	@Override
	public List<User> getUserByUserName(String name) {
		// TODO Auto-generated method stub
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		List<User> list=sqlSession.selectList("queryUserName2","张" );
		sqlSession.close();
		return list;
	}

	@Override
	//记得事务提交  要不然  数据库里面没有值
	
	public void insertUser(User user) {
		// TODO Auto-generated method stub
		SqlSession sqlSession=SqlSessionFactoryUtils.getSqlSessionFactory().openSession();
		sqlSession.insert("saveUser", user);
		sqlSession.commit();
		sqlSession.close();

		
	}

}

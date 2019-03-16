package com.liu.test;

import java.io.IOException;
import java.io.InputStream;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Test;

public class MybatisTest {
	@Test
	public void init() throws Exception {
		//����SqlSessionFactoryBuilder����
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		//����SqlMapConfig.xml�����ļ�
		InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
		//����SQLSessionFactory����
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		//����SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//����һ��User.xml��statement��id����������sqlִ���������
		Object user=sqlSession.selectOne("queryUserById", 1);
		System.out.println(user);
		//�ͷ���Դ
		sqlSession.close();
	}
}

package com.liu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MybatisTest {
	SqlSessionFactory sqlSessionFactory=null;
	//@Before��@Testǰ������
	@Before
	public void init() throws IOException {
		//����SqlSessionFactoryBuilder����
				SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
				//����SqlMapConfig.xml�����ļ�
				InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
				//����SQLSessionFactory����
				this.sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
	}

	
	@Test
	public void queryUserId() throws Exception {
		
		//����SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//����һ��User.xml��statement��id����������sqlִ���������
		Object user=sqlSession.selectOne("queryUserById", 1);
		System.out.println(user);
		//�ͷ���Դ
		sqlSession.close();
	}
	@Test
	public void queryUsername() throws Exception {
		
		//����SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//����һ��User.xml��statement��id����������sqlִ���������
		//��ѯ��������selectList
		List<Object> list=sqlSession.selectList("querUserByname1", "%��%");
		//��ǿforѭ�����user
		for(Object user : list) {
			System.out.println(user);
		}
		//�ͷ���Դ
		sqlSession.close();
	}
	
}

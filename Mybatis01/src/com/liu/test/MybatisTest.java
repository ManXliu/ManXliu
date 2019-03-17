package com.liu.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import com.liu.domain.User;

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
	public void queryUsername1() throws Exception {
		
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
	@Test
	public void queryByUserName2(){
		//���SqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//��ѯ�������� selectList��ӳ���ļ�ID�����������ַ�����
		List<String> list=sqlSession.selectList("queryUserName2", "��");
		for(Object user : list) {
			System.out.println(user);
		}
		//�ͷ���Դ
		sqlSession.close();
		
		
	}
	//��auto_increment��ʽ����

	@Test
	public void saveUser() {
		//�õ�SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//ʵ����User
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("�½�");
		user.setSex("��");
		user.setUsername("Ҫ����");
		//����
		sqlSession.insert("saveUser", user);
		//�����ύ
		sqlSession.commit();
		//�ͷ���Դ
		sqlSession.close();
	}
	//��UUID��ʽ����
	//����ʵ�� ����int����
	@Test
	public void saveUser2() {
		//�õ�SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//ʵ����User
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("�½�");
		user.setSex("��");
		user.setUsername("Ҫ����");
		//����
		sqlSession.insert("saveUser2", user);
		//�����ύ
		sqlSession.commit();
		//�ͷ���Դ
		sqlSession.close();
	}
	//����
	@Test
	public void updateByUserId() {
		//�õ�SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();

		//ʵ����User
		User user=new User();
		user.setBirthday(new Date());
		user.setId(1);
		user.setAddress("��³ľ��");
		user.setSex("Ů");
		user.setUsername("С��");
		
		//�����û�
		sqlSession.update("updateUserById", user);
		//�����ύ
		sqlSession.commit();
		//�ͷ���Դ
		sqlSession.close();
	}
	//ɾ���û�
	@Test
	public void deleteUserById() {
		//�õ�SqlSession����
		SqlSession sqlSession=sqlSessionFactory.openSession();

		
		//�����û�
		sqlSession.delete("deleteUserById", 27);
		//�����ύ
		sqlSession.commit();
		//�ͷ���Դ
		sqlSession.close();
	}
	
	
}

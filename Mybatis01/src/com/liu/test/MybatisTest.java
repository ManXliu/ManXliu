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
	//@Before在@Test前面运行
	@Before
	public void init() throws IOException {
		//创建SqlSessionFactoryBuilder对象
				SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
				//加载SqlMapConfig.xml配置文件
				InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
				//创建SQLSessionFactory对象
				this.sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
	}

	
	@Test
	public void queryUserId() throws Exception {
		
		//创建SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//参数一：User.xml的statement的id，参数二：sql执行所需参数
		Object user=sqlSession.selectOne("queryUserById", 1);
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
	@Test
	public void queryUsername() throws Exception {
		
		//创建SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//参数一：User.xml的statement的id，参数二：sql执行所需参数
		//查询多条数据selectList
		List<Object> list=sqlSession.selectList("querUserByname1", "%王%");
		//增强for循环输出user
		for(Object user : list) {
			System.out.println(user);
		}
		//释放资源
		sqlSession.close();
	}
	
}

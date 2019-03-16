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
		//创建SqlSessionFactoryBuilder对象
		SqlSessionFactoryBuilder sqlSessionFactoryBuilder=new SqlSessionFactoryBuilder();
		//加载SqlMapConfig.xml配置文件
		InputStream inputStream=Resources.getResourceAsStream("SqlMapConfig.xml");
		//创建SQLSessionFactory对象
		SqlSessionFactory sqlSessionFactory=sqlSessionFactoryBuilder.build(inputStream);
		//创建SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//参数一：User.xml的statement的id，参数二：sql执行所需参数
		Object user=sqlSession.selectOne("queryUserById", 1);
		System.out.println(user);
		//释放资源
		sqlSession.close();
	}
}

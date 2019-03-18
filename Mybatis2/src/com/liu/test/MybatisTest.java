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
	public void queryUsername1() throws Exception {
		
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
	@Test
	public void queryByUserName2(){
		//获得SqlSession
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//查询多条数据 selectList（映射文件ID参数，传入字符串）
		List<String> list=sqlSession.selectList("queryUserName2", "王");
		for(Object user : list) {
			System.out.println(user);
		}
		//释放资源
		sqlSession.close();
		
		
	}
	//用auto_increment方式自增

	@Test
	public void saveUser() {
		//得到SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//实例化User
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("新疆");
		user.setSex("男");
		user.setUsername("要上天");
		//插入
		sqlSession.insert("saveUser", user);
		//事务提交
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	//用UUID方式自增
	//不能实现 主键int类型
	@Test
	public void saveUser2() {
		//得到SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();
		//实例化User
		User user=new User();
		user.setBirthday(new Date());
		user.setAddress("新疆");
		user.setSex("男");
		user.setUsername("要上天");
		//插入
		sqlSession.insert("saveUser2", user);
		//事务提交
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	//更新
	@Test
	public void updateByUserId() {
		//得到SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();

		//实例化User
		User user=new User();
		user.setBirthday(new Date());
		user.setId(1);
		user.setAddress("乌鲁木齐");
		user.setSex("女");
		user.setUsername("小花");
		
		//更新用户
		sqlSession.update("updateUserById", user);
		//事务提交
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	//删除用户
	@Test
	public void deleteUserById() {
		//得到SqlSession对象
		SqlSession sqlSession=sqlSessionFactory.openSession();

		
		//更新用户
		sqlSession.delete("deleteUserById", 27);
		//事务提交
		sqlSession.commit();
		//释放资源
		sqlSession.close();
	}
	
	
}

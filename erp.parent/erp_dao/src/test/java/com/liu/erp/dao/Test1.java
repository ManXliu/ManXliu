package com.liu.erp.dao;

import org.junit.Assert;
import org.junit.Test;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;



public class Test1 {
	@Test
	public void test(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext_dao.xml","applicationContext_datasource.xml");
		EmpDao bean = (EmpDao) context.getBean("empDao");
		Object object = bean.get((long) 2);
		System.out.println(object.toString());
	}
	@Test
	public void test2(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext*_*.xml");
		DepDao bean = (DepDao) context.getBean("depDao");
		Object object = bean.get((long) 4);
		Assert.assertNotNull(object);
	}

}

package erp_dao;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.liu.erp.dao.EmpDao;



public class TestEmpDao {
	@Test
	public void test(){
		ApplicationContext context=new ClassPathXmlApplicationContext("applicationContext*_*.xml");
		EmpDao bean = (EmpDao) context.getBean("empDao");
		Object object = bean.get((long) 4);
		System.out.println(object.toString());
	}

}

package com.liu.erp.dao;

import com.liu.erp.entity.Emp;

public interface EmpDao<T> extends IBaseDao<Emp> {
	public Emp loginUser(String username,String pwd);


}

package com.liu.erp.biz;

import java.util.List;



public interface BaseBiz<T> {
	
	public List<T> getListByPage(T t1,T t2,Object param,int firstResult,int maxResult);
	public long getCount(T t1,T t2,Object param);
	public void add(T t);
	public void delete(Long id);
	public T get(Long id);
	public void update(T t);
	public List<T> getList(T t1,T t2,Object param);
	public T get(String id);

}

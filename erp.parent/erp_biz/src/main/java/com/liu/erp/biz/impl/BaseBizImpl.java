package com.liu.erp.biz.impl;

import java.util.List;

import com.liu.erp.biz.BaseBiz;
import com.liu.erp.dao.IBaseDao;
import com.liu.erp.dao.impl.BaseDao;



public class BaseBizImpl<T>  implements BaseBiz<T> {
	private IBaseDao<T> baseDao;



	public void setBaseDao(BaseDao baseDao) {
		this.baseDao = baseDao;
	}

	@Override
	public List<T> getListByPage(T t1, T t2, Object param, int firstResult, int maxResult) {
		// TODO Auto-generated method stub
		
		return baseDao.getListByPage(t1, t2, param, firstResult, maxResult);
	}


	@Override
	public long getCount(T t1, T t2, Object param) {
		// TODO Auto-generated method stub
		
		return baseDao.getCount(t1, t2, param);
	}


	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		baseDao.add(t);
	}


	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		baseDao.delete(id);
		
	}


	@Override
	public T get(Long id) {
		// TODO Auto-generated method stub
		return   baseDao.get(id);
	}


	public void update(T t) {
		// TODO Auto-generated method stub
		baseDao.update(t);
	}



	@Override
	public List<T> getList(T t1, T t2, Object param) {
		// TODO Auto-generated method stub
		return baseDao.getList(t1, t2, param);
	}

	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		return baseDao.get(id);
	}
}

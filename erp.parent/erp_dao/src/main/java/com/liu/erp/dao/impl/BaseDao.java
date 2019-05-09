package com.liu.erp.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.Projections;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.liu.erp.dao.IBaseDao;


public class BaseDao<T>  extends HibernateDaoSupport implements IBaseDao<T>{
	private Class<T> entityClass;
	public BaseDao() {
		Type baseDao = getClass().getGenericSuperclass();
		ParameterizedType type=(ParameterizedType)baseDao;
		Type[] actualTypeArguments = type.getActualTypeArguments();
		Type type2 = actualTypeArguments[0];
		entityClass=(Class<T>) type2;
	}

	@Override
	public List<T> getListByPage(T t1, T t2, Object param, int firstResult, int maxResult) {
		DetachedCriteria criteria=getDetachedCriteria(t1, t2, param);

		return (List<T>) getHibernateTemplate().findByCriteria(criteria,firstResult,maxResult);
	}
	@Override
	public long getCount(T t1, T t2, Object param) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=getDetachedCriteria(t1, t2, param);
		criteria.setProjection(Projections.rowCount());
		List<Long> count=(List<Long>) this.getHibernateTemplate().findByCriteria(criteria);
	
		return count.get(0);
	}
	public DetachedCriteria getDetachedCriteria(T t1,T t2,Object param) {
		return null;
	}
	@Override
	public void add(T t) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().save(t);
	}
	@Override
	public void delete(Long id) {
		// TODO Auto-generated method stub
		this.getHibernateTemplate().delete(getHibernateTemplate().get(entityClass, id));
	}
	@Override
	public T get(Long id) {
		// TODO Auto-generated method stub
		
		return (T) getHibernateTemplate().get(entityClass, id);
	}
	@Override
	public void update(T t) {
		// TODO Auto-generated method stub
		getHibernateTemplate().update(t);
		
	}

	@Override
	public List<T> getList(T t1, T t2, Object param) {
		// TODO Auto-generated method stub
		DetachedCriteria criteria=getDetachedCriteria(t1, t2, param);
		return (List<T>) getHibernateTemplate().findByCriteria(criteria);
	}

	@Override
	public T get(String id) {
		// TODO Auto-generated method stub
		return getHibernateTemplate().get(entityClass, id);
	}
}

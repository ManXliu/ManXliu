package com.liu.erp.dao.impl;



import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;

import org.hibernate.criterion.Restrictions;

import com.liu.erp.entity.*;
import com.liu.erp.dao.DepDao;

public class DepDaoImpl extends BaseDao<Dep> implements DepDao<Dep> {

	//父类也有getDetachedCriteria,实际调用子类 的方法
	@Override
	public DetachedCriteria getDetachedCriteria(Dep dep1,Dep dep2,Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Dep.class);
		if(dep1!=null) {
			if(dep1.getName()!=null&&dep1.getName().length()>0) {
				criteria.add(Restrictions.like("name", dep1.getName(),MatchMode.ANYWHERE));
			}
			if(dep1.getTele()!=null&&dep1.getTele().length()>0) {
				criteria.add(Restrictions.like("tele", dep1.getTele(),MatchMode.ANYWHERE));
			}
		}
		return criteria;
	}

}

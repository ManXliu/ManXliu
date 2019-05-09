package com.liu.erp.dao.impl;



import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.liu.erp.dao.EmpDao;

import com.liu.erp.entity.Emp;

public class EmpDaoImpl extends BaseDao<Emp> implements EmpDao<Emp> {
	@Override
	public DetachedCriteria getDetachedCriteria(Emp t1, Emp t2, Object param) {
		DetachedCriteria criteria=DetachedCriteria.forClass(Emp.class);
		if(t1!=null) {
			if(t1.getName()!=null&&t1.getName().trim().length()>0) {
				criteria.add(Restrictions.like("name", t1.getName(),MatchMode.ANYWHERE));
			}
			if(t1.getTele()!=null&&t1.getTele().trim().length()>0) {
				criteria.add(Restrictions.like("tele", t1.getTele(),MatchMode.ANYWHERE));
			}
			if(t1.getAddress()!=null&&t1.getAddress().trim().length()>0) {
				criteria.add(Restrictions.like("address", t1.getAddress(),MatchMode.ANYWHERE));
			}
			if(t1.getUsername()!=null&&t1.getUsername().trim().length()>0) {
				criteria.add(Restrictions.like("username", t1.getUsername(),MatchMode.ANYWHERE));
			}
			if(t1.getPwd()!=null&&t1.getPwd().trim().length()>0) {
				criteria.add(Restrictions.like("pwd", t1.getPwd(),MatchMode.ANYWHERE));
			}
			if(t1.getDep()!=null&&t1.getDep().getUuid()!=null) {
				criteria.add(Restrictions.eq("dep", t1.getDep()));
			}
			if(t1.getEmail()!=null&&t1.getEmail().trim().length()>0) {
				criteria.add(Restrictions.like("email", t1.getEmail()));
			}
			if(t1.getGender()>=0) {
				criteria.add(Restrictions.eq("gender", t1.getGender()));
			}
			if(t1.getBirthday()!=null) {
				System.out.println(t1.getBirthday());
				criteria.add(Restrictions.ge("birthday", t1.getBirthday()));
			}
		
		}
		if(t2!=null&&t2.getBirthday()!=null) {
			System.out.println(t2.getBirthday());
			criteria.add(Restrictions.le("birthday", t2.getBirthday()));
		}
		return criteria;
	}

	@Override
	public Emp loginUser(String username,String pwd) {
		// TODO Auto-generated method stub
		List<Emp> list=(List<Emp>) getHibernateTemplate().find("from Emp where username=? and pwd=?", username,pwd);
		if(list.size()>0) {
			return list.get(0);
		
		}
		return null;
	}


}

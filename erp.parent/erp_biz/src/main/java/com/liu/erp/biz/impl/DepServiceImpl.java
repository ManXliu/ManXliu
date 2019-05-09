package com.liu.erp.biz.impl;


import com.liu.erp.biz.DepBiz;
import com.liu.erp.dao.DepDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Dep;

public class DepServiceImpl extends BaseBizImpl<Dep> implements DepBiz<Dep> {


	private DepDao depDao;

	public void setDepDao(DepDao depDao) {
		this.depDao = depDao;
		super.setBaseDao((BaseDao) depDao);
	}
}

package com.liu.erp.action;


import com.liu.erp.biz.DepBiz;
import com.liu.erp.entity.Dep;

public class DepAction extends BaseAction<Dep>{
	
	private DepBiz depBiz;

	public void setDepBiz(DepBiz depBiz) {
		this.depBiz = depBiz;
		super.setBaseBiz(this.depBiz);
	}
	




}

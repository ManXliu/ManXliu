package com.liu.erp.action;

import com.liu.erp.biz.ISupplierBiz;
import com.liu.erp.entity.Supplier;

/**
 * 供应商Action 
 * @author Administrator
 *
 */
public class SupplierAction extends BaseAction<Supplier> {

	private ISupplierBiz supplierBiz;
	private String q;

	public String getQ() {
		return q;
	}

	public void setQ(String q) {
		this.q = q;
	}

	public void setSupplierBiz(ISupplierBiz supplierBiz) {
		this.supplierBiz = supplierBiz;
		super.setBaseBiz(this.supplierBiz);
	}

	@Override
	public void getList() {
		if(null==getT1()){
			setT1(new Supplier());
		}
		getT1().setName(q);
		super.getList();
	}
}

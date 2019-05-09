package com.liu.erp.biz.impl;

import com.liu.erp.biz.ISupplierBiz;
import com.liu.erp.dao.ISupplierDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Supplier;

/**
 * 供应商业务逻辑类
 * @author Administrator
 *
 */
public class SupplierBiz extends BaseBizImpl<Supplier> implements ISupplierBiz {

	private ISupplierDao supplierDao;
	
	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
		super.setBaseDao((BaseDao) this.supplierDao);
	}
	
}

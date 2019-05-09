package com.liu.erp.biz.impl;

import com.liu.erp.biz.IInventoryBiz;
import com.liu.erp.dao.IInventoryDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Inventory;

/**
 * 盘盈盘亏业务逻辑类
 * @author Administrator
 *
 */
public class InventoryBiz extends BaseBizImpl<Inventory> implements IInventoryBiz {

	private IInventoryDao inventoryDao;
	
	public void setInventoryDao(IInventoryDao inventoryDao) {
		this.inventoryDao = inventoryDao;
		super.setBaseDao((BaseDao) this.inventoryDao);
	}
	
}

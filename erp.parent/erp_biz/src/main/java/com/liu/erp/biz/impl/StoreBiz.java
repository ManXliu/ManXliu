package com.liu.erp.biz.impl;
;
import com.liu.erp.biz.IStoreBiz;
import com.liu.erp.dao.IStoreDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Store;

/**
 * 仓库业务逻辑类
 * @author Administrator
 *
 */
public class StoreBiz extends BaseBizImpl<Store> implements IStoreBiz {

	private IStoreDao storeDao;
	
	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
		super.setBaseDao((BaseDao) this.storeDao);
	}
	
}

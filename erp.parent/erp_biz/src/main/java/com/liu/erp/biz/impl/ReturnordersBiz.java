package com.liu.erp.biz.impl;

import com.liu.erp.biz.IReturnordersBiz;
import com.liu.erp.dao.IReturnordersDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Returnorders;

/**
 * 退货订单业务逻辑类
 * @author Administrator
 *
 */
public class ReturnordersBiz extends BaseBizImpl<Returnorders> implements IReturnordersBiz {

	private IReturnordersDao returnordersDao;
	
	public void setReturnordersDao(IReturnordersDao returnordersDao) {
		this.returnordersDao = returnordersDao;
		super.setBaseDao((BaseDao) this.returnordersDao);
	}
	
}

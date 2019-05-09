package com.liu.erp.biz.impl;

import com.liu.erp.biz.IReturnorderdetailBiz;
import com.liu.erp.dao.IReturnorderdetailDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Returnorderdetail;

/**
 * 退货订单明细业务逻辑类
 * @author Administrator
 *
 */
public class ReturnorderdetailBiz extends BaseBizImpl<Returnorderdetail> implements IReturnorderdetailBiz {

	private IReturnorderdetailDao returnorderdetailDao;
	
	public void setReturnorderdetailDao(IReturnorderdetailDao returnorderdetailDao) {
		this.returnorderdetailDao = returnorderdetailDao;
		super.setBaseDao((BaseDao) this.returnorderdetailDao);
	}
	
}

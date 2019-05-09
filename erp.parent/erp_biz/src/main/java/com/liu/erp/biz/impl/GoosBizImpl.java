package com.liu.erp.biz.impl;

import com.liu.erp.biz.GoodsBiz;
import com.liu.erp.dao.IGoodsDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Goods;

public class GoosBizImpl extends BaseBizImpl<Goods> implements GoodsBiz<Goods> {
	private  IGoodsDao goodsDao;

	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
		super.setBaseDao((BaseDao) goodsDao);
	}
	
}

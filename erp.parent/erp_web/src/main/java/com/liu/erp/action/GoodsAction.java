package com.liu.erp.action;

import com.liu.erp.biz.GoodsBiz;
import com.liu.erp.entity.Goods;


public class GoodsAction extends BaseAction<Goods> {
	private GoodsBiz goodsBiz;

	public void setGoodsBiz(GoodsBiz goodsBiz) {
		this.goodsBiz = goodsBiz;
		super.setBaseBiz(goodsBiz);
	}

}

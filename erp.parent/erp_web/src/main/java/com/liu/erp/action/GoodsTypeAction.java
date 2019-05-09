package com.liu.erp.action;

import com.liu.erp.biz.BaseBiz;
import com.liu.erp.biz.GoodsTypeBiz;
import com.liu.erp.entity.GoodsType;
import com.opensymphony.xwork2.ActionSupport;

public class GoodsTypeAction extends BaseAction<GoodsType> {
	private GoodsTypeBiz goodsTypeBiz;

	public void setGoodsTypeBiz(GoodsTypeBiz goodsTypeBiz) {
		this.goodsTypeBiz = goodsTypeBiz;
		super.setBaseBiz((BaseBiz) goodsTypeBiz);
	}
}

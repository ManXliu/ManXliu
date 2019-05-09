package com.liu.erp.biz.impl;



import com.liu.erp.biz.GoodsTypeBiz;
import com.liu.erp.dao.IGoodstypeDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.GoodsType;

public class GoodsTypeImpl extends BaseBizImpl<GoodsType> implements GoodsTypeBiz<GoodsType> {
	private IGoodstypeDao goodsType;

	public void setGoodsType(IGoodstypeDao goodsType) {
		this.goodsType = goodsType;
		super.setBaseDao((BaseDao) goodsType);
	}


}

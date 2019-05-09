package com.liu.erp.dao.impl;


import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

import com.liu.erp.dao.IGoodstypeDao;
import com.liu.erp.entity.GoodsType;

/**
 * 商品分类数据访问类
 * @author Administrator
 *
 */
public class GoodsTypeDao extends BaseDao<GoodsType> implements IGoodstypeDao<GoodsType> {

	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	@Override
	public DetachedCriteria getDetachedCriteria(GoodsType goodstype1,GoodsType goodstype2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(GoodsType.class);
		if(goodstype1!=null){
			if(null != goodstype1.getName() && goodstype1.getName().trim().length()>0){
				dc.add(Restrictions.like("name", goodstype1.getName(), MatchMode.ANYWHERE));
			}

		}
		return dc;
	}

}

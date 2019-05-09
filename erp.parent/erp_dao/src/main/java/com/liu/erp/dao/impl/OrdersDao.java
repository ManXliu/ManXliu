package com.liu.erp.dao.impl;
import com.liu.erp.dao.IOrdersDao;
import com.liu.erp.entity.Orders;
import org.hibernate.criterion.DetachedCriteria;

import org.hibernate.criterion.Restrictions;
/**
 * 订单数据访问类
 * @author Administrator
 *
 */
public class OrdersDao extends BaseDao<Orders> implements IOrdersDao {

	public DetachedCriteria getDetachedCriteria(Orders orders1,Orders orders2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Orders.class);
		if(orders1!=null){
			if(null != orders1.getType() && orders1.getType().trim().length()>0){
				dc.add(Restrictions.eq("type",orders1.getType()));
			}
			if(null != orders1.getState() && orders1.getState().trim().length()>0){
				dc.add(Restrictions.eq("state", orders1.getState()));
			}
			if (null!=orders1.getCreater()){
				dc.add(Restrictions.eq("creater",orders1.getCreater()));
			}

		}
		return dc;
	}

}

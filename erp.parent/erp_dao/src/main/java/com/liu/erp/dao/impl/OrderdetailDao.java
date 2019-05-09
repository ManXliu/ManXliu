package com.liu.erp.dao.impl;
import com.liu.erp.dao.IOrderdetailDao;
import com.liu.erp.entity.Orderdetail;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

/**
 * 订单明细数据访问类
 * @author Administrator
 *
 */
public class OrderdetailDao extends BaseDao<Orderdetail> implements IOrderdetailDao {

	/**
	 * 构建查询条件
	 * @param dep1
	 * @param dep2
	 * @param param
	 * @return
	 */
	public DetachedCriteria getDetachedCriteria(Orderdetail orderdetail1,Orderdetail orderdetail2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Orderdetail.class);
		if(orderdetail1!=null){
			if(null != orderdetail1.getGoodsname() && orderdetail1.getGoodsname().trim().length()>0){
				dc.add(Restrictions.like("goodsname", orderdetail1.getGoodsname(), MatchMode.ANYWHERE));
			}
			if(null != orderdetail1.getState() && orderdetail1.getState().trim().length()>0){
				dc.add(Restrictions.eq("state", orderdetail1.getState()));
			}
			if (null!=orderdetail1.getOrders()&&null!=orderdetail1.getOrders().getUuid()){
				dc.add(Restrictions.eq("orders",orderdetail1.getOrders()));
			}

		}
		return dc;
	}

}

package com.liu.erp.dao.impl;
import com.liu.erp.dao.IStoreoperDao;
import com.liu.erp.entity.Storeoper;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;


import java.util.Calendar;


/**
 * 仓库操作记录数据访问类
 * @author Administrator
 *
 */
public class StoreoperDao extends BaseDao<Storeoper> implements IStoreoperDao {


	public DetachedCriteria getDetachedCriteria(Storeoper storeoper1,Storeoper storeoper2,Object param){
		DetachedCriteria dc=DetachedCriteria.forClass(Storeoper.class);
		Calendar calendar=Calendar.getInstance();
		if(storeoper1!=null){
			if(null != storeoper1.getType() && storeoper1.getType().trim().length()>0){
				dc.add(Restrictions.eq("type", storeoper1.getType()));
			}
			if (null!=storeoper1.getEmpuuid()){
				dc.add(Restrictions.eq("empuuid",storeoper1.getEmpuuid()));
			}
			if (null!=storeoper1.getStoreuuid()){
				dc.add(Restrictions.eq("storeuuid",storeoper1.getStoreuuid()));
			}
			if (null!=storeoper1.getGoodsuuid()){
				dc.add(Restrictions.eq("goodsuuid",storeoper1.getGoodsuuid()));
			}
			if (null!=storeoper1.getOpertime()){
			/*	Date date=new Date();
				SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
				simpleDateFormat.format(date);*/
				calendar.setTime(storeoper1.getOpertime());
				calendar.set(Calendar.HOUR,0);
				calendar.set(Calendar.MINUTE,0);
				calendar.set(Calendar.SECOND,0);
				calendar.set(Calendar.MILLISECOND,0);
				dc.add(Restrictions.ge("opertime",calendar.getTime()));
			}

		}
		if (storeoper2!=null){
			if (storeoper2.getOpertime()!=null){
				calendar.setTime(storeoper1.getOpertime());
				calendar.set(Calendar.HOUR,23);
				calendar.set(Calendar.MINUTE,59);
				calendar.set(Calendar.SECOND,59);
				calendar.set(Calendar.MILLISECOND,999);
				dc.add(Restrictions.le("opertime",calendar.getTime()));
			}
		}
		return dc;
	}

}

package com.liu.erp.biz.impl;

import com.liu.erp.biz.IOrderdetailBiz;
import com.liu.erp.dao.IOrderdetailDao;
import com.liu.erp.dao.IStoredetailDao;
import com.liu.erp.dao.IStoreoperDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.dao.impl.StoredetailDao;
import com.liu.erp.entity.Orderdetail;
import com.liu.erp.entity.Orders;
import com.liu.erp.entity.Storedetail;
import com.liu.erp.entity.Storeoper;
import com.liu.erp.exception.ErpException;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 订单明细业务逻辑类
 * @author Administrator
 *
 */
public class OrderdetailBiz extends BaseBizImpl<Orderdetail> implements IOrderdetailBiz {

	private IOrderdetailDao orderdetailDao;
	
	public void setOrderdetailDao(IOrderdetailDao orderdetailDao) {
		this.orderdetailDao = orderdetailDao;
		super.setBaseDao((BaseDao) this.orderdetailDao);
	}
	private IStoredetailDao storedetailDao;
	private IStoreoperDao storeoperDao;

	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
	}
	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
	}


	@Override
	public void doInStore(Long orderDetailId, Long empId, Long storeId) {
		Orderdetail orderdetail=orderdetailDao.get(orderDetailId);
		if (!orderdetail.getState().equals(Orderdetail.STATE_NOT_IN)){
			throw new ErpException("亲,商品已经入库过了!!!!");
		}
		//商品详情处理
		orderdetail.setEnder(empId);
		orderdetail.setEndtime(new Date());
		orderdetail.setState(Orderdetail.STATE_IN);
		orderdetail.setStoreuuid(storeId);


		//仓库操作表的设置
		Storeoper storeoper=new Storeoper();
		storeoper.setEmpuuid(empId);
		storeoper.setType(Storeoper.INSTORE);
		storeoper.setStoreuuid(storeId);
		storeoper.setGoodsuuid(orderdetail.getGoodsuuid());
		storeoper.setNum(orderdetail.getNum());
		storeoper.setOpertime(new Date());
		storeoperDao.add(storeoper);

		//仓库表处理

		Storedetail storedetail=new Storedetail();
		storedetail.setGoodsuuid(orderdetail.getGoodsuuid());
		storedetail.setStoreuuid(orderdetail.getStoreuuid());
		storedetail.setStoreuuid(storeId);
		List<Storedetail> list=storedetailDao.getList(storedetail,null,null);
		if (null!=list&&list.size()>0){
			Storedetail storeDetail1=list.get(0);
			storeDetail1.setNum(storeDetail1.getNum()+orderdetail.getNum());
		}else {
			storedetailDao.add(storedetail);
		}
		//订单表的操作
		Orders order=orderdetail.getOrders();
		Orderdetail count=new Orderdetail();
		count.setOrders(order);
		count.setState(Orderdetail.STATE_NOT_IN);
		Long num=orderdetailDao.getCount(count,null,null);
		if (num==0){
			order.setEnder(empId);
			order.setState(Orders.STATE_END);
			order.setEndtime(Calendar.getInstance().getTime());
		}
	}

	@Override
	public void doOutStore(Long orderDetailId, Long empId, Long storeId) {
		Orderdetail orderdetail=orderdetailDao.get(orderDetailId);
		if (!orderdetail.getState().equals(Orderdetail.START_NOT_OUT)){
			throw new ErpException("亲.订单已经出库!");
		}
		//订单更新
		orderdetail.setEndtime(new Date());
		orderdetail.setEnder(empId);
		orderdetail.setState(Orderdetail.START_OUT);
		orderdetail.setStoreuuid(storeId);

		//查询库存,是否够用
		Storedetail storedetail=new Storedetail();
		storedetail.setStoreuuid(storeId);
		storedetail.setGoodsuuid(orderdetail.getGoodsuuid());
		List<Storedetail> list=storedetailDao.getList(storedetail,null,null);
		long num=-1;
		if (null!=list&&list.size()>0){
			Storedetail detaile=list.get(0);
			num=detaile.getNum().longValue()-orderdetail.getNum().longValue();
		}
		if (num>0){
			list.get(0).setNum(num);
		}else{
			throw new ErpException("亲!库存不足");
		}
		//添加库存操作表
		Storeoper storeoper=new Storeoper();
		storeoper.setOpertime(Calendar.getInstance().getTime());
		storeoper.setNum(orderdetail.getNum());
		storeoper.setGoodsuuid(orderdetail.getGoodsuuid());
		storeoper.setStoreuuid(storeId);
		storeoper.setType(Storeoper.OUTSTORE);
		storeoper.setEmpuuid(empId);
		storeoperDao.add(storeoper);

		//查看订单是否全部入库
		Orders orders=orderdetail.getOrders();
		Orderdetail queryparam=new Orderdetail();
		queryparam.setOrders(orders);
		queryparam.setState("0");
		long cnt=orderdetailDao.getCount(queryparam,null,null);
		if (cnt==0){
			orders.setEndtime(new Date());
			orders.setEnder(empId);
			orders.setState("1");
		}

	}
}

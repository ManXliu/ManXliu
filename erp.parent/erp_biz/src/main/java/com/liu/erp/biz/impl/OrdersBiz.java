package com.liu.erp.biz.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.liu.erp.biz.IOrdersBiz;
import com.liu.erp.dao.*;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.dao.impl.OrdersDao;
import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Orderdetail;
import com.liu.erp.entity.Orders;
import com.liu.erp.entity.Supplier;
import com.liu.erp.exception.ErpException;

/**
 * 订单业务逻辑类
 * @author Administrator
 *
 */
public class OrdersBiz extends BaseBizImpl<Orders> implements IOrdersBiz {

	private IOrdersDao ordersDao;

	private EmpDao empDao;

	private IStoreDao storeDao;

	private IStoredetailDao storedetailDao;

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public void setSupplierDao(ISupplierDao supplierDao) {
		this.supplierDao = supplierDao;
	}

	private ISupplierDao supplierDao;
	
	public void setOrdersDao(IOrdersDao ordersDao) {
		this.ordersDao = ordersDao;
		super.setBaseDao((BaseDao) this.ordersDao);
	}
	
	public void add(Orders orders){
		//1. 设置订单的状态
		orders.setState(Orders.STATE_CREATE);
		//2. 订单的类型
	//	orders.setType(Orders.TYPE_IN);
		//3. 下单时间
		orders.setCreatetime(new Date());
		
		// 合计金额
		double total = 0;
		
		for(Orderdetail detail : orders.getOrderDetails()){
			//累计金额
			total += detail.getMoney();
			//明细的状态
			detail.setState(Orderdetail.STATE_NOT_IN);
			//跟订单的关系
			detail.setOrders(orders);
		}
		//设置订单总金额
		orders.setTotalmoney(total);
		
		//保存到DB
		ordersDao.add(orders);
	}
	@Override
	public List<Orders> getListByPage(Orders t1, Orders t2, Object param, int firstResult, int maxResult) {
		List<Orders> list=super.getListByPage(t1, t2, param, firstResult, maxResult);
		HashMap hashMapName=new HashMap<Long,String>();
		HashMap supplierMap=new HashMap<Long,String>();
		for (Orders o:list) {
			o.setCreaterName(empName(o.getCreater(),hashMapName));
			o.setCheckerName(empName(o.getChecker(),hashMapName));
			o.setStarterName(empName(o.getStarter(),hashMapName));
			o.setEnderName(empName(o.getEnder(),hashMapName));
			o.setSupplierName(supplierName(o.getSupplieruuid(),supplierMap));
		}
	//	return super.getListByPage(t1,t2,param,firstResult,maxResult);
		return list;

	}
	private String empName(Long id, HashMap<Long,String> hashMapName){
		if (null==id){
			return null;
		}
		if (null==hashMapName.get(id)){
			Emp emp= (Emp) empDao.get(id);
			hashMapName.put(id,emp.getName());
			return emp.getName();
		}
		return hashMapName.get(id);

	}
	private String supplierName(Long id,HashMap<Long,String> supplierMap){
		if (null==id){
			return null;
		}
		if (null==supplierMap.get(id)){
			Supplier supplier=supplierDao.get(id);
			supplierMap.put(id,supplier.getName());
			return supplier.getName();
		}
		return supplierMap.get(id);
	}


	@Override
	public void doCheck(Long orederId, Long empId) {
		Orders orders=ordersDao.get(orederId);
		if (!orders.getState().equals(Orders.STATE_CREATE)){
			throw new ErpException("亲,订单已审核");
		}
		orders.setChecker(empId);
		orders.setChecktime(new Date());
		orders.setState(Orders.STATE_CHECK);
	}

	@Override
	public void doStart(Long orderId, Long empId) {
		Orders orders=ordersDao.get(orderId);
		if(!Orders.STATE_CHECK.equals(orders.getState())){
			throw new ErpException("请订单已确认");
		}
		orders.setState(Orders.STATE_START);
		orders.setStarter(empId);
		orders.setStarttime(new Date());
	}
}

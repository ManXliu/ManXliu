package com.liu.erp.action;
import java.util.List;

import com.alibaba.fastjson.JSON;
import com.liu.erp.biz.IOrdersBiz;
import com.liu.erp.biz.impl.OrdersBiz;
import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Orderdetail;
import com.liu.erp.entity.Orders;
import com.liu.erp.exception.ErpException;

/**
 * 订单Action 
 * @author Administrator
 *
 */
public class OrdersAction extends BaseAction<Orders> {

	private IOrdersBiz ordersBiz;

	public void setOrdersBiz(IOrdersBiz ordersBiz) {
		this.ordersBiz = ordersBiz;
		super.setBaseBiz(this.ordersBiz);
	}
	
	//接收订单明细的json格式的字符,数组形式的json字符串,里面的元素应该是每个订单明细
	private String json;
	public String getJson() {
		return json;
	}
	public void setJson(String json) {
		this.json = json;
	}
	
	/**
	 * 添加订单
	 */
	public void add(){
		
		Emp loginUser = getUser();
		if(null == loginUser){
			//用户没有登陆，session已失效
			write(ajaxReturn(false, "亲！您还没有登陆"));
			return;
		}
		try {
			//System.out.println(json);
			Orders orders = getT();
			//订单创建者
			orders.setCreater(loginUser.getUuid());
			List<Orderdetail> detailList = JSON.parseArray(json,Orderdetail.class);
			//订单明细
			orders.setOrderDetails(detailList);
			//System.out.println(detailList.size());
			ordersBiz.add(orders);
			write(ajaxReturn(true, "添加订单成功"));
		} catch (Exception e) {
			write(ajaxReturn(false, "添加订单失败"));
			e.printStackTrace();
		}
	}
	public void doCheck(){
		Emp emp=getUser();
		if (null==emp){
			write(ajaxReturn(false,"亲你还没有登录!"));
			return;
		}
		try {
			ordersBiz.doCheck(getId(),emp.getUuid());
			write(ajaxReturn(true,"亲,订单审核成功"));

		}catch (ErpException e){
			write(ajaxReturn(false,e.getMessage()));
		}catch (Exception e){
			write(ajaxReturn(false,"订单审核失败"));
		}
	}
	public void doStart(){
		Emp emp=getUser();
		if (null==emp){
			write(ajaxReturn(false,"亲,你还没有登录"));
			return;
		}
		try {
			ordersBiz.doStart(getId(), emp.getUuid());
			write(ajaxReturn(true,"确认成功"));
		}catch (ErpException e){
			write(ajaxReturn(false,e.getMessage()));
		}catch (Exception e){
			write(ajaxReturn(false,"确认失败"));
		}
	}
	public void myListByPage(){
		if (null==getT1()){
			setT1(new Orders());
		}
		Emp emp=getUser();
		getT1().setCreater(emp.getUuid());
		super.getListByPage();
	}

}

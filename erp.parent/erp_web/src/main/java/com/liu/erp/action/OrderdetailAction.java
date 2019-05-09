package com.liu.erp.action;
import com.liu.erp.biz.IOrderdetailBiz;
import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Orderdetail;
import com.liu.erp.exception.ErpException;

/**
 * 订单明细Action 
 * @author Administrator
 *
 */
public class OrderdetailAction extends BaseAction<Orderdetail> {

	private IOrderdetailBiz orderdetailBiz;

	private Long storeuuid;

	public Long getStoreuuid() {
		return storeuuid;
	}

	public void setStoreuuid(Long storeuuid) {
		this.storeuuid = storeuuid;
	}

	public void setOrderdetailBiz(IOrderdetailBiz orderdetailBiz) {
		this.orderdetailBiz = orderdetailBiz;
		super.setBaseBiz(this.orderdetailBiz);
	}
	public void doInStore(){

		if (null==getUser()){
			write(ajaxReturn(false,"亲,你还没有登录."));
			return;
		}
		try {
			orderdetailBiz.doInStore(getId(),getUser().getUuid(),storeuuid);
			write(ajaxReturn(true,"入库成功!"));

		}catch (ErpException e){
			write(ajaxReturn(false,e.getMessage()));
		}catch (Exception e){
			e.printStackTrace();
			write(ajaxReturn(false,"入库失败"));
		}

	}
	public void doOutStore(){
		Emp emp=getUser();
		if (emp==null){
			write(ajaxReturn(false,"亲!请登录!"));
			return;
		}
		try {
			orderdetailBiz.doOutStore(getId(),getUser().getUuid(),storeuuid);
			write(ajaxReturn(true,"用户出库成功"));
		}catch (ErpException e){
			write(ajaxReturn(false,e.getMessage()));
		}catch (Exception e){
			write(ajaxReturn(false,"出库失败!"));
		}
	}

}

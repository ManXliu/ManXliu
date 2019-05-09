package com.liu.erp.action;

import com.liu.erp.biz.IStoreBiz;
import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Store;

/**
 * 仓库Action 
 * @author Administrator
 *
 */
public class StoreAction extends BaseAction<Store> {

	private IStoreBiz storeBiz;

	public void setStoreBiz(IStoreBiz storeBiz) {
		this.storeBiz = storeBiz;
		super.setBaseBiz(this.storeBiz);
	}
	public void myList(){
		if (null==getT1()){
			setT1(new Store());
		}

		Emp emp=getUser();
		if (null==emp){
			write(ajaxReturn(false,"亲!请登录"));
		}else{
			getT1().setEmpuuid(emp.getUuid());
			super.getList();
		}


	}

}

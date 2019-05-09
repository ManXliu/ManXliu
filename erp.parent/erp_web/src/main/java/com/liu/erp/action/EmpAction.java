package com.liu.erp.action;


import com.alibaba.fastjson.JSON;
import com.liu.erp.biz.EmpBiz;
import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Tree;
import com.liu.erp.exception.ErpException;

import java.util.List;


public class EmpAction extends BaseAction<Emp>{
	
	private EmpBiz empBiz;
	private String oldPwd;
	private String newPwd;
	private String checkStr;

	public String getCheckStr() {
		return checkStr;
	}

	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}
	public String getNewPwd() {
		return newPwd;
	}

	public void setNewPwd(String newPwd) {
		this.newPwd = newPwd;
	}

	public String getOldPwd() {
		return oldPwd;
	}

	public void setOldPwd(String oldPwd) {
		this.oldPwd = oldPwd;
	}


	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
		super.setBaseBiz(this.empBiz);
	}
	public void updatePwd() {
		try {
			empBiz.updatePwd(getUser().getUuid(), oldPwd, newPwd);
			write(ajaxReturn(true, "修改成功"));
			
		} catch (ErpException e) {
			write(ajaxReturn(false, e.getMessage()));
		}catch(Exception e) {
			write(ajaxReturn(false, "密码修改失败"));
		}
		
	}
	public void update_reset() {
		try {
			System.out.println("id"+getId()+"newPwd"+newPwd);
			empBiz.update_reset(getId(),newPwd);
			write(ajaxReturn(true, "重置密码成功"));
		} catch (Exception e) {
			write(ajaxReturn(false, "重置密码失败"));

		}
		
	}
	public void readEmpRoles(){
		List<Tree> roleList=empBiz.readEmpRoles(getId());
		write(JSON.toJSONString(roleList));
	}
	public void updateEmpRoles(){
		try {
			empBiz.updateEmpRoles(getId(),checkStr);
			write(ajaxReturn(true,"更新成功"));
		}catch (Exception e){
			e.printStackTrace();
			write(ajaxReturn(false,"更新失败"));
		}
	}







}

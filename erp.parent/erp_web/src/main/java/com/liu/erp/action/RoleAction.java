package com.liu.erp.action;

import com.alibaba.fastjson.JSON;
import com.liu.erp.biz.IRoleBiz;
import com.liu.erp.entity.Role;
import com.liu.erp.entity.Tree;

import java.util.List;

/**
 * 角色Action 
 * @author Administrator
 *
 */
public class RoleAction extends BaseAction<Role> {

	private IRoleBiz roleBiz;
	private String checkStr;

	public String getCheckStr() {
		return checkStr;
	}

	public void setCheckStr(String checkStr) {
		this.checkStr = checkStr;
	}

	public void setRoleBiz(IRoleBiz roleBiz) {
		this.roleBiz = roleBiz;
		super.setBaseBiz(this.roleBiz);
	}
	public void readRoleMenus(){
		List<Tree> list=roleBiz.readRoleMenus(getId());
		write(JSON.toJSONString(list));
	}
	public void updateRoleMenus(){
		try {
			roleBiz.updateRoleMenus(getId(),checkStr);
			write(ajaxReturn(true,"更新成功"));
		}catch (Exception e){
			write(ajaxReturn(false,"更新失败"));
			e.printStackTrace();
		}
	}

}

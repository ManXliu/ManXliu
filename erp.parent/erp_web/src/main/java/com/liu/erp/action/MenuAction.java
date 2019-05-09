package com.liu.erp.action;

import com.alibaba.fastjson.JSON;
import com.liu.erp.biz.MenuBiz;
import com.liu.erp.entity.Menu;

public class MenuAction extends BaseAction<Menu> {
	private MenuBiz menuBiz;

	public void setMenuBiz(MenuBiz menuBiz) {
		this.menuBiz = menuBiz;
		super.setBaseBiz(this.menuBiz);
	}
	public void getMenuTree() {
		Menu menu=(Menu) menuBiz.get("0");
		String jsonString = JSON.toJSONString(menu);
		write(jsonString);
	}
	

}

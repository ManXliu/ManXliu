package com.liu.erp.biz.impl;

import com.liu.erp.biz.MenuBiz;
import com.liu.erp.dao.MenuDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Menu;

public class MenuBizImpl<T> extends BaseBizImpl<Menu> implements MenuBiz<Menu> {
	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
		super.setBaseDao((BaseDao) this.menuDao);
	}

}

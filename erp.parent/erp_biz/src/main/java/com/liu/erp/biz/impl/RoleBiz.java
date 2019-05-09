package com.liu.erp.biz.impl;

import com.liu.erp.biz.IRoleBiz;
import com.liu.erp.dao.IRoleDao;
import com.liu.erp.dao.MenuDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.entity.Menu;
import com.liu.erp.entity.Role;
import com.liu.erp.entity.Tree;

import java.util.ArrayList;
import java.util.List;

/**
 * 角色业务逻辑类
 * @author Administrator
 *
 */
public class RoleBiz extends BaseBizImpl<Role> implements IRoleBiz {

	private IRoleDao roleDao;

	private MenuDao menuDao;

	public void setMenuDao(MenuDao menuDao) {
		this.menuDao = menuDao;
	}

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
		super.setBaseDao((BaseDao) this.roleDao);
	}

	@Override
	public List<Tree> readRoleMenus(Long uuid) {
		List<Tree> treeList=new ArrayList<Tree>();
		List<Menu> rolemenu=roleDao.get(uuid).getMenus();
		Menu root= (Menu) menuDao.get("0");
		Tree t1=null;
		Tree t2=null;
		for (Menu m1:root.getMenus()) {
			t1=new Tree();
			t1.setId(m1.getMenuid());
			t1.setText(m1.getMenuname());
			for (Menu m2:m1.getMenus()){
				t2=new Tree();
				t2.setId(m2.getMenuid());
				t2.setText(m2.getMenuname());
				if (rolemenu.contains(m2)){
					t2.setChecked(true);
				}
				t1.getChildren().add(t2);
			}
			treeList.add(t1);
		}
		return treeList;
	}

	@Override
	public void updateRoleMenus(Long uuid, String checkStr) {
		Role role=roleDao.get(uuid);

		role.setMenus(new ArrayList<Menu>());

		String[] ids=checkStr.split(",");
		Menu menu=null;
		for (String id:ids){
			menu= (Menu) menuDao.get(id);
			role.getMenus().add(menu);
		}

	}
}

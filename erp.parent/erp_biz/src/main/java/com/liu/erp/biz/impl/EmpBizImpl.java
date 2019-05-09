package com.liu.erp.biz.impl;


import com.liu.erp.dao.IRoleDao;
import com.liu.erp.entity.Role;
import com.liu.erp.entity.Tree;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.beans.factory.annotation.Autowired;

import com.liu.erp.biz.EmpBiz;
import com.liu.erp.dao.EmpDao;
import com.liu.erp.dao.impl.BaseDao;

import com.liu.erp.entity.Emp;
import com.liu.erp.exception.ErpException;

import java.util.ArrayList;
import java.util.List;

public class EmpBizImpl extends BaseBizImpl<Emp> implements EmpBiz<Emp> {


	private EmpDao empDao;
	private IRoleDao roleDao;

	public void setRoleDao(IRoleDao roleDao) {
		this.roleDao = roleDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
		super.setBaseDao((BaseDao) empDao);
	}
	@Override
	public Emp loginUser(String username, String pwd) {
		// TODO Auto-generated method stub
		Md5Hash md5Hash=new Md5Hash(pwd, username, 2);

		
		return empDao.loginUser(username,md5Hash.toString());
	}
	@Override
	public void add(Emp t) {
		// TODO Auto-generated method stub
		String salt=t.getName();
		Md5Hash md5Hash=new Md5Hash(t.getUsername(), salt, 2);
		t.setPwd(md5Hash.toString());
		empDao.add(t);
		
	}

	@Override
	public void updatePwd(Long uuid, String oldPwd, String newPwd) {
		Emp emp=(Emp) empDao.get(uuid);
		Md5Hash md5Hash=new Md5Hash(oldPwd, emp.getUsername(), 2);
		if(!md5Hash.toString().equals(emp.getPwd())) {
			throw new ErpException("原密码不正确");
		}
		Md5Hash md5Hash2=new Md5Hash(newPwd, emp.getUsername(), 2);
		emp.setPwd(md5Hash2.toString());	
	}
	@Override
	public void update_reset(long uuid, String newPwd) {
		Emp emp = (Emp) empDao.get(uuid);
		Md5Hash md5Hash=new Md5Hash(newPwd, emp.getUsername(),2);
		emp.setPwd(md5Hash.toString());		
	}

	@Override
	public List<Tree> readEmpRoles(Long uuid) {
		List<Tree> treeList=new ArrayList<Tree>();

		Emp emp= (Emp) empDao.get(uuid);
		//获取用户下的所有角色
		List<Role> empRoles=emp.getRoles();

		//获取所有角色
		List<Role> roleList=roleDao.getList(null,null,null);
		Tree t1=null;
		for (Role role:roleList){
			t1=new Tree();
			t1.setText(role.getName());
			t1.setId(String.valueOf(role.getUuid()));
			if (empRoles.contains(role)){
				t1.setChecked(true);
			}
			treeList.add(t1);
		}
		return treeList;
	}

	@Override
	public void updateEmpRoles(Long uuid, String checkStr) {
		Emp emp= (Emp) empDao.get(uuid);
		emp.setRoles(new ArrayList<Role>());
		String[] ids=checkStr.split(",");
		Role role=null;
		for (String id:ids){
			role=roleDao.get(Long.valueOf(id));
			emp.getRoles().add(role);
		}

	}


}

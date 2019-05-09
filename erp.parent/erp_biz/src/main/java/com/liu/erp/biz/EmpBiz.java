package com.liu.erp.biz;


import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Tree;

import java.util.List;

public interface EmpBiz<T> extends BaseBiz<Emp> {
	Emp loginUser(String username,String pwd);
	void updatePwd(Long uuid,String oldPwd,String newPwd);
	void update_reset(long uuid,String newPwd);
	List<Tree> readEmpRoles(Long uuid);
	void updateEmpRoles(Long uuid,String checkStr);

	

}

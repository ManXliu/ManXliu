package com.liu.erp.biz;
import com.liu.erp.entity.Role;
import com.liu.erp.entity.Tree;

import java.util.List;

/**
 * 角色业务逻辑层接口
 * @author Administrator
 *
 */
public interface IRoleBiz extends BaseBiz<Role>{
    List<Tree> readRoleMenus(Long uuid);

    void updateRoleMenus(Long uuid,String checkStr);

}


package com.liu.erp.realm;

import com.liu.erp.biz.EmpBiz;
import com.liu.erp.entity.Emp;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

public class ErpRealm extends AuthorizingRealm {
    private EmpBiz empBiz;

    public void setEmpBiz(EmpBiz empBiz) {
        this.empBiz = empBiz;
    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        System.out.println("授权.........");
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("认证............");
        UsernamePasswordToken upt= (UsernamePasswordToken) token;
        String password=new String(upt.getPassword());
        Emp emp=empBiz.loginUser(upt.getUsername(),password);
        if (null!=emp){
            return new SimpleAuthenticationInfo(emp,password,getName());
        }
        return null;
    }
}

package com.liu.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.liu.erp.biz.EmpBiz;

import com.liu.erp.entity.Emp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class LoginAction extends ActionSupport{
	private EmpBiz empBiz;
	private String username;
	private String pwd;

	public void setEmpBiz(EmpBiz empBiz) {
		this.empBiz = empBiz;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}

	public void checkUser() {
		try {
/*			UsernamePasswordToken upt=new UsernamePasswordToken(username,pwd);

			Subject subject=SecurityUtils.getSubject();
			subject.login(upt);*/
			Emp loginuser=empBiz.loginUser(username, pwd);
			if(null==loginuser) {
				write(ajaxReturn(false, "用户名或者密码错误"));
				return;
			}
			ActionContext.getContext().getSession().put("loginuser",loginuser);
			write(ajaxReturn(true, "登录成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false, "登录失败"));
		}

	}
	
	public void showName() {
		Emp loginuser=(Emp)ActionContext.getContext().getSession().get("loginuser");
		//Emp loginuser=(Emp) SecurityUtils.getSubject().getPrincipal();
		if(loginuser==null) {
			write(ajaxReturn(false, "请登录"));
		}else {
			write(ajaxReturn(true, loginuser.getName()));
		}
	}
	public void loginOut() {
		SecurityUtils.getSubject().logout();
		//ActionContext.getContext().getSession().remove("loginuser");
	
	}
	public String ajaxReturn(boolean success,String message) {
		Map map=new HashMap<>();
		map.put("success", success);
		map.put("message", message);
		String jsonString=JSON.toJSONString(map);
		return jsonString;
	}
	public void write(String jsonString) {
		HttpServletResponse response=ServletActionContext.getResponse();
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html;charset=UTF-8");
		try {
			response.getWriter().print(jsonString);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}


}

package com.liu.erp.action;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.SecurityUtils;
import org.apache.struts2.ServletActionContext;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.liu.erp.biz.BaseBiz;
import com.liu.erp.entity.Emp;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class BaseAction<T> extends ActionSupport{
	private BaseBiz<T> baseBiz;

	public void setBaseBiz(BaseBiz<T> baseBiz) {
		this.baseBiz = baseBiz;
	}
	private T t1;
	private T t2;
	private T t;
	private Long id;
	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public T getT() {
	
		return t;
	}


	public void setT(T t) {
		this.t = t;
	}


	public T getT2() {
		return t2;
	}


	public void setT2(T t2) {
		this.t2 = t2;
	}
	private int page;
	private int rows;
	private Object param;

	public Object getParam() {
		return param;
	}


	public void setParam(Object param) {
		this.param = param;
	}


	public int getPage() {
		return page;
	}


	public void setPage(int page) {
		this.page = page;
	}


	public int getRows() {
		return rows;
	}


	public void setRows(int rows) {
		this.rows = rows;
	}


	public T getT1() {
		return t1;
	}


	public void setT1(T t1) {
		this.t1 = t1;
	}



	public void getListByPage() {
		long count=baseBiz.getCount(t1, t2, param);
		int fisrtResult=(page-1)*rows;
		List<T> list = baseBiz.getListByPage(t1, t2, param, fisrtResult, rows);
		Map map=new HashMap<>();
		map.put("total", count);
		map.put("rows", list);
//		System.out.println(list.size());
		String jsonString = JSON.toJSONString(map,SerializerFeature.DisableCircularReferenceDetect);
		write(jsonString);

	}
	public void getList() {
		List<T> list=baseBiz.getList(t1, t2, param);
		String josnSting=JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
		write(josnSting);
	}
	public void add() {
		try {
			baseBiz.add(t);
			write(ajaxReturn(true,"添加成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false,"添加失败"));
		}
		
	}
	public void delete() {
		try {
		
			baseBiz.delete(id);
			write(ajaxReturn(true,"删除成功"));
		} catch (Exception e) {
			e.printStackTrace();
			write(ajaxReturn(false,"删除失败"));
		}
	}
	public void get() {
		T t= baseBiz.get(id);
		String jsonString = JSON.toJSONStringWithDateFormat(t, "yyyy-MM-dd");
		System.out.println(jsonString);
		write(MapJson(jsonString, "t"));
	}
	public void update() {
		try {
			baseBiz.update(t);
			write(ajaxReturn(true,"修改成功"));
			
		} catch (Exception e) {
			// TODO: handle exception
			write(ajaxReturn(false,"修改失败"));
		}
	}
	public String MapJson(String josnString,String prefix) {
		Map<String,Object> map=JSON.parseObject(josnString);
		Map<String,Object> newmap=new HashMap();
		for (String key : map.keySet()) {
			if(map.get(key) instanceof Map) {
				Map<String,Object> map2=(Map<String,Object>)map.get(key);
				for (String key2 : map2.keySet()) {
					newmap.put(prefix+"."+key+"."+key2,map.get(key2));
				}
			}else {
				newmap.put(prefix+"."+key,map.get(key));
			}
		}
		return JSON.toJSONString(newmap);
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
	public Emp getUser() {
		//return (Emp)SecurityUtils.getSubject().getPrincipal();
		return (Emp) ActionContext.getContext().getSession().get("loginuser");
	}
	

}

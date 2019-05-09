package com.liu.erp.biz.impl;

import com.liu.erp.biz.IStoreoperBiz;
import com.liu.erp.dao.EmpDao;
import com.liu.erp.dao.IGoodsDao;
import com.liu.erp.dao.IStoreDao;
import com.liu.erp.dao.IStoreoperDao;
import com.liu.erp.dao.impl.BaseDao;

import com.liu.erp.entity.Emp;
import com.liu.erp.entity.Goods;
import com.liu.erp.entity.Store;
import com.liu.erp.entity.Storeoper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库操作记录业务逻辑类
 * @author Administrator
 *
 */
public class StoreoperBiz extends BaseBizImpl<Storeoper> implements IStoreoperBiz {

	private IStoreoperDao storeoperDao;
	private IGoodsDao goodsDao;
	private EmpDao empDao;
	private IStoreDao storeDao;
	
	public void setStoreoperDao(IStoreoperDao storeoperDao) {
		this.storeoperDao = storeoperDao;
		super.setBaseDao((BaseDao) this.storeoperDao);
	}
	public List<Storeoper> getListByPage(Storeoper t1,Storeoper t2,Object parm,int firstResult,int maxResult){
		List<Storeoper> list=super.getListByPage(t1,t2,parm,firstResult,maxResult);
		Map empMap=new HashMap<Long,String>();
		Map goodsMap=new HashMap<Long,String>();
		Map storeMap=new HashMap<Long,String>();
		for (Storeoper so:list) {
			so.setEmpname(getEmpName(so.getEmpuuid(),empMap));
			so.setGoodsname(getGoodsName(so.getGoodsuuid(),goodsMap));
			so.setStorename(getStoreName(so.getStoreuuid(),storeMap));
		}
		return list;
	}
	private String getStoreName(Long uuid, Map<Long,String> storeMap){
		if (uuid==null){
			return null;
		}
		String name=storeMap.get(uuid);
		if (name==null){
			Store store=storeDao.get(uuid);
			name=store.getName();
		}
		return name;
	}
	private String getGoodsName(Long uuid, Map<Long,String> goodsMap){
		if (uuid==null){
			return null;
		}
		String name=goodsMap.get(uuid);
		if (name==null){
			Goods goods= (Goods) goodsDao.get(uuid);
			name=goods.getName();
		}
		return name;

	}
	private String getEmpName(Long uuid, Map<Long,String> empMap){
		if (uuid==null){
			return null;
		}
		String name=empMap.get(uuid);
		if (name==null){
			Emp emp= (Emp) empDao.get(uuid);
			name=emp.getName();
		}
		return name;

	}
	public void setGoodsDao(IGoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setEmpDao(EmpDao empDao) {
		this.empDao = empDao;
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

}

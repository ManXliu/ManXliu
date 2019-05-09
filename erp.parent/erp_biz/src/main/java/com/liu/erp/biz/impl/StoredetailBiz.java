package com.liu.erp.biz.impl;

import com.liu.erp.biz.IStoredetailBiz;
import com.liu.erp.dao.IStoreDao;
import com.liu.erp.dao.IStoredetailDao;
import com.liu.erp.dao.impl.BaseDao;
import com.liu.erp.dao.impl.GoodsDao;
import com.liu.erp.entity.Goods;
import com.liu.erp.entity.Storedetail;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 仓库库存业务逻辑类
 * @author Administrator
 *
 */
public class StoredetailBiz extends BaseBizImpl<Storedetail> implements IStoredetailBiz {

	private IStoredetailDao storedetailDao;

	public void setGoodsDao(GoodsDao goodsDao) {
		this.goodsDao = goodsDao;
	}

	public void setStoreDao(IStoreDao storeDao) {
		this.storeDao = storeDao;
	}

	private GoodsDao goodsDao;
	private IStoreDao storeDao;
	
	public void setStoredetailDao(IStoredetailDao storedetailDao) {
		this.storedetailDao = storedetailDao;
		super.setBaseDao((BaseDao) this.storedetailDao);
	}

	@Override
	public List<Storedetail> getListByPage(Storedetail t1, Storedetail t2, Object param, int firstResult, int maxResult) {
		List<Storedetail> list=super.getListByPage(t1, t2, param, firstResult, maxResult);
		Map<Long,String> goodsNameMap=new HashMap<Long,String>();
		Map<Long,String> storeNameMap=new HashMap<Long,String>();
		for (Storedetail sd:list) {
			sd.setGoodsName(showGoodsName(sd.getGoodsuuid(),goodsNameMap));
			sd.setStoreName(showStoreName(sd.getStoreuuid(),storeNameMap));
		}
		return list;

	}
	private String showGoodsName(Long uuid,Map<Long,String> goodsMapName){
		if (uuid==null){
			return null;
		}
		if (goodsMapName.get(uuid)!=null){
			return  goodsMapName.get(uuid);
		}
		else{
			Goods goods=goodsDao.get(uuid);
			goodsMapName.put(uuid,goods.getName());
			return goods.getName();
		}
	}
	private String showStoreName(Long uuid,Map<Long,String> storeMapName){
		if (uuid==null){
			return null;
		}
		String storeName=storeMapName.get(uuid);
		if (storeName==null){
			storeName=storeDao.get(uuid).getName();
			storeMapName.put(uuid,storeName);
		}
		return storeName;

	}
}

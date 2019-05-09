package com.liu.erp.biz;

import com.liu.erp.entity.Orderdetail;

/**
 * 订单明细业务逻辑层接口
 * @author Administrator
 *
 */
public interface IOrderdetailBiz extends BaseBiz<Orderdetail>{
    void doInStore(Long orderDetailId,Long empId,Long storeId);
    void doOutStore(Long orderDetailId,Long empId,Long storeId);
}


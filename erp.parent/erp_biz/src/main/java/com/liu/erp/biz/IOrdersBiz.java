package com.liu.erp.biz;

import com.liu.erp.entity.Orders;

/**
 * 订单业务逻辑层接口
 * @author Administrator
 *
 */
public interface IOrdersBiz extends BaseBiz<Orders>{
    void doCheck(Long orderId,Long empId);
    void doStart(Long orderId,Long empId);
}



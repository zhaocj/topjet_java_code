package com.topjet.manage.service;

import com.topjet.manage.domain.bean.OrderListAdvertBean;
import com.topjet.manage.domain.model.OrderListAdvertModel;

import java.util.List;

/**
 * Created by liyj on 2017/10/23.
 */
public interface OrderListAdvertService {

    /**
     *货源列表广告查询
     */
    public List<OrderListAdvertBean> getAdvertList(OrderListAdvertBean orderListAdvertBean);
    /**
     * 货源列表广告页数
     */
    public Integer getAdvertCount(OrderListAdvertBean orderListAdvertBean);
    /**
     * 根据appType valid = 1 查询数据
     */
    public List<OrderListAdvertModel> findAdvertByAppType(Integer appType);
    /**
     * 根据id查询
     */
    public OrderListAdvertModel findOrderListAdvertById(Integer id);

}

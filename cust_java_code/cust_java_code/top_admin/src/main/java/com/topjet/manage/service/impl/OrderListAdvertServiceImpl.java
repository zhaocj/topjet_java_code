package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.OrderListAdvertBean;
import com.topjet.manage.domain.model.OrderListAdvertModel;
import com.topjet.manage.mapper.readMapper.OrderListAdvertModelEMapper;
import com.topjet.manage.service.OrderListAdvertService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/10/23.
 */
@Service
public class OrderListAdvertServiceImpl implements OrderListAdvertService {

    @Autowired
    private OrderListAdvertModelEMapper orderListAdvertModelEMapper;

    @Override
    public List<OrderListAdvertBean> getAdvertList(OrderListAdvertBean orderListAdvertBean) {
        return orderListAdvertModelEMapper.getAdvertList(orderListAdvertBean);
    }

    @Override
    public Integer getAdvertCount(OrderListAdvertBean orderListAdvertBean) {
        return orderListAdvertModelEMapper.getAdvertCount(orderListAdvertBean);
    }

    @Override
    public List<OrderListAdvertModel> findAdvertByAppType(Integer appType) {
        return orderListAdvertModelEMapper.findAdvertByAppType(appType);
    }

    @Override
    public OrderListAdvertModel findOrderListAdvertById(Integer id) {
        return orderListAdvertModelEMapper.findOrderListAdvertById(id);
    }
}

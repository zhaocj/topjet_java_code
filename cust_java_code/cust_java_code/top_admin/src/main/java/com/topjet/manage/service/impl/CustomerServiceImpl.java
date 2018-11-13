package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.CustomerBean;
import com.topjet.manage.mapper.readMapper.CustomerEMapper;
import com.topjet.manage.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


/**
 * Created by tsj010 on 2017/11/29.
 */
@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerEMapper customerEMapper;


    @Override
    public List<CustomerBean> getCustomerPerformance(CustomerBean customerBean) {
        return customerEMapper.getCustomerPerformance(customerBean);
    }
    @Override
    public Integer  getCustomerCount(CustomerBean customerBean){
        return customerEMapper.getCustomerCount(customerBean);
    }
}

package com.topjet.manage.service;

import com.topjet.manage.domain.bean.CustomerBean;

import java.util.List;

public interface CustomerService {

    //客服绩效
    public List<CustomerBean>  getCustomerPerformance(CustomerBean customerBean);
    public Integer  getCustomerCount(CustomerBean customerBean);
}

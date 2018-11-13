package com.topjet.manage.controller;

import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CustomerBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.service.CustomerService;
import com.topjet.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by tsj010 on 2017/11/29.
 */
@Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController {

    private final static Logger log = Logger.getLogger(CustomerController.class);
    @Autowired
    private CustomerService customerService;

    /**
     * 客服绩效列表页面
     * @param customerBean
     * @param
     * @return
     */
    @RequestMapping("list")
    @ResponseBody
    public Object list(CustomerBean customerBean, String start, String end) {
        if(!StringUtils.isBlank(start)){
            customerBean.setBeginDate(DateUtils.StringToDate(start+ " "+ "00:00:00"));
        }
        if(!StringUtils.isBlank(end)){
            customerBean.setEndDate(DateUtils.StringToDate(end+ " "+ "00:00:00"));
        }
        PaginationBean beans = new PaginationBean();
        int total = customerService.getCustomerCount(customerBean);
        beans.setRows(customerService.getCustomerPerformance(customerBean));
        beans.setTotal(total);
        return beans;
    }


}

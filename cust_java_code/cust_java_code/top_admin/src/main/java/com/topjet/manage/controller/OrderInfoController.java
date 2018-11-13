package com.topjet.manage.controller;

import com.topjet.manage.domain.bean.MatchCenterUserBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.service.OrderInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-08-23 15:57
 */

@Controller
@RequestMapping("/orderInfo")
public class OrderInfoController{
    @Autowired
    private OrderInfoService orderInfoService;


    @RequestMapping("userQueryList")
    @ResponseBody
    public Object userQueryList(MatchCenterUserBean mcub) throws Exception {
        List<MatchCenterUserBean> dataList = orderInfoService.userQueryList(mcub);
        int total = orderInfoService.userQueryCount(mcub);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }




}

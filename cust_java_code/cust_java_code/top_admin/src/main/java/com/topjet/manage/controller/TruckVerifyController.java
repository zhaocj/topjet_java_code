package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.TruckVerifyBean;
import com.topjet.manage.domain.vo.TruckListVerifyQuery;
import com.topjet.manage.service.TruckInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-15 16:08
 */

@Controller
@RequestMapping("/truckVerify")
public class TruckVerifyController {

    private static Logger logger = LoggerFactory.getLogger(TruckVerifyController.class);

    @Autowired
    private TruckInfoService truckInfoService;


    /**
     * 获取待审核车辆列表
     * @return
     */

    @RequestMapping("/list")
    @ResponseBody
    public PaginationBean list(TruckListVerifyQuery query){
        return truckInfoService.listTruckListVerify(query);
    }

    /**
     * 获取车辆审核详情信息
     * @param id
     * @return
     */
    @RequestMapping("/edit")
    public ModelAndView edit(Integer id){
        ModelAndView mv = new ModelAndView("view/truckVerify/truckVerifyDetail");
        mv.addObject("data",truckInfoService.getTruckDetil(id));
        return mv;
    }


    /**
     * 车辆认证审核
     */

    @RequestMapping("/updateTruckVerify")
    @ResponseBody
    public Object updateTruckVerify(TruckVerifyBean truckVerifyBean, HttpServletRequest request) throws Exception {
        ResultBaseMsg resultBaseMsg = truckInfoService.updateVerify(truckVerifyBean);
        return resultBaseMsg;
    }



}

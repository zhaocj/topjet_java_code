package com.topjet.manage.controller;

import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.MatchCenterOrderBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OrderInfoModel;
import com.topjet.manage.domain.vo.OrderDetailAdminVO;
import com.topjet.manage.service.CityService;
import com.topjet.manage.service.OrderInfoService;
import com.topjet.manage.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/9/22.
 */
@Controller
@RequestMapping("/goods/")
public class GoodsController extends BaseController{
    @Autowired
    private OrderInfoService orderInfoService;
    @Autowired
    private CityService cityService;
    @Autowired
    private OrderService orderService;
    /*
     * 管理平台货源管理
     * */
    @ResponseBody
    @RequestMapping("getGoodsList")
    public Object getGoodsList(MatchCenterOrderBean matchCenterOrderBean)throws Exception {
        matchCenterOrderBean = setOrderCondition(matchCenterOrderBean);
        List<MatchCenterOrderBean> dataList = orderInfoService.orderQuertList(matchCenterOrderBean);
        for(MatchCenterOrderBean mb : dataList){
            mb.setResidentCity(cityService.getFullName(mb.getResidentCityId()));
        }
        int total = orderInfoService.orderQuertCount(matchCenterOrderBean);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    //货源查询设值
    public MatchCenterOrderBean setOrderCondition(MatchCenterOrderBean mcdb) {
        if (!StringUtils.isBlank(mcdb.getDepart1())) {
            mcdb.setDepart1(cityService.getCityModel(mcdb.getDepart1()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDepart2())) {
            mcdb.setDepart2(cityService.getCityModel(mcdb.getDepart2()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDepart3())) {
            mcdb.setDepart3(cityService.getCityModel(mcdb.getDepart3()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination1())) {
            mcdb.setDestination1(cityService.getCityModel(mcdb.getDestination1()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination2())) {
            mcdb.setDestination2(cityService.getCityModel(mcdb.getDestination2()).getCityFullName());
        }
        if (!StringUtils.isBlank(mcdb.getDestination3())) {
            mcdb.setDestination3(cityService.getCityModel(mcdb.getDestination3()).getCityFullName());
        }
        return mcdb;
    }

    @RequestMapping("goodsdetail")
    public Object goodsdetail(Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("view/common/goodsDetail");
        if(request.getParameter("serialNo") != null && !request.getParameter("serialNo").equals("")){
            String orderNo = new String(request.getParameter("serialNo").getBytes("ISO-8859-1"), "UTF-8");
            //根据货源单号查询
            OrderInfoModel orderList = orderService.findByOrderNo(orderNo);

            if(orderList != null){
                id = orderList.getId();
            }else{
                mv.addObject("title", "你搜索的订单 "+orderNo+"不存在");
                mv.setViewName("common/404");
                return mv;
            }
        }

        OrderDetailAdminVO result=orderService.goodsDetail(id);
        if (result != null){
            mv.addObject("data",result);
        }
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        mv.addObject("title",title);
        return mv;
    }

    @ResponseBody
    @RequestMapping("isHidden")
    public Object isHidden(Integer goodsId,Integer version,Integer isHidden){
        return orderService.isHidden(goodsId,version,isHidden);
    }

}

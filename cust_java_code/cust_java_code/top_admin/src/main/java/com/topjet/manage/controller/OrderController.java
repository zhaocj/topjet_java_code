package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.OrderAuditHistoryModel;
import com.topjet.manage.domain.vo.OrderDetailAdminVO;
import com.topjet.manage.domain.vo.OrderInfoBean;
import com.topjet.manage.domain.vo.OrderListQuery;
import com.topjet.manage.domain.vo.OrderListVO;
import com.topjet.manage.service.OrderService;
import com.topjet.util.DateUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyj on 2017/8/26.
 */
@Controller
@RequestMapping("/order/")
public class OrderController extends BaseController{

    @Autowired
    private OrderService orderService;

    ResultBaseMsg msg = new ResultBaseMsg();

    @ResponseBody
    @RequestMapping("list")
    public Object list(OrderListQuery query, String start, String end) {
        if(!StringUtils.isBlank(query.getDepartCityId())){
            query.setEndDepartCityId(getEndCityid(query.getDepartCityId()));
        }
        if(!StringUtils.isBlank(query.getDestinationCityId())){
            query.setEndDestinationCityId(getEndCityid(query.getDestinationCityId()));
        }
        if(!StringUtils.isBlank(start)){
            query.setStartDate(DateUtils.StringToDate(start));
        }
        if(!StringUtils.isBlank(end)){
            query.setEndDate(DateUtils.StringToDate(end));
        }
        PaginationBean bean = new PaginationBean();
        List<OrderListVO> dataList = orderService.getOrderList(query);
        int total = orderService.getOrderListCount(query);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

       @RequestMapping("detail")
    public Object detail(Integer id, HttpServletRequest request) throws Exception {
        ModelAndView mv = new ModelAndView("view/common/orderDetail");
        OrderDetailAdminVO result=orderService.orderDetail(id);
        if (result != null){
            mv.addObject("data",result);
        }
        String title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        mv.addObject("title",title);
        return mv;
    }

    /**
     * 根据前端传过来的城市id获取最大返回值
     * @param cityStart
     * @return
     */
    protected static String getEndCityid(String cityStart){
        if(cityStart.indexOf("0000")!=-1){
            return cityStart.substring(0,cityStart.length()-4)+"9999";
        }else if(cityStart.indexOf("00")!=-1){
            return cityStart.substring(0,cityStart.length()-2)+"99";
        }
        return cityStart;
    }

    @ResponseBody
    @RequestMapping("isHidden")
    public Object isHidden(Integer goodsId,Integer version,Integer isHidden){
        return orderService.isHidden(goodsId,version,isHidden);
    }

    @ResponseBody
    @RequestMapping("getOperationLog")
    public Object getOperationLog(OrderAuditHistoryModel orderAuditHistoryModel){
        List<OrderAuditHistoryModel> dataList = orderService.getOperationLog(orderAuditHistoryModel);
        return dataList;
    }

    @ResponseBody
    @RequestMapping("addOrUpdateOrder")
    public ResultBaseMsg addOrUpdateOrder(OrderInfoBean orderInfoBean){
        return  orderService.addOrUpdateOrder(orderInfoBean);
    }


    @ResponseBody
    @RequestMapping("getRunninnTrack")
    public Object getRunninnTrack(OrderInfoBean orderInfoBean){
        ArrayList<Object> mapList = new ArrayList<>();
        mapList.add(orderService.getOrderTrack(orderInfoBean.getId()));//提货 签收两个点
        mapList.add(orderService.getRunninnTrack(orderInfoBean));//司机在提货签收两个时间段中的运行轨迹
        return mapList;
    }
}

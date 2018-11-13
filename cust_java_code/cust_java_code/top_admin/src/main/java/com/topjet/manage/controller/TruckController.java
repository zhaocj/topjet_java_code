package com.topjet.manage.controller;

import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.vo.TruckListQuery;
import com.topjet.manage.domain.vo.TruckListVO;
import com.topjet.manage.domain.model.TruckInfoModel;
import com.topjet.manage.service.TruckService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by liyj on 2017/9/11.
 */
@Controller
@RequestMapping("/truck/")
public class TruckController {

    @Autowired
    private TruckService truckService;

    @ResponseBody
    @RequestMapping("list")
    public Object list(TruckListQuery truckListQuery,String start,String end,String plateNo1,String plateNo2,String plateNo3,String plateNo){
        if(!StringUtils.isBlank(start)){
            truckListQuery.setStartDate(DateUtil.StringToDate(start));
        }
        if(!StringUtils.isBlank(end)){
            truckListQuery.setEndDate(DateUtil.StringToDate(end));
        }
        if(!StringUtils.isBlank(plateNo)){
            plateNo1 = plateNo.substring(0,1);
            plateNo2 = plateNo.substring(1,2);
            plateNo3 = plateNo.substring(2);
            if(!StringUtils.isBlank(plateNo1)){
                truckListQuery.setPlateNo1(plateNo1);
            }
            if(!StringUtils.isBlank(plateNo2)){
                truckListQuery.setPlateNo2(plateNo2);
            }
            if(!StringUtils.isBlank(plateNo3)){
                truckListQuery.setPlateNo3(plateNo3);
            }
        }
        List<TruckListVO> truckList = truckService.getTruckList(truckListQuery);
        int total = truckService.getTruckCount(truckListQuery);
        PaginationBean bean = new PaginationBean();
        bean.setRows(truckList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("edit")
    public ModelAndView edit(Integer id, HttpServletRequest request) throws Exception {//mobile不为空表示为全局搜索
        ModelAndView view = new ModelAndView("view/truckManage/truckDetail");
        if(request.getParameter("plateNo") != null && !request.getParameter("plateNo").equals("")){
            String plateNo = new String(request.getParameter("plateNo").getBytes("ISO-8859-1"), "UTF-8");
            TruckInfoModel truckInfoModel = new TruckInfoModel();
            List<TruckInfoModel>  truckList = truckService.getTruckInfoByPlatNo(truckInfoModel);
            if(!truckList.isEmpty()){
                id = truckList.get(0).getId();
            }else{
                view.addObject("title", "你搜索的车辆 "+plateNo+"不存在");
                view.setViewName("common/404.ftl");
                return view;
            }
        }
        //TruckEditResponseBean responseDTO= (TruckEditResponseBean) service.edit(id);
        view.addObject("data", truckService.edit(id));
        String title = "";
        if(request.getParameter("title") != null && !request.getParameter("title").equals("")){
            title = new String(request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8");
        }
        view.addObject("title", title);
        return view;
    }
    @ResponseBody
    @RequestMapping("update")
    public Object update(Integer id, String plateNo1,String plateNo2,String plateNo3, Integer truckColor, Integer truckType,Integer truckLength,String operatorRemark){
        return truckService.updateTruckInfo(id,plateNo1,plateNo2,plateNo3,truckColor,truckType,truckLength,operatorRemark);
    }
}

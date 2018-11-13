package com.topjet.manage.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.BrokerInfoModel;
import com.topjet.manage.domain.model.BrokerRouteInfoModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.service.StationBrokerService;
import com.topjet.manage.service.UserInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:货运经纪人Controller
 * @Date: 2017-11-06 13:34
 */

@Controller
@RequestMapping("/stationBroker")
public class StationBrokerController {

    private final static Logger log = Logger.getLogger(StationBrokerController.class);

    @Autowired
    private StationBrokerService stationBrokerService;

    @Autowired
    private UserInfoService userInfoService;


    /**
     * 查询经纪人
     * @param request
     * @return
     */
    @RequestMapping("/list")
    @ResponseBody
    public Object list(HttpServletRequest request, BrokerInfoModel brokerInfoModelInfo) {
        PageHelper.startPage(brokerInfoModelInfo.getPage(), brokerInfoModelInfo.getRows());
        List<Map<String,Object>> dataList = stationBrokerService.listBroker(brokerInfoModelInfo);
        PageInfo<Map<String,Object>> pageInfo=new PageInfo<Map<String,Object>>(dataList);
        PaginationBean beans = new PaginationBean();
        beans.setRows(dataList);
        beans.setTotal((int)pageInfo.getTotal());
        return beans;
    }

    /**
     * 添加、编辑 /路线列表
     * @param request
     * @return
     */
    @RequestMapping("/brokerInfo")
    @ResponseBody
    public Map<String,Object> getbrokerInfo(BrokerInfoModel brokerInfo,HttpServletRequest request){
        BrokerInfoModel binfo  = stationBrokerService.getBrokerInfo(brokerInfo).get(0);
        BrokerRouteInfoModel brokerRouteInfo = new BrokerRouteInfoModel();
        brokerRouteInfo.setBrokerId(binfo.getId());
        List<BrokerRouteInfoModel> routeList = stationBrokerService.listBrokerRouteInfo(brokerRouteInfo);
        Map<String,Object> brokerMap = new HashMap<String,Object>();
        brokerMap.put("broker",binfo);
        brokerMap.put("route",routeList);
        return brokerMap;
    }

    @RequestMapping("/deleteRoute")
    @ResponseBody
    public String deleteRoute(BrokerRouteInfoModel brokerRouteInfo,HttpServletRequest request){
        stationBrokerService.deleteBrokerRouteInfo(brokerRouteInfo);
        return "1";
    }

    /**
     * 更新经纪人状态
     * @param brokenInfo
     * @param request
     * @return
     */
    @RequestMapping("/changeStatus")
    @ResponseBody
    public String changeStatus(BrokerInfoModel brokenInfo,HttpServletRequest request){
        stationBrokerService.updateBroker(brokenInfo);
        return "1";
    }

    @RequestMapping("/deleteBroker")
    @ResponseBody
    public String deleteBroker(HttpServletRequest request,BrokerInfoModel brokenInfo){
        stationBrokerService.deleteBroker(brokenInfo);
        return "1";
    }

    /**
     * 保存
     * @return
     */
    @RequestMapping("/add")
    @ResponseBody
    public ResultBaseMsg add(HttpServletRequest request, BrokerInfoModel brokerInfo,Integer flag){
        ResultBaseMsg msg=new ResultBaseMsg();
        if(brokerInfo.getId()==0 && flag.equals(1)){
            //新增  判断经纪人是否存在
            List<BrokerInfoModel> list  = stationBrokerService.getBrokerInfo(brokerInfo);
            if(list.size()>0){
                msg.setMsg("该经纪人已存在");
                msg.setStatus("1");
                return msg;
            }
            //brokerInfo.setCreateBy(SessionUtils.getSysUserSession().getId());
            //新增
            stationBrokerService.insertBroker(brokerInfo);
        }else{
            //根据id 修改userID
            stationBrokerService.updateBroker(brokerInfo);
        }


        String[] route = request.getParameter("routeInfo").split(",");
        BrokerRouteInfoModel brokerRouteInfo;
        for(String str : route){
            String[] arr = str.split("_");
            int id = Integer.parseInt(arr[0]);
            brokerRouteInfo = new BrokerRouteInfoModel();
            brokerRouteInfo.setBeginCity(Integer.parseInt(arr[1]));
            brokerRouteInfo.setEndCity(Integer.parseInt(arr[2]));
            if(id==0){
                //添加路线
                brokerRouteInfo.setBrokerId(brokerInfo.getId());
                stationBrokerService.insertBrokerRouteInfo(brokerRouteInfo);
            }else{
                //更新路线
                brokerRouteInfo.setId(id);
                stationBrokerService.updateBrokerRouteInfo(brokerRouteInfo);
            }
        }
        msg.setStatus("0");
        return msg;
    }


    /**
     * 获取用户信息
     * @param request
     * @return
     */
    @RequestMapping("/userBymobile")
    @ResponseBody
    public UserInfoModel userBymobile(HttpServletRequest request) {
        String mobile = request.getParameter("mobile");
        UserInfoModel userInfoModel = userInfoService.selectUserByMobile(mobile);
        return userInfoModel;
    }


}

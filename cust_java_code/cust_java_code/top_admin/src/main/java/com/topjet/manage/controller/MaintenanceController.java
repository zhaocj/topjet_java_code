package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.service.MaintenanceService;
import com.topjet.system.domain.model.AssertSettingInfoModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/8/22.
 */
@Controller
@RequestMapping("/maintenance/")
public class MaintenanceController extends BaseController {

    @Autowired
    private MaintenanceService maintenanceService;

    /**
     * 查询所有
     * @return
     */
    @RequestMapping("queryAllAssertSettingInfoModel")
    @ResponseBody
    public Object queryAllAssertSettingInfoModel(){
        List<AssertSettingInfoModel> assertList = maintenanceService.getAllAssertList();
        int total = maintenanceService.getAssertCount();
        PaginationBean bean = new PaginationBean();
        bean.setRows(assertList);
        bean.setTotal(total);
        return bean;
    }
    @ResponseBody
    @RequestMapping("addMaintenance")
    public Object addMaintenance(AssertSettingInfoModel assertSettingInfoModel)throws TopjetErrorCodeException {
        //插入操作
        maintenanceService.addMaintenance(assertSettingInfoModel);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }
    /**
     * 通过ID查询详情
     * @param id
     * @return
     */
    @RequestMapping("queryAssertSettingInfoModelById")
    public ModelAndView queryAssertSettingInfoModelById(Integer id){
        AssertSettingInfoModel assertSettingInfoModel = maintenanceService.findAssertById(id);
        ModelAndView mv = new ModelAndView("view/maintenance/maintenanceInfo");
        mv.addObject("model", assertSettingInfoModel);
        return mv;
    }
    /**
     * 修改
     */
    @ResponseBody
    @RequestMapping("updeteMaintenance")
    public ResultBaseMsg updeteMaintenance(AssertSettingInfoModel assertSettingInfoModel)throws TopjetErrorCodeException {
        maintenanceService.updateMaintence(assertSettingInfoModel);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }
    /**
     * 删除 修改公告状态
     */
    @ResponseBody
    @RequestMapping("updeteDeletMaintenance")
    public ResultBaseMsg updeteDeletMaintenance(Integer id)throws TopjetErrorCodeException {
        maintenanceService.deleteMaintenance(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }
}

package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.service.SysAppUpGradeService;
import com.topjet.system.domain.model.AppUpgradeHistoryModel;
import com.topjet.system.domain.model.AppUpgradeInfoModel;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * Created by liyj on 2017/8/8.
 */
@Controller
@RequestMapping("/appUpgrade/")
public class SysAppUpGradeController extends BaseController {

    @Autowired
    private SysAppUpGradeService sysAppUpGradeService;

    private final static Logger log = Logger.getLogger(SysAppUpGradeController.class);
    ResultBaseMsg resultBaseMsg = new ResultBaseMsg();



    @ResponseBody
    @RequestMapping("list")
    public Object list(AppUpgradeInfoModel appUpgradeInfoModel)throws Exception {
        log.error("----------------------------------------");
        List<AppUpgradeInfoModel> appList = sysAppUpGradeService.getAppUpGradeList(appUpgradeInfoModel);
        int total = sysAppUpGradeService.getCount(appUpgradeInfoModel);
        PaginationBean bean = new PaginationBean();
        bean.setRows(appList);
        bean.setTotal(total);
        return bean;
    }

    @RequestMapping("update")
    public ModelAndView update(Integer id)throws Exception {
        ModelAndView mv = new ModelAndView();
        AppUpgradeInfoModel appUpgradeInfoModel = sysAppUpGradeService.findAppById(id);
        mv.setViewName("view/sysAdmin/sysAppUpgrade/editUpgrade");
        mv.addObject("appUpgradeInfoModel",appUpgradeInfoModel);
        return mv;
    }

    @ResponseBody
    @RequestMapping("saveOrUpdate")
    public Object saveOrUpdate(AppUpgradeInfoModel appUpgradeInfoModel) throws TopjetExceptionHandler {
        sysAppUpGradeService.saveOrUpdate(appUpgradeInfoModel);
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

    @ResponseBody
    @RequestMapping("queryByType")
    public Object queryByType(String type){
        if(type != null){
            List<AppUpgradeInfoModel> appUpgradeInfoModel = sysAppUpGradeService.findAppByType(type);
            if(appUpgradeInfoModel != null && appUpgradeInfoModel.size()>0){
                AppUpgradeInfoModel app = appUpgradeInfoModel.get(0);
                return app;
            }
            else{
                return null;
            }
        }
        else{
            return null;
        }
    }

    @ResponseBody
    @RequestMapping("viesHistoryDataList")
    public Object viesHistoryDataList(String type){
        AppUpgradeHistoryModel appUpgradeHistoryModel = new AppUpgradeHistoryModel();
        appUpgradeHistoryModel.setType(type);
        List<AppUpgradeHistoryModel> dataList = sysAppUpGradeService.getAppHistoryList(appUpgradeHistoryModel);
        Integer total = sysAppUpGradeService.getHistoryCount(appUpgradeHistoryModel);
        PaginationBean bean = new PaginationBean();
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }
    @ResponseBody
    @RequestMapping("sendMsg")
    public Object sendMsg(AppUpgradeInfoModel appUpgradeInfoModel)throws Exception {
        sysAppUpGradeService.send(appUpgradeInfoModel);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return  msg;
    }
}

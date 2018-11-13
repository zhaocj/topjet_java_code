package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CollocationInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.CollocationInfoModel;
import com.topjet.manage.domain.model.ResourceFileVersionInfoModel;
import com.topjet.manage.service.CollocationInfoService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * Created by liyj on 2017/9/21.
 */
@Controller
@RequestMapping("/collocationInfo/")
public class CollocationInfoController extends BaseController{
    private final static Logger log = Logger.getLogger(CollocationInfoController.class);

    @Autowired
    private CollocationInfoService collocationInfoService;


    @RequestMapping("list")
    @ResponseBody
    public Object list(CollocationInfoBean collocationInfo, HttpServletResponse response) {
        PaginationBean pagination = new PaginationBean();
        List<CollocationInfoBean> dataList = collocationInfoService.getCollocationList(collocationInfo);
        int total = collocationInfoService.getCollocationCount(collocationInfo);
        pagination.setRows(dataList);
        pagination.setTotal(total);
        return pagination;
    }

    @RequestMapping("create")
    @ResponseBody
    public ResultBaseMsg create(CollocationInfoBean collocationInfo, HttpServletResponse response) {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        int i = collocationInfoService.getCollocationCount(collocationInfo);
        if(collocationInfo.getCollocationType()!=4 || i<10){
            //新增操作
            Integer collocationInfo1 = collocationInfoService.createCollocationInfo(collocationInfo);
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_35.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_35.getMsg());
        }
        return resultBaseMsg;
    }
    @RequestMapping("edit")
    public ModelAndView edit(CollocationInfoBean collocationInfo, HttpServletResponse response) {
        List<CollocationInfoBean> dataList = collocationInfoService.getCollocationList(collocationInfo);
        ModelAndView mv = new ModelAndView();
        mv.addObject("collocationInfo",dataList.get(0));
        mv.setViewName("view/collocationInfo/collocationInfoEdit");
        return mv;
    }
    @RequestMapping("update")
    @ResponseBody
    public ResultBaseMsg update(CollocationInfoBean collocationInfo, HttpServletResponse response) {
        collocationInfoService.updateCollocationInfo(collocationInfo);
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }
    @RequestMapping("delete")
    @ResponseBody
    public ResultBaseMsg delete(CollocationInfoBean collocationInfo, HttpServletResponse response) {
        List<CollocationInfoBean> dataList = collocationInfoService.getCollocationList(collocationInfo);
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        CollocationInfoBean cib = new CollocationInfoBean();
        cib.setCollocationType(dataList.get(0).getCollocationType());
        int i = collocationInfoService.getCollocationCount(cib);
        if(dataList.get(0).getCollocationType()!=4 || i>4 ){
            collocationInfoService.deleteCollocationInfo(collocationInfo);
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_35.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_35.getMsg());
        }
        return resultBaseMsg;
    }
    /**
     * 修改后更新resourceFileVersionInfo表
     * @param rfvModel
     * @param response
     * @return
     */
    @RequestMapping("updateResource")
    @ResponseBody
    public ResultBaseMsg updateResource(ResourceFileVersionInfoModel rfvModel, HttpServletResponse response) {
        try{
            collocationInfoService.updateResource(rfvModel);
        }catch (Exception e){
            e.printStackTrace();
        }
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

    /**
     * 匹配中心电话配置页面
     * @param request
     * @return
     */
    @RequestMapping("getMatchCenterPhone")
    public Object callSurveyDurationList(HttpServletRequest request) {
        ModelAndView mv = new ModelAndView();
        CollocationInfoModel dataList = collocationInfoService.getMatchCenterPhone();
        mv.setViewName("view/matchCenter/PhoneConfiguration");
        mv.addObject("dataList", dataList);
        return mv;
    }
    /**
     * 匹配中心电话编辑提交
     * @param mobile
     * @return
     */
    @RequestMapping("updateOrAdd")
    @ResponseBody
    public ResultBaseMsg updateOrAdd(String mobile) {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg =  collocationInfoService.updateOrAdd(mobile);
        return resultBaseMsg;
    }
}

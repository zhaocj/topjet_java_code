package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.AppSlideModel;
import com.topjet.manage.service.AppSlideService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhaocj
 * @Description:轮播图Controller
 * @Date: 2017-12-04 15:29
 */

@Controller
@RequestMapping("/appSlide")
public class AppSlideController {

    private static Logger log = LoggerFactory.getLogger(AppSlideController.class);

    @Autowired
    private AppSlideService appSlideService;





    @RequestMapping("/listAppSlide")
    @ResponseBody
    public PaginationBean listAppSlide(AppSlideModel appSlideModel){
        return  appSlideService.listAppSlide(appSlideModel);
    }

    @RequestMapping("/addAppSlide")
    @ResponseBody
    public ResultBaseMsg addAppSlide(AppSlideModel appSlideModel){
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();

        Integer result = appSlideService.saveAppSlide(appSlideModel);
        if(result>0){
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_3.getMsg());
        }
        return resultBaseMsg;

    }

    /**
     * 获取轮播图详情
     * @param id
     * @return
     * @throws Exception
     */
    @RequestMapping("/appSlideDetail")
    public ModelAndView getAppSlideDetail(Integer id) throws Exception{
        ModelAndView mv = new ModelAndView("view/appslide/appSlideDetail");
        mv.addObject("aim",appSlideService.getAppSlideModelById(id));
        return mv;
    }


    @RequestMapping("/updateAppSlide")
    @ResponseBody
    public ResultBaseMsg updateAppSlide(AppSlideModel appSlideModel){
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        if(appSlideModel.getId() != null){
            appSlideService.updateAppSlide(appSlideModel);
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_3.getMsg());
        }
        return resultBaseMsg;

    }

    @RequestMapping("/removeAppSlide")
    @ResponseBody
    public ResultBaseMsg removeAppSlide(Integer id) throws Exception {

        ResultBaseMsg msg = new ResultBaseMsg();

        AppSlideModel appSlideModel = new AppSlideModel();
        appSlideModel.setIsValid(0);
        appSlideModel.setId(id);
        appSlideModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        appSlideModel.setUpdateTime(DateUtil.now());
        appSlideService.updateAppSlide(appSlideModel);

        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());

        return msg;

    }








}

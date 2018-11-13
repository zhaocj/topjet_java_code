package com.topjet.manage.controller;

import com.topjet.basic.UserFeignService;
import com.topjet.cloud.manage.custservice.user.bean.UpdateFeedbackInfoRTS;
import com.topjet.cloud.manage.custservice.user.bean.UpdateFeedbackInfoVRU;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.FeedbackInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.FeedbackInfoModel;
import com.topjet.manage.service.FeedBackInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-21 17:42
 */

@Controller
@RequestMapping("/feedBack")
public class FeedBackInfoController extends BaseController{

    @Autowired
    private FeedBackInfoService feedBackInfoService;

    @Autowired
    private UserFeignService userFeignService;

    /**
     * 加载意见反馈列表
     * @param feedbackInfoBean
     * @return
     */
    @RequestMapping("/listFeedBackInfo")
    @ResponseBody
    public PaginationBean listFeedBackInfo(FeedbackInfoBean feedbackInfoBean) throws Exception{
        return feedBackInfoService.listFeedBackInfo(feedbackInfoBean);
    }

    @RequestMapping("/dealWithFeedBackInfo")
    @ResponseBody
    public Object dealWithFeedBackInfo(FeedbackInfoModel feedbackInfoModel){
        UpdateFeedbackInfoRTS rts = new UpdateFeedbackInfoRTS();
        rts.setFeedbackInfoId(feedbackInfoModel.getId());
        rts.setRemark(feedbackInfoModel.getRemark());
        rts.setUserId(SessionUtils.getSysUserSession().getId());
        UpdateFeedbackInfoVRU updateFeedbackInfoVRU = userFeignService.updateFeedbackInfo(rts);
        if(updateFeedbackInfoVRU != null && updateFeedbackInfoVRU.getCode() ==1){
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            msg.setStatus(ExceptionEnum.E_37.getStatus());
            msg.setMsg(ExceptionEnum.E_37.getMsg());
        }

        return msg;

    }

}

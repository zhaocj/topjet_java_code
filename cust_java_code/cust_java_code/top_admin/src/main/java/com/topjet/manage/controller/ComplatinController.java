package com.topjet.manage.controller;

import com.topjet.basic.OrderFeignService;
import com.topjet.basic.UserFeignService;
import com.topjet.cloud.manage.custservice.user.bean.UpdateComplaintInfoRTS;
import com.topjet.cloud.manage.custservice.user.bean.UpdateComplaintInfoVRU;
import com.topjet.common.ExceptionEnum;
import com.topjet.common.SessionUtils;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.ComplaintInfoBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.ComplaintInfoModel;
import com.topjet.manage.service.ComplaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-21 15:08
 */

@Controller
@RequestMapping("/complaint")
public class ComplatinController extends BaseController{

    @Autowired
    private ComplaintService complaintService;

    @Autowired
    private UserFeignService userFeignService;


    /**
     * 加载用户投诉列表
     * @param complaintInfoModel
     * @return
     */
    @RequestMapping("/listComplaint")
    @ResponseBody
    public PaginationBean listComplaint(ComplaintInfoModel complaintInfoModel){
        return complaintService.listComplaint(complaintInfoModel);
    }


    /**
     * 获取用户投诉详情
     * @param id
     * @return
     */
    @RequestMapping("/getComplaintDetail")
    public ModelAndView getComplaintDetail(Integer id ){
        ModelAndView mv = new ModelAndView("view/complaint/detailsComplaint");
        ComplaintInfoBean cb = complaintService.getComplaintDetailById(id);
        mv.addObject("complaintDetails",cb);
        String statusName = "";
        if(cb != null){
            if(cb.getStatus() == 1){
                statusName = "待处理";
            }else if(cb.getStatus() == 2){
                statusName = "投诉属实";
            }else if(cb.getStatus() == 3){
                statusName = "投诉和解";
            }else if(cb.getStatus() == 4){
                statusName = "虚假投诉";
            }
        }
        mv.addObject("statusName",statusName);
        return mv;
    }


    @RequestMapping("/dealWithComplaint")
    @ResponseBody
    public Object dealWithComplaint(ComplaintInfoModel complaintInfoModel)throws Exception{
        ComplaintInfoModel model = complaintService.getComplaintById(complaintInfoModel.getId());
        UpdateComplaintInfoRTS rts = new UpdateComplaintInfoRTS();
        rts.setComplaintInfoId(complaintInfoModel.getId());
        rts.setRemark(complaintInfoModel.getRemark());
        rts.setStatus(complaintInfoModel.getStatus());
        rts.setVersion(model.getVersion());
        rts.setUserId(SessionUtils.getSysUserSession().getId());
        UpdateComplaintInfoVRU updateComplaintInfoVRU = null;
        try{
            updateComplaintInfoVRU = userFeignService.updateComplaintInfo(rts);
        }catch (Exception e){
            e.printStackTrace();
        }

        if(updateComplaintInfoVRU != null && updateComplaintInfoVRU.getCode() == 1){
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            msg.setStatus(ExceptionEnum.E_37.getStatus());
            msg.setMsg(ExceptionEnum.E_37.getMsg());
        }
        return msg;
    }


}

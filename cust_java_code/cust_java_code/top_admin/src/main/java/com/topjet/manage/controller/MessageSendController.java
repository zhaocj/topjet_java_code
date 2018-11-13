package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.MessageSendModel;
import com.topjet.manage.service.MessageSendService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-15 9:45
 */

@Controller
@RequestMapping("/messageSend")
public class MessageSendController extends BaseController{

    private static Logger log = LoggerFactory.getLogger(MessageSendController.class);

    @Autowired
    private MessageSendService messageSendService;

    /**
     * 获取推送消息列表
     * @param messageSendModel
     * @param request
     * @return
     */
    @RequestMapping("/listMessageSend")
    @ResponseBody
    public PaginationBean listMessageSend(MessageSendModel messageSendModel, HttpServletRequest request){
        PaginationBean page = new PaginationBean();
        page.setRows(messageSendService.listMessageSend(messageSendModel));
        page.setTotal(messageSendService.countMessageSend(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE));

        return page;

    }

    /**
     * 新增消息推送接口
     * @param messageSendModel
     * @param request
     * @return
     */
    @RequestMapping("/create")
    @ResponseBody
    public Object create(MessageSendModel messageSendModel,HttpServletRequest request) throws Exception{
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        if(messageSendModel != null){
            messageSendService.insertMessageSend(messageSendModel);
        }
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        return resultBaseMsg;
    }

    /**
     * 获取消息推送详情
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("/edit")
    public ModelAndView edit(HttpServletRequest request) throws Exception{
        ModelAndView mv  = new ModelAndView("/view/messageSend/messageSendEdit");
        MessageSendModel messageSendModel = messageSendService.getMessageSendDetail(Integer.valueOf(request.getParameter("id")));
        if(messageSendModel != null){
            mv.addObject("messageSendModel",messageSendModel);
            return mv;
        }else{
            throw new TopjetExceptionHandler(ExceptionEnum.E_8.getStatus(),ExceptionEnum.E_8.getMsg());
        }
    }

    /**
     * 删除消息推送记录
     * @param request
     * @param id
     * @return
     */
    @RequestMapping("/delete")
    @ResponseBody
    public Object delete(HttpServletRequest request,Integer id){
        ResultBaseMsg msg = new ResultBaseMsg();
        if(id == null) return msg;
        messageSendService.deleteMessageSend(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @RequestMapping("/update")
    @ResponseBody
    public Object update(HttpServletRequest request, MessageSendModel messageSendModel){
        ResultBaseMsg baseMsg = new ResultBaseMsg();

        Integer id = Integer.valueOf(request.getParameter("id"));
        Integer version = Integer.valueOf(request.getParameter("version"));
        MessageSendModel messageSendModel1 = messageSendService.selectMessageSend(id, version);
        if(messageSendModel1 != null){
            messageSendModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
            messageSendModel.setUpdateTime(DateUtil.now());
            messageSendModel.setVersion(version+1);
            messageSendService.update(messageSendModel);
            baseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            baseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        }else{
            baseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            baseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return baseMsg;
    }

    @RequestMapping("/send")
    @ResponseBody
    public Object send(MessageSendModel model){
        //获取需要发消息推送的用户列表(如果推送范围是所有用户，IOS、android货主，IOS货主，android货主，IOS、android司机，IOS司机，Android司机，则调用接口发送；
        messageSendService.sendMessage(model);
        model.setStatus(1);
        messageSendService.update(model);
        return msg;
    }


}

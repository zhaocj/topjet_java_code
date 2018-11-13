package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.bean.WhiteUserBean;
import com.topjet.manage.domain.model.WhiteUserInfoModel;
import com.topjet.manage.service.WhiteUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by tsj010 on 2018/4/25.
 */
@Controller
@RequestMapping("whiteUserAction")
public class WhiteUserController  extends BaseController{

    @Autowired
    private WhiteUserService whiteUserService ;


    @ResponseBody
    @RequestMapping("whiteUserList")
    public Object getwhiteUserList(){
        List<WhiteUserBean> whiteUserList = whiteUserService.getWhiteUserList();
        Integer total = whiteUserService.getWhiteUserListCount();
        PaginationBean bean = new PaginationBean();
        bean.setRows(whiteUserList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("insertWhiteUser")
    public ResultBaseMsg insertSensitiveWord(WhiteUserInfoModel whiteUserInfoModel){
        ResultBaseMsg msg = new ResultBaseMsg();
        WhiteUserInfoModel wui  = whiteUserService.getWhiteUserInfo(whiteUserInfoModel);
         if(wui ==null ){
             whiteUserInfoModel.setType(1);
             whiteUserInfoModel.setCreateBy(SessionUtils.getSysUserSession().getId());
             whiteUserInfoModel.setCreateTime(DateUtil.now());
             whiteUserService.insert(whiteUserInfoModel);
             msg.setStatus(ExceptionEnum.E_0.getStatus());
             msg.setMsg(ExceptionEnum.E_0.getMsg());
         }else{
             msg.setStatus(ExceptionEnum.E_1.getStatus());
             msg.setMsg(ExceptionEnum.E_1.getMsg());
         }
        return msg;
    }




    @ResponseBody
    @RequestMapping("deleted")
    public Object deleted(Integer id){
        whiteUserService.update(id);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }



    @ResponseBody
    @RequestMapping("checkMobile")
    public UserInfoBean checkMobile(UserInfoBean   us){
        UserInfoBean  userInfoBean = whiteUserService.getUserInfo(us.getMobile());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return   userInfoBean;
    }


}

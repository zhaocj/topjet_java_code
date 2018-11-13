package com.topjet.manage.controller;

import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.SensitiveWordInfoBean;
import com.topjet.manage.domain.model.SensitiveWordInfoModel;
import com.topjet.manage.domain.model.SysUserModel;
import com.topjet.manage.service.SensitiveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liyj on 2017/11/29.
*/
@Controller
@RequestMapping("sensitiveWordAction")
public class SensitiveWordController extends BaseController{

    @Autowired
    private SensitiveService sensitiveWordService;

    @ResponseBody
    @RequestMapping("getSensitiveWordList")
    public Object getSensitiveWordList(SensitiveWordInfoBean sensitiveWordInfoBean){
        List<SensitiveWordInfoBean> sensitiveList = sensitiveWordService.getSensitiveWordList(sensitiveWordInfoBean);
        Integer total = sensitiveWordService.getSensitiveWordCount(sensitiveWordInfoBean);
        PaginationBean bean = new PaginationBean();
        bean.setRows(sensitiveList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("insertSensitiveWord")
    public Object insertSensitiveWord(Integer id,String name,SensitiveWordInfoBean sensitiveBean){
        //根据名字查数据
        sensitiveBean = new SensitiveWordInfoBean();
        sensitiveBean.setName(name);
        List<SensitiveWordInfoBean> sensiveList = sensitiveWordService.getSensitiveWordList(sensitiveBean);
        SysUserModel sysUserModel = SessionUtils.getSysUserSession();
        if(sensiveList.isEmpty()){
            if(id != null){
                SensitiveWordInfoBean ssm = sensitiveWordService.getSensitiveWordById(id);
                ssm.setName(name);
                ssm.setUpdateBy(sysUserModel.getId());
                ssm.setUpdateTime(DateUtil.now());
                sensitiveWordService.updateSensiticeWord(ssm);
            }
            else{
                name = name.replaceAll("，",",");
                String[] wordArr = name.split(",");
                for(String word : wordArr) {
                    word = word.trim();
                    if("".equals(word))continue;
                    SensitiveWordInfoBean sensitiveWordInfoModel = new SensitiveWordInfoBean();
                    sensitiveWordInfoModel.setName(word);
                    sensitiveWordInfoModel.setCount(0);
                    sensitiveWordInfoModel.setCreateTime(DateUtil.now());
                    sensitiveWordInfoModel.setCreateBy(sysUserModel.getId());
                    sensitiveWordInfoModel.setUpdateBy(sysUserModel.getId());
                    sensitiveWordInfoModel.setUpdateTime(DateUtil.now());
                    sensitiveWordInfoModel.setDeleted(0);
                    sensitiveWordInfoModel.setVersion(1);
                    sensitiveWordService.addSensitiveWord(sensitiveWordInfoModel);
                }
            }
        }
        else{
            msg.setStatus(ExceptionEnum.E_39.getStatus());
            msg.setMsg(ExceptionEnum.E_39.getMsg());
            return msg;
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @ResponseBody
    @RequestMapping("deleted")
    public Object deleted(Integer id){
        SensitiveWordInfoBean sensitiveWordInfoModel = new SensitiveWordInfoBean();
        sensitiveWordInfoModel.setDeleted(1);
        sensitiveWordInfoModel.setId(id);
        sensitiveWordService.updateSensiticeWord(sensitiveWordInfoModel);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

}

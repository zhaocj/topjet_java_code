package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.tool.redis.config.RedisConstant;
import com.topjet.tool.redis.redisuser.service.StringRedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by tsj010 on 2018/4/26.
 */
@Controller
@RequestMapping("orderSignTime")
public class orderSignTimeController extends BaseController {

    @Autowired
    private StringRedisService stringRedisService;



    @RequestMapping("orderSignTimeList")
    public ModelAndView orderSignTimeList(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("view/orderSignTime/orderSignTimeDuration");
        //从redis中获取值
        String  value =stringRedisService.getStringValue(RedisConstant.ORDER_SIGN_TIME);
        view.addObject("orderSignTime",value);
        return view;
    }



    @RequestMapping("update")
    @ResponseBody
    public Object callSurveyDurationUpdate(String  orderTime) {
        try {
            stringRedisService.saveStringValue(RedisConstant.ORDER_SIGN_TIME,orderTime);
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
        }catch (Exception e){

            msg.setStatus(ExceptionEnum.E_3.getStatus());
            msg.setMsg(ExceptionEnum.E_3.getMsg());
            e.printStackTrace();
        }
        return msg;
    }



}

package com.topjet.manage.controller;

import com.topjet.common.ExceptionEnum;
import com.topjet.common.util.SystemConfiguration;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.CallSurveyDurationBean;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Created by liyj on 2017/10/18.
 */
@Controller
@RequestMapping("/callSurveyDuration/")
public class CallSurveyDurationController extends BaseController {



    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String, String> collocationInfoModelRedisTemplate;

    @RequestMapping("callSurveyDurationList")
    public Object callSurveyDurationList(){
        ModelAndView mv = new ModelAndView();
        CallSurveyDurationBean csd = new CallSurveyDurationBean();
        ValueOperations opsForValue = this.collocationInfoModelRedisTemplate.opsForValue();
        csd.setDuration(opsForValue.get(SystemConfiguration.CALL_SURVEY_DURATION).toString());
        mv.setViewName("view/callSurveyDuration/callSurveyDuration");
        mv.addObject("callSurveyDurationBean",csd);
        return mv;
    }

    @RequestMapping("update")
    @ResponseBody
    public Object callSurveyDurationUpdate(CallSurveyDurationBean csd) {
        //updateResource(csd.getDuration());
        ValueOperations opsForValue = this.collocationInfoModelRedisTemplate.opsForValue();
        opsForValue.set(SystemConfiguration.CALL_SURVEY_DURATION,csd.getDuration());
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }


}

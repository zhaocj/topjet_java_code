package com.topjet.manage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-30 17:16
 */

@Controller
@RequestMapping("/test")
public class TestController {

    @Autowired
    private  TaskJob taskJob;
    @Autowired
    private  BirthdaySmsTask birthdaySmsTask;
    @Autowired
    private BonusAndScoreSettingJob bonusAndScoreSettingJob;

    @Autowired
    private RecommendationJob recommendationJob;

    ModelAndView mv = new ModelAndView("index");

    @RequestMapping("/index")
    public ModelAndView test(){

        return mv;
    }

    /**
     * 定时任务
     * @param request
     */
    @RequestMapping("/taskJob")
    @ResponseBody
    public ModelAndView test(HttpServletRequest request){
        taskJob.createAndAssignTasks();
        return mv;
    }

    /**
     * 生日祝福任务
     * @param request
     */
    @RequestMapping("/birthdaySmsTask")
    @ResponseBody
    public ModelAndView birthdaySmsTask(HttpServletRequest request){
        //获取发送短信用户
        birthdaySmsTask.getBirthdayUser();
        //发送短信
        birthdaySmsTask.sendBirthdayMessage();
        return mv;
    }


    /**
     * 补贴任务
     * @param request
     */
    @RequestMapping("/bonusAndScoreSettingJob")
    @ResponseBody
    public ModelAndView bonusAndScoreSettingJob(HttpServletRequest request){
        bonusAndScoreSettingJob.reSettingBonusAndScore();
        bonusAndScoreSettingJob.reSettingBonus();
        return mv;
    }


    /**
     * 推荐补贴任务
     * @param request
     */
    @RequestMapping("/recommendationJob")
    @ResponseBody
    public ModelAndView recommendationJob(HttpServletRequest request){
        recommendationJob.execute();
        return mv;
    }


}

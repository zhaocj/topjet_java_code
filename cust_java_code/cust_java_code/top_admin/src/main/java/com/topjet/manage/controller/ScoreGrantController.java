package com.topjet.manage.controller;

import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.ScoreBean;
import com.topjet.manage.domain.model.ScoreInfoModel;
import com.topjet.manage.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liyj on 2017/10/16.
 */
@Controller
@Scope("prototype")
@RequestMapping("/scoreGrantAction/")
public class ScoreGrantController extends BaseController{

    @Autowired
    private ScoreService scoreService;



    @RequestMapping("list")
    @ResponseBody
    public Object list(ScoreBean scoreBean){
        PaginationBean bean = new PaginationBean();
        List<ScoreBean> dataList = scoreService.getScoreList(scoreBean);
        int total = scoreService.getScoreCount(scoreBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

    @ResponseBody
    @RequestMapping("queryMobile")
    public Object queryMobile(ScoreBean scoreBean) {
        scoreBean = scoreService.findByMoblie(scoreBean.getMobile());
        if (scoreBean != null) {
            return scoreBean;
        } else {
            msg.setMsg(ExceptionEnum.E_20.getMsg());
            msg.setStatus(ExceptionEnum.E_20.getStatus());
            return msg;
        }
    }

    @ResponseBody
    @RequestMapping("beforeSubmitGrant")
    public Object beforeSubmitGrant(ScoreBean scoreBean){
        return scoreService.isBlackList(scoreBean.getMobile());
    }

    @ResponseBody
    @RequestMapping("submitGrant")
    public Object submitGrant(ScoreInfoModel scoreInfoModel) {
        scoreService.insertScoreInfo(scoreInfoModel);
        return msg;
    }


}

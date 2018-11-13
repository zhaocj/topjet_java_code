package com.topjet.manage.controller;

import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.BlockedBonusBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.vo.RecommendationBonusListQuery;
import com.topjet.manage.domain.vo.RecommendationBonusListVO;
import com.topjet.manage.service.BonusManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by liyj on 2017/8/29.
 */
@Controller
@RequestMapping("/bonusManage/")
public class BonusManageController extends BaseController {

    @Autowired
    private BonusManageService bonusManageService;

    /**
     * 推荐补贴
     * @return
     */
    @ResponseBody
    @RequestMapping("recommendationBonusList")
    public Object recommendationBonusList(RecommendationBonusListQuery query){
        List<RecommendationBonusListVO> list= bonusManageService.getRecommendationBonusList(query);
        int total=bonusManageService.getRecommendationBonusListCount(query);
        PaginationBean beans=new PaginationBean();
        beans.setRows(list);
        beans.setTotal(total);
        return beans;
    }

    /**
     * 被屏蔽补贴管理
     */
    @ResponseBody
    @RequestMapping("blockedBonusList")
    public Object blockedBonusList(BlockedBonusBean blockedBonusBean){
        PaginationBean bean = new PaginationBean();
        List<BlockedBonusBean> dataList = bonusManageService.getBlockedList(blockedBonusBean);
        int total = bonusManageService.getBlockedCount(blockedBonusBean);
        bean.setRows(dataList);
        bean.setTotal(total);
        return bean;
    }

}

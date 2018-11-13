package com.topjet.manage.service;

import com.topjet.cloud.manage.mq.score.ScoreGainMQBean;
import com.topjet.manage.domain.bean.ScoreBean;
import com.topjet.manage.domain.model.ScoreInfoModel;

import java.util.List;

/**
 * Created by liyj on 2017/10/16.
 */
public interface ScoreService {

    /**
     * 积分人工发放列表
     */
    public List<ScoreBean> getScoreList(ScoreBean scoreBean);
    /**
     * 积分人工发放页数
     */
    public Integer getScoreCount(ScoreBean scoreBean);
    /**
     * 根据手机号查询
     */
    public ScoreBean findByMoblie(String mobile);
    /**
     * 根据手机号查询是否是黑名单
     */
    public int isBlackList(String mobile);
    /**
     * 添加人工积分
     */
    public Integer insertScoreInfo(ScoreInfoModel scoreInfoModel);

}

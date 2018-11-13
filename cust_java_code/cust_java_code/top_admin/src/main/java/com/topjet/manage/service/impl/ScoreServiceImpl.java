package com.topjet.manage.service.impl;

import com.topjet.cloud.manage.mq.score.ScoreGainMQBean;
import com.topjet.cloud.manage.mq.score.constant.ScoreConstant;
import com.topjet.common.util.ScoreMQUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.ScoreBean;
import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.ScoreInfoModel;
import com.topjet.manage.domain.model.UserBlackListInfoModel;
import com.topjet.manage.mapper.readMapper.ScoreInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.service.ScoreService;
import com.topjet.manage.service.UserBlackListInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/10/16.
 */
@Service
public class ScoreServiceImpl implements ScoreService{

    private static final Logger LOG = LoggerFactory.getLogger(ScoreServiceImpl.class);

    @Autowired
    private ScoreInfoModelEMapper scoreInfoModelEMapper;
    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
    @Autowired
    private UserBlackListInfoService userBlackListInfoService;
    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Override
    public List<ScoreBean> getScoreList(ScoreBean scoreBean) {
        return scoreInfoModelEMapper.getScoreList(scoreBean);
    }

    @Override
    public Integer getScoreCount(ScoreBean scoreBean) {
        return scoreInfoModelEMapper.getScoreCount(scoreBean);
    }

    @Override
    public ScoreBean findByMoblie(String mobile) {
        ScoreBean scoreBean = new ScoreBean();
        UserInfoBean userInfoBean = scoreInfoModelEMapper.findByMoblie(mobile);
        if(userInfoBean != null){
            scoreBean.setName(userInfoBean.getUsername());
            scoreBean.setIdNo(userInfoBean.getIdNo());
            scoreBean.setType(userInfoBean.getUserType()+"");
            scoreBean.setBusinessAddress(userInfoBean.getBusinessAddress());
            scoreBean.setCompanyName(userInfoBean.getCompanyName());
            scoreBean.setResident1(userInfoBean.getResident1());
            scoreBean.setResident2(userInfoBean.getResident2());
            scoreBean.setUserId(userInfoBean.getId());
            return scoreBean;
        }
            return null;
    }

    @Override
    public int isBlackList(String mobile) {
        UserInfoBean userInfoBean = scoreInfoModelEMapper.findByMoblie(mobile);
        if(userInfoBean == null){
            return 2;
        }
        UserBlackListInfoModel userBlackListInfoModel = userBlackListInfoService.getBlackListInfoByUserId(userInfoBean.getId());
        if(userBlackListInfoModel != null){
            return 1;
        }
        return 0;
    }

    @Override
    public Integer insertScoreInfo(ScoreInfoModel scoreInfoModel) {
            ScoreMQUtil scoreMQUtil = new ScoreMQUtil();
            ScoreGainMQBean  scoreGainMQBean = new ScoreGainMQBean();
            scoreGainMQBean.setUserId(scoreInfoModel.getUserId());
            scoreGainMQBean.setCause(scoreInfoModel.getCause());
            scoreGainMQBean.setScoreType(ScoreConstant.SCORE_TYPE_ARTIFICIAL);
            scoreGainMQBean.setValue(scoreInfoModel.getValue());
            scoreMQUtil.scoreGainMQBean(scoreGainMQBean);
            return 0;
    }

}

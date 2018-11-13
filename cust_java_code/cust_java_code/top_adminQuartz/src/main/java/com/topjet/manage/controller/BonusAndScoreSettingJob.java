package com.topjet.manage.controller;

import com.topjet.manage.domain.model.BonusSettingModel;
import com.topjet.manage.domain.model.ScoreNewSettingModel;
import com.topjet.manage.service.BonusAndScoreNewSettingService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description:补贴和积分配置定时任务：每日0：00：00秒扫描配置表，重置已经修改的配置
 * @Date: 2017-10-25 13:32
 */


@Component
//@EnableScheduling
public class BonusAndScoreSettingJob {
    private static final Logger logger = LoggerFactory.getLogger(BonusAndScoreSettingJob.class);

    @Autowired
    private BonusAndScoreNewSettingService bonusAndScoreNewSettingService;

//    @Scheduled(cron ="0 0 0 * * ?")
    public void reSettingBonusAndScore(){

        //先获取已设置但是尚未生效的积分设置项

        List<ScoreNewSettingModel> unResetScoreNewSettings = bonusAndScoreNewSettingService.getUnResetScoreNewSetting();
        logger.info("获取尚未生效的积分配置，共有："+unResetScoreNewSettings.size()+ "条");

        //查询当前生效的积分设置项
        if(unResetScoreNewSettings != null && unResetScoreNewSettings.size()>0){

            for (ScoreNewSettingModel unResetScoreNewSetting : unResetScoreNewSettings) {
                ScoreNewSettingModel scoreNewSettingModel = bonusAndScoreNewSettingService.getScoreNewSettingModel(unResetScoreNewSetting.getScoreType(), 0);
                if(scoreNewSettingModel != null){
                    scoreNewSettingModel.setDeleted(1);
                    //将旧积分设置项置为失效
                    bonusAndScoreNewSettingService.updateScoreNewSetting(scoreNewSettingModel);
                    unResetScoreNewSetting.setDeleted(0);
                    bonusAndScoreNewSettingService.updateScoreNewSetting(unResetScoreNewSetting);
                }
            }

        }
        logger.info("重置积分设置结束！");
    }


    /**
     * 补贴配置定时任务
     */
//    @Scheduled(cron ="0 0 0 * * ?")
    public void reSettingBonus(){

        //先获取已设置但是尚未生效的补贴设置项

        List<BonusSettingModel> unResetScoreNewSettings = bonusAndScoreNewSettingService.getResetBonusSetting();
        logger.info("获取尚未生效的补贴配置，共有："+unResetScoreNewSettings.size()+ "条");

        //查询当前生效的积分设置项
        if(unResetScoreNewSettings != null && unResetScoreNewSettings.size()>0){

            for (BonusSettingModel unResetBonusSetting : unResetScoreNewSettings) {
                BonusSettingModel bonusSettingModel = bonusAndScoreNewSettingService.getBonusSettingModel(unResetBonusSetting.getBonusType(), 0, unResetBonusSetting.getBonusTarget());
                if(bonusSettingModel != null){
                    bonusSettingModel.setDeleted(1);
                    //将旧积分设置项置为失效
                    bonusAndScoreNewSettingService.updateBonusSetting(bonusSettingModel);
                    unResetBonusSetting.setDeleted(0);
                    bonusAndScoreNewSettingService.updateBonusSetting(unResetBonusSetting);
                }
            }

        }
        logger.info("重置补贴设置结束！");
    }








}

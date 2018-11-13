package com.topjet.manage.controller;

import com.topjet.constants.SystemConfig;
import com.topjet.manage.domain.model.RecommendRelationshipModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusSettingModel;
import com.topjet.manage.mapper.readMapper.RecommendRelationshipModelEMapper;
import com.topjet.manage.mapper.readMapper.RecommendationBonusSettingModelEMapper;
import com.topjet.manage.service.RecommendationBonusInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description: 生成推荐补贴任务
 * @Date: 2017-09-04 16:34
 */

@Component
@Configurable
//@EnableScheduling
public class RecommendationJob {

    private static final Logger logger = LoggerFactory.getLogger(RecommendationJob.class);

    @Autowired
    private RecommendationBonusInfoService recommendationBonusInfoService;

    @Autowired
    private RecommendRelationshipModelEMapper recommendRelationshipModelEMapper;

    @Autowired
    private RecommendationBonusSettingModelEMapper recommendationBonusSettingModelEMapper;

    @Autowired
    private SystemConfig systemConfig;

    //推荐补贴生成
//    @Scheduled(cron ="0 0/1 * * * ?")
    public void execute() {

        if(systemConfig.getRecommendBonusSwitch().equals("0")){
            return;
        }

        try {
            logger.info("生成审核推荐补贴job执行开始");
            List<RecommendRelationshipModel> rrsList = recommendationBonusInfoService.getSuitableRecoBonusAllUser();
            for(RecommendRelationshipModel rrs : rrsList){
                logger.info("获得一条审核待生成的推荐补贴RecommendRelationshipModel ID: "+rrs.getId());
                createRecommendation(rrs);
            }
            logger.info("生成审核推荐补贴job执行结束");
        } catch (Exception e) {
            logger.error("生成审核推荐补贴job执行异常:" + e.getMessage());
        }

    }






    public void createRecommendation(RecommendRelationshipModel recommendRelationshipModel) {

        int recommendRelationshipId = 0;
        Map<String,Object> paramMap = new HashMap<String ,Object>();
            logger.info("审核推荐补贴条件 关系IDrecommendRelationshipId:"+recommendRelationshipModel.getId()+" 推荐人recommendMobile:"+recommendRelationshipModel.getRecommendMobile()+" 被推荐人recommendedMobile:"+recommendRelationshipModel.getRecommendedMobile());
            if (recommendationBonusInfoService.verificationBinus(recommendRelationshipModel)) {
                logger.info("满足生成推荐补贴条件");
                int recommendUserId = recommendRelationshipModel.getRecommendUserId();
                recommendRelationshipId = recommendRelationshipModel.getId();
                paramMap.clear();
                paramMap.put("deleted",0);
                List<RecommendationBonusSettingModel> recommendationBonusSettingModels = recommendationBonusSettingModelEMapper.selectListByParam(paramMap);

                if (recommendationBonusSettingModels != null && recommendationBonusSettingModels.size() > 0) {
                    RecommendationBonusSettingModel recommendationBonusSettingModel = recommendationBonusSettingModels.get(0);
                    /* 生成补贴记录 */
                    RecommendationBonusInfoModel recommendationBonusInfoModel = new RecommendationBonusInfoModel();
                    recommendationBonusInfoModel.setAmount(recommendationBonusSettingModel.getAmountPerPerson());
                    recommendationBonusInfoModel.setAuditStatus(0);
                    recommendationBonusInfoModel.setUserId(recommendUserId);
                    recommendationBonusInfoModel.setRecommendationId(recommendRelationshipId);
                    recommendationBonusInfoModel.setSettingId(recommendationBonusSettingModel.getId());
                    recommendationBonusInfoModel.setCreateTime(new Date());
                    recommendationBonusInfoService.insert(recommendationBonusInfoModel);
                    logger.info("生成推荐补贴条件成功recommendationBonusInfoModel-->userId:"+recommendUserId+"recommendationId:"+recommendRelationshipId);
                }
            }


    }

}

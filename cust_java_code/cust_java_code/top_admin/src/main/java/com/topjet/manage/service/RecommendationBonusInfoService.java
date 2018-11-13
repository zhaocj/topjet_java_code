package com.topjet.manage.service;

import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.domain.bean.RecommendationBonusBean;
import com.topjet.manage.domain.bean.RecommendationBonusResponseBean;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description: 推荐补贴service
 * @Date: 2017-08-28 10:37
 */

public interface RecommendationBonusInfoService {

    /**
     * 推荐费补贴审核查询列表方法
     * @param recommendationBonusBean
     * @return
     */
    List<RecommendationBonusBean> listRecommendationBonusInfo(RecommendationBonusBean recommendationBonusBean);

    /**
     * 推荐费补贴审核查询数量方法
     * @param recommendationBonusBean
     * @return
     */
    int queryByCount(RecommendationBonusBean recommendationBonusBean);

    /**
     * 审核推荐费补贴方法
     * @param recommendationBonusBean
     * @return
     * @throws TopjetExceptionHandler
     */
    public RecommendationBonusResponseBean audit(RecommendationBonusBean recommendationBonusBean) throws TopjetExceptionHandler;


    public List<RecommendationBonusInfoModel> selectRecommendationBonusInfo(RecommendationBonusInfoModel recommendationBonusInfoModel);

    RecommendationBonusInfoModel selectByPrimaryKey(Integer id);

    int updateByPrimaryKey(RecommendationBonusInfoModel recommendationBonusInfoModel);

    List<RecommendationBonusBean> queryByPendingList(RecommendationBonusBean recommendationBonusBean);

    int queryByPendingCount(RecommendationBonusBean recommendationBonusBean);







}

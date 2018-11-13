package com.topjet.manage.service;

import com.topjet.manage.domain.bean.BlockedBonusBean;
import com.topjet.manage.domain.vo.RecommendationBonusListQuery;
import com.topjet.manage.domain.vo.RecommendationBonusListVO;

import java.util.List;

/**
 * Created by liyj on 2017/8/29.
 */
public interface BonusManageService {
    /**
     * 推荐补贴
     * @param dto
     * @return
     */
    public List<RecommendationBonusListVO> getRecommendationBonusList(RecommendationBonusListQuery dto);
    /**
     * 推荐补贴管理页数
     */
    public Integer getRecommendationBonusListCount(RecommendationBonusListQuery dto);
    /**
     * 被屏蔽补贴管理
     */
    public List<BlockedBonusBean> getBlockedList(BlockedBonusBean blockedBonusBean);
    /**
     * 被屏蔽补贴管理页数
     */
    public Integer getBlockedCount(BlockedBonusBean blockedBonusBean);
}

package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.BlockedBonusBean;
import com.topjet.manage.domain.vo.RecommendationBonusListQuery;
import com.topjet.manage.domain.vo.RecommendationBonusListVO;
import com.topjet.manage.mapper.readMapper.BlockedBonusInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.RecommendationBonusInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.RecommendationBonusInfoModelMapper;
import com.topjet.manage.service.BonusManageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/8/29.
 */
@Service
public class BonusManageServiceImpl implements BonusManageService {

    @Autowired
    private RecommendationBonusInfoModelMapper recommendationBonusInfoModelMapper;
    @Autowired
    private RecommendationBonusInfoModelEMapper recommendationBonusInfoModelEMapper;
    @Autowired
    private BlockedBonusInfoModelEMapper blockedBonusInfoModelEMapper;

    @Override
    public List<RecommendationBonusListVO> getRecommendationBonusList(RecommendationBonusListQuery dto) {
        return recommendationBonusInfoModelEMapper.getRecommendationBonusList(dto);
    }

    @Override
    public Integer getRecommendationBonusListCount(RecommendationBonusListQuery dto) {
        return recommendationBonusInfoModelEMapper.getRecommendationBonusListCount(dto);
    }

    @Override
    public List<BlockedBonusBean> getBlockedList(BlockedBonusBean blockedBonusBean) {
        return blockedBonusInfoModelEMapper.getBlockedList(blockedBonusBean);
    }

    @Override
    public Integer getBlockedCount(BlockedBonusBean blockedBonusBean) {
        return blockedBonusInfoModelEMapper.getBlockedCount(blockedBonusBean);
    }
}

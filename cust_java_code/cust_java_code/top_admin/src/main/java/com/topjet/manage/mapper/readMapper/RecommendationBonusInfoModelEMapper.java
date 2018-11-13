package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.RecommendationBonusBean;
import com.topjet.manage.domain.vo.RecommendationBonusListQuery;
import com.topjet.manage.domain.vo.RecommendationBonusListVO;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface RecommendationBonusInfoModelEMapper extends BaseEMapper<RecommendationBonusInfoModel> {

    //查询推荐补贴一审列表
    List<RecommendationBonusBean> listRecommendationBonusInfo(RecommendationBonusBean recommendationBonusBean);

    //查询推荐补贴一审总数
    public int countRecommendationBonusInfo(RecommendationBonusBean recommendationBonusBean);

    List<RecommendationBonusInfoModel> selectListByEntity(RecommendationBonusInfoModel recommendationBonusInfoModel);

    //查询三
    public List<RecommendationBonusBean> queryByPendingList(RecommendationBonusBean recommendationBonusBean);

    public int queryByPendingCount(RecommendationBonusBean recommendationBonusBean);

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


    //查询推荐补贴二审列表
    List<RecommendationBonusBean> listRecommendationBonusInfos(RecommendationBonusBean recommendationBonusBean);

    //查询推荐补贴二审总数
    public int countRecommendationBonusInfos(RecommendationBonusBean recommendationBonusBean);




}
package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.BonusBillInfoBean;
import com.topjet.manage.domain.bean.BonusBillInfoModelBean;
import com.topjet.manage.domain.bean.RecommendationFeeBounsBillDeatailBean;
import com.topjet.manage.domain.bean.TransportFeeBounsBillDeatailBean;
import com.topjet.manage.domain.model.BonusBillInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface BonusBillInfoModelEMapper extends BaseEMapper<BonusBillInfoModel> {

    List<BonusBillInfoModel> selectListByEntity(BonusBillInfoModel bonusBillInfoModel);

    BonusBillInfoModel getRecommendationFeeBounsBillAllCount(RecommendationBonusInfoModel recommendationBonusInfoModel);


    /**
     * 根据日期查询补贴订单
     * @param
     * @return
     */
    public List<BonusBillInfoBean>getBillInfo(BonusBillInfoBean bib);

    /**
     * 根据日期查询补贴订单总数
     * @param
     * @return
     */
    public Integer getBillCount(BonusBillInfoBean bib);

    public BonusBillInfoModel getBonusBillInfo(Map<String, Object> paramMap);

    /**
     * 补贴发放查询明细信息
     * @param model
     * @return
     */
    public List<RecommendationFeeBounsBillDeatailBean> getRecommendationFeeBounsBillDeatailBean(RecommendationFeeBounsBillDeatailBean model);

    /**
     * 补贴发放查询明细数量
     * @param model
     * @return
     */
    public Integer getRecommendationFeeBounsBillDeatailBeanCount(RecommendationFeeBounsBillDeatailBean model);

    /**
     * 获取未支付的补贴
     * @param bean
     * @return
     */
    public List<BonusBillInfoModelBean> getBillInfoModelBean(BonusBillInfoBean bean);

    List<TransportFeeBounsBillDeatailBean> getTransportFeeBounsBillDeatailBean(TransportFeeBounsBillDeatailBean model);

    Integer getTransportFeeBounsBillDeatailBeanCount(TransportFeeBounsBillDeatailBean model);


}
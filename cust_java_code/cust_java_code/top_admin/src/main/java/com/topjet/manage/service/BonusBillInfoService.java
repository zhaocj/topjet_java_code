package com.topjet.manage.service;

import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.BonusBillInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;

import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description: 补贴账单表service
 * @Date: 2017-09-01 9:48
 */

public interface BonusBillInfoService {

    /**
     * 分页获取补贴账单表list
     * @param paramMap
     * @return
     */
    List<BonusBillInfoModel> selectPageListByEntity(Map<String,Object> paramMap);

    /**
     * 获取补贴账单表数量
     * @param paramMap
     * @return
     */
    int getCountByParam(Map<String,Object> paramMap);

    /**
     *获取补贴账单表完整信息
     */
    List<BonusBillInfoModel> selectByEntity(BonusBillInfoModel bonusBillInfoModel);

    /**
     * 根据收款人id获取账单表信息
     */
    BonusBillInfoModel getBillInfoModelInfo(RecommendationBonusInfoModel recommendationBonusInfoModel);

    /**
     * 更新一天内单人推荐费总额
     * @param
     * @return
     */
    void updateBillInfoForRecommendation(BonusBillInfoModel bn);

    int insertSelective(BonusBillInfoModel bonusBillInfoModel);

    /*
     * 生成账单号：type：1 父账单 2.子账单
     * 规则：PHB + YYYYMMDDHHMM +3位随机数
     */

    String getBillNo(Integer type);

    /**
     * 根据日期参数查询相应的补贴订单分类
     * @param
     * @return
     */
    List<BonusBillInfoBean> getBillInfo(BonusBillInfoBean bib);

    Integer getBillCount(BonusBillInfoBean bib);

    BonusBillInfoModel getBonusBillInfo(Map<String,Object> paramMap);

    BonusBillInfoModel selectByPrimaryKey(Integer id);

    /**
     * 获取推荐补贴的订单明细
     * @param model
     * @return
     */
    PaginationBean getRecommendationFeeBounsBillDeatailBean(RecommendationFeeBounsBillDeatailBean model);

    /**
     * 获取指定未支付的补贴
     * @param bean
     * @return
     */
    List<BonusBillInfoModelBean> getBillInfoModelBean(BonusBillInfoBean bean);

    int update(BonusBillInfoModel bonusBillInfoModel);

    int updateByParentId(BonusBillInfoModel bonusBillInfoModel);


    /**
     * 删除账单
     * @param id
     * @return
     */
    void deleteBill(Integer id);

    PaginationBean getTransportFeeBounsBillDeatailBean(TransportFeeBounsBillDeatailBean model);
}

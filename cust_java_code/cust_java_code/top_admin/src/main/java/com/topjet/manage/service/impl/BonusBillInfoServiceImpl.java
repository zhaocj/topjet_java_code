package com.topjet.manage.service.impl;

import com.topjet.common.util.DateStyle;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.BonusBillInfoModel;
import com.topjet.manage.domain.model.RecommendationBonusInfoModel;
import com.topjet.manage.mapper.readMapper.BonusBillInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.BonusBillInfoModelMapper;
import com.topjet.manage.service.BonusBillInfoService;
import com.topjet.manage.service.UserInfoService;
import org.apache.commons.lang.RandomStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-01 9:51
 */

@Service
@Transactional
public class BonusBillInfoServiceImpl implements BonusBillInfoService{

    @Autowired
    private BonusBillInfoModelMapper bonusBillInfoModelMapper;
    @Autowired
    private BonusBillInfoModelEMapper bonusBillInfoModelEMapper;

    @Autowired
    private UserInfoService userInfoService;


    @Override
    public List<BonusBillInfoModel> selectPageListByEntity(Map<String,Object> paramMap) {
        return bonusBillInfoModelEMapper.selectPageListByParam(paramMap);
    }

    @Override
    public int getCountByParam(Map<String,Object> paramMap) {
        return bonusBillInfoModelEMapper.getCountByParam(paramMap);
    }

    @Override
    public List<BonusBillInfoModel> selectByEntity(BonusBillInfoModel bonusBillInfoModel) {
        return bonusBillInfoModelEMapper.selectListByEntity(bonusBillInfoModel);
    }

    @Override
    public BonusBillInfoModel getBillInfoModelInfo(RecommendationBonusInfoModel recommendationBonusInfoModel) {
        return bonusBillInfoModelEMapper.getRecommendationFeeBounsBillAllCount(recommendationBonusInfoModel);
    }

    @Override
    public void updateBillInfoForRecommendation(BonusBillInfoModel bn) {
        bonusBillInfoModelMapper.update(bn);
    }

    @Override
    public int insertSelective(BonusBillInfoModel bonusBillInfoModel) {
        return bonusBillInfoModelMapper.insert(bonusBillInfoModel);
    }

    @Override
    public String getBillNo(Integer type) {
        String date = DateUtil.DateToString(DateUtil.now(), DateStyle.YYYYMMDDHHMM);
        String random = RandomStringUtils.random(3, false, true);
        return type ==1?("PHBA"+ date + random):("PHBB"+ date + random);
    }

    @Override
    public List<BonusBillInfoBean> getBillInfo(BonusBillInfoBean bib) {
        return bonusBillInfoModelEMapper.getBillInfo(bib);
    }

    @Override
    public Integer getBillCount(BonusBillInfoBean bib) {
        return bonusBillInfoModelEMapper.getBillCount(bib);
    }

    @Override
    public BonusBillInfoModel getBonusBillInfo(Map<String,Object> paramMap) {
        return bonusBillInfoModelEMapper.getBonusBillInfo(paramMap);
    }

    @Override
    public BonusBillInfoModel selectByPrimaryKey(Integer id) {
        return bonusBillInfoModelEMapper.selectByPrimaryKey(id);
    }

    @Override
    public PaginationBean getRecommendationFeeBounsBillDeatailBean(RecommendationFeeBounsBillDeatailBean model) {
        List<RecommendationFeeBounsBillDeatailBean> rows = bonusBillInfoModelEMapper.getRecommendationFeeBounsBillDeatailBean(model);
        Integer total = bonusBillInfoModelEMapper.getRecommendationFeeBounsBillDeatailBeanCount(model);
        PaginationBean bean = new PaginationBean();
        bean.setRows(rows);
        bean.setTotal(total);
        return bean;
    }

    @Override
    public List<BonusBillInfoModelBean> getBillInfoModelBean(BonusBillInfoBean bean) {
        return bonusBillInfoModelEMapper.getBillInfoModelBean(bean);
    }

    @Override
    public int update(BonusBillInfoModel bonusBillInfoModel) {
        return bonusBillInfoModelMapper.update(bonusBillInfoModel);
    }

    @Override
    public int updateByParentId(BonusBillInfoModel bonusBillInfoModel) {
        return bonusBillInfoModelMapper.updateByParentId(bonusBillInfoModel);
    }

    @Override
    public void deleteBill(Integer id) {
        BonusBillInfoModel bonusBillInfoModel = bonusBillInfoModelEMapper.selectByPrimaryKey(id);
        BonusBillInfoModel parentBill = bonusBillInfoModelEMapper.selectByPrimaryKey(bonusBillInfoModel.getParentId());
        if(bonusBillInfoModel != null){
            bonusBillInfoModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
            Integer update = bonusBillInfoModelMapper.update(bonusBillInfoModel);
            parentBill.setAmount(parentBill.getAmount().subtract(bonusBillInfoModel.getAmount()));
            if(parentBill.getAmount().compareTo(new BigDecimal(0))>0){
                bonusBillInfoModelMapper.update(parentBill);
            }else{
                parentBill.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_TURE);
                bonusBillInfoModelMapper.update(parentBill);
            }
        }
    }

    @Override
    public PaginationBean getTransportFeeBounsBillDeatailBean( TransportFeeBounsBillDeatailBean model) {
        PaginationBean bean = new PaginationBean();
        bean.setRows(bonusBillInfoModelEMapper.getTransportFeeBounsBillDeatailBean(model));
        bean.setTotal(bonusBillInfoModelEMapper.getTransportFeeBounsBillDeatailBeanCount(model));
        return bean;
    }

}

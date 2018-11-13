package com.topjet.manage.service;

import com.topjet.manage.domain.bean.RefundDetail2Bean;
import com.topjet.manage.domain.model.RefundInfoModel;
import com.topjet.manage.domain.model.RefundPhotoInfoModel;
import sun.util.resources.cldr.ig.CalendarData_ig_NG;

import java.util.List;

/**
 * Created by liyj on 2017/9/25.
 */
public interface RefundService {

    //根据传入条件获得所有符合条件的退款账单
    public List<RefundDetail2Bean> getRefundsList(RefundDetail2Bean refundDetail2Bean);

    //根据传入条件获得所有符合条件的退款账单数量
    public Integer getRefundsCount(RefundDetail2Bean refundDetail2Bean);

    //查询refundPhotoInfo表 根据refundType=0和refundId 查询
    public List<RefundPhotoInfoModel> getRefundPhontList0(Integer refundId);

    //查询refundPhotoInfo表 根据refundType=1和refundId 查询
    public List<RefundPhotoInfoModel> getRefundPhontList1(Integer refundId);

    //退款
    public void updateById(RefundInfoModel refundInfoModel,Integer ownerId,Integer orderId);

}

package com.topjet.manage.service.impl;

import com.topjet.basic.RefundFeignService;
import com.topjet.basic.fallback.OrderFeignFallBack;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoRTS;
import com.topjet.cloud.manage.custservice.order.bean.UpdateRefundCsInfoVRU;
import com.topjet.manage.domain.bean.RefundDetail2Bean;
import com.topjet.manage.domain.model.RefundInfoModel;
import com.topjet.manage.domain.model.RefundPhotoInfoModel;
import com.topjet.manage.mapper.readMapper.RefundInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.RefundPhotoInfoModelEMapper;
import com.topjet.manage.service.RefundService;
import com.topjet.tool.common.util.ExceptionUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.util.resources.cldr.luo.CurrencyNames_luo;

import java.util.Date;
import java.util.List;

/**
 * Created by liyj on 2017/9/25.
 */
@Service
public class RefundServiceImpl implements RefundService {


    private static Logger log = LoggerFactory.getLogger(RefundServiceImpl.class);

    @Autowired
    private RefundInfoModelEMapper refundInfoModelEMapper;
    @Autowired
    private RefundPhotoInfoModelEMapper refundPhotoInfoModelEMapper;
    @Autowired
    private RefundFeignService refundFeignService;

    @Override
    public List<RefundDetail2Bean> getRefundsList(RefundDetail2Bean refundDetail2Bean) {
        return refundInfoModelEMapper.getRefundsList(refundDetail2Bean);
    }

    @Override
    public Integer getRefundsCount(RefundDetail2Bean refundDetail2Bean)
    {
        return refundInfoModelEMapper.getRefundsCount(refundDetail2Bean);
    }

    @Override
    public List<RefundPhotoInfoModel> getRefundPhontList0(Integer refundId) {
        return refundPhotoInfoModelEMapper.getRefundPhontList0(refundId);
    }

    @Override
    public List<RefundPhotoInfoModel> getRefundPhontList1(Integer refundId) {
        return refundPhotoInfoModelEMapper.getRefundPhontList1(refundId);
    }

    @Override
    public void updateById(RefundInfoModel refundInfoModel,Integer ownerId,Integer orderId) {
        ownerId = refundInfoModel.getTriggerId();
        orderId = refundInfoModel.getOrderId();
        UpdateRefundCsInfoRTS updateRefundCsInfoRTS = new UpdateRefundCsInfoRTS();
        updateRefundCsInfoRTS.setCsStatus(refundInfoModel.getCsStatus());
        updateRefundCsInfoRTS.setRemark(refundInfoModel.getRemark());
        updateRefundCsInfoRTS.setVersion(refundInfoModel.getVersion());
        updateRefundCsInfoRTS.setId(refundInfoModel.getId());
        updateRefundCsInfoRTS.setOrderId(orderId);
        updateRefundCsInfoRTS.setGoodsId(refundInfoModel.getId());
        updateRefundCsInfoRTS.setUserId(ownerId);
        try{
            UpdateRefundCsInfoVRU updateRefundCsInfoVRU = refundFeignService.UpdateRefundCsInfo(updateRefundCsInfoRTS);
        }catch (Exception e){
            log.error("退款服务请求异常： "+ ExceptionUtil.printCallStatck(e));
            e.printStackTrace();
        }

    }

}

package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.basic.OrderFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.custservice.order.bean.FrostOrUnfreezeOrderVRU;
import com.topjet.cloud.manage.custservice.order.bean.HiddenOrShowOrderRTS;
import com.topjet.cloud.manage.custservice.order.bean.HrostOrUnfreezeOrderRTS;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.constants.OrderConstant;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.OrderDetailAdminVO;
import com.topjet.manage.domain.vo.OrderInfoBean;
import com.topjet.manage.domain.vo.OrderListQuery;
import com.topjet.manage.domain.vo.OrderListVO;
import com.topjet.manage.mapper.readMapper.GoodsInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.OrderAffiliateModelEMapper;
import com.topjet.manage.mapper.readMapper.OrderAuditHistoryModelEMapper;
import com.topjet.manage.mapper.readMapper.OrderInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.OrderAuditHistoryModelMapper;
import com.topjet.manage.service.OrderService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by liyj on 2017/8/26.
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderInfoModelEMapper orderInfoModelMapper;
    @Autowired
    private BasicFeignService basicFeignService;
    @Autowired
    private OrderAuditHistoryModelMapper orderAuditHistoryModelMapper;
    @Autowired
    private OrderAuditHistoryModelEMapper orderAuditHistoryModelEMapper;
    @Autowired
    private GoodsInfoModelEMapper goodsInfoModelMapper;
    @Autowired
    private OrderAffiliateModelEMapper orderAffiliateModelMapper;
    @Autowired
    private OrderFeignService orderFeignService;


    @Override
    public List<OrderListVO> getOrderList(OrderListQuery orderListQuery) {
        return orderInfoModelMapper.getOrderList(orderListQuery);
    }

    @Override
    public Integer getOrderListCount(OrderListQuery orderListQuery) {
        return orderInfoModelMapper.getOrderListCount(orderListQuery);
    }

    @Override
    public OrderInfoModel findByOrderNo(String orderNo) {
        return orderInfoModelMapper.findByOrderNo(orderNo);
    }

    @Override
    public OrderDetailAdminVO orderDetail(Integer id) {
        OrderDetailAdminVO vo = orderInfoModelMapper.orderDetail(id);
        try {
            GeturlRTS geturlRTS = new GeturlRTS();
            if(!StringUtils.isBlank(vo.getPhotoRemark())){
                geturlRTS.setKey(vo.getPhotoRemark());
                geturlRTS.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
                vo.setPhotoRemark(basicFeignService.getUrl(geturlRTS).getObjurl());
            }
            if(!StringUtils.isBlank(vo.getAudioRemarkUrl())){
                geturlRTS.setKey(vo.getAudioRemarkUrl());
                geturlRTS.setType(PictureServerProperties.AUDIO_OWNER_REMARK);
                vo.setAudioRemarkUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

       /*
           //订单补贴信息
        //需要 根据订单id，类型查询订单补贴信息
       OrderBonusInfoModelCriteria orderBonusInfoModelCriteria=new OrderBonusInfoModelCriteria();
        orderBonusInfoModelCriteria.createCriteria().andOrderIdEqualTo(vo.getId()).
                andTypeEqualTo(OrderConstant.ORDER_BONUS_TYPE_TRANSPORT).andDeletedEqualTo(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        List<OrderBonusInfoModel> modelList=orderBonusInfoService.selectByCriteria(orderBonusInfoModelCriteria);
        if(modelList.isEmpty()){
           //被屏蔽补贴业务信息
           //需要根据订单id，类型查询被屏蔽补贴业务信息
            BlockedBonusInfoModelCriteria blockedBonusInfoModelCriteria=new BlockedBonusInfoModelCriteria();
            blockedBonusInfoModelCriteria.createCriteria().andOrderIdEqualTo(vo.getId()).
                    andTypeEqualTo(Integer.parseInt(CommonConstant.BLACK_LIST_TYPE_TRANSPORT_FEE_BONUS)).andDeletedEqualTo(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            List<BlockedBonusInfoModel> blockedList= blockedBonusInfoService.selectByCriteria(blockedBonusInfoModelCriteria);
            if(blockedList.isEmpty()){
                vo.setDriverAmount("无");
                vo.setOwnerAmount("无");
            }else {
                String remark="无(";
                for (BlockedBonusInfoModel model: blockedList) {
                    remark= remark+model.getRemark()+",";
                }
                vo.setDriverAmount(remark+")");
                vo.setOwnerAmount(remark+")");
            }
        }else{
            OrderBonusInfoModel orderBonusInfoModel=modelList.get(0);
            vo.setDriverAmount(FormatUtil.bigDecimalToString(orderBonusInfoModel.getDriverAmount()));
            vo.setOwnerAmount(FormatUtil.bigDecimalToString(orderBonusInfoModel.getOwnerAmount()));
        }*/

        return vo;
    }


    @Override
    public OrderDetailAdminVO goodsDetail(Integer id) {
        OrderDetailAdminVO vo = orderInfoModelMapper.goodsDetail(id);
        try {
            GeturlRTS geturlRTS = new GeturlRTS();
            if(!StringUtils.isBlank(vo.getPhotoRemark())){
                geturlRTS.setKey(vo.getPhotoRemark());
                geturlRTS.setType(PictureServerProperties.PHOTO_OWNER_REMARK);
                vo.setPhotoRemark(basicFeignService.getUrl(geturlRTS).getObjurl());
            }
            if(!StringUtils.isBlank(vo.getAudioRemarkUrl())){
                geturlRTS.setKey(vo.getAudioRemarkUrl());
                geturlRTS.setType(PictureServerProperties.AUDIO_OWNER_REMARK);
                vo.setAudioRemarkUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return vo;
    }

    @Override
    public Integer findGoodIdByOrderId(Integer id) {
        return orderInfoModelMapper.findGoodIdByOrderId(id);
    }

    @Override
    public Object isHidden(Integer goodsId, Integer version, Integer isHidden) {
        ResultBaseMsg msg=new ResultBaseMsg();
        //传的id就是订单id
        // 先根据订单id查询货源id 然后根据货源id修改 是否隐藏状态
      //  Integer goodId = orderInfoModelMapper.findGoodIdByOrderId(id);
      //  Integer versions = orderInfoModelMapper.findGoodIdByOrderId(id);
        if(goodsId == null){
            msg.setStatus(ExceptionEnum.E_2.getStatus());
            msg.setMsg(ExceptionEnum.E_2.getMsg());
            return msg;
        }
     /*   if(!versions.equals(version)){
            msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            return msg;
        }*/
        try{
            HiddenOrShowOrderRTS hiddenOrShowOrderRTS = new HiddenOrShowOrderRTS();
            //差一步修改的操作  修改隐藏状态goodsInfo表
            hiddenOrShowOrderRTS.setGoodsId(goodsId);
            hiddenOrShowOrderRTS.setOrderId(goodsId);
            hiddenOrShowOrderRTS.setUserId(SessionUtils.getSysUserSession().getId());
            hiddenOrShowOrderRTS.setIsHidden(isHidden);
            orderFeignService.hiddenOrShowOrder(hiddenOrShowOrderRTS);
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //插入订单审核历史表orderAuditHistory
        OrderAuditHistoryModel auditHistoryModel=new OrderAuditHistoryModel();
        auditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
        auditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        auditHistoryModel.setCreateTime(DateUtil.now());
        auditHistoryModel.setRemark("货源隐藏显示");
        auditHistoryModel.setSourceType(OrderConstant.AUDIT_GOOD_HIDDEN);
        //sourceID 根据货物id查询出订单id 将这个订单id放入sourceID中
        auditHistoryModel.setSourceId(goodsId);
        auditHistoryModel.setStatusAfter(OrderConstant.AUDIT_GOOD_HIDDEN_REMARK);
        auditHistoryModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        orderAuditHistoryModelMapper.insert(auditHistoryModel);
        return msg;
    }

    @Override
    public GoodsInfoModel selectByPrimaryKey(Integer id) {
        return goodsInfoModelMapper.findGoodInfoById(id);
    }

    @Override
    public GoodsDetailInfoModel findGoodDetailById(Integer id) {
        return goodsInfoModelMapper.findGoodDetailById(id);
    }

    /**
     * 根据sourceID sourceType获取日志
     */
    public List<OrderAuditHistoryModel> getOperationLog(OrderAuditHistoryModel orderAuditHistoryModel){
        return orderAuditHistoryModelEMapper.getOperationLog(orderAuditHistoryModel);
    }

    @Override
    public ResultBaseMsg addOrUpdateOrder(OrderInfoBean orderInfoBean) {
        ResultBaseMsg msg=new ResultBaseMsg();
        Integer statusAfter = OrderConstant.ORDER_AUDIT_FROZEN;//初始化冻结
        Integer sourceType = OrderConstant.AUDIT_ORDER_FROZEN;//初始化订单修改备注
        List<OrderAffiliateModel> dataList = orderAffiliateModelMapper.getOrderAffiliateList(orderInfoBean.getId());
        HrostOrUnfreezeOrderRTS hrostOrUnfreezeOrderRTS = new HrostOrUnfreezeOrderRTS();
        if(dataList.isEmpty()){//为空 则新增
            //添加记录订单冻结表orderAffiliate 状态 0：未冻结  1：已冻结',
            OrderAffiliateModel orderAffiliateModel = new OrderAffiliateModel();
            orderAffiliateModel.setCreateBy(SessionUtils.getSysUserSession().getId());
            hrostOrUnfreezeOrderRTS.setOrderId(orderInfoBean.getId());
            hrostOrUnfreezeOrderRTS.setOrderFrozenState(1);
            hrostOrUnfreezeOrderRTS.setGoodsId(orderInfoBean.getGoodsId());
            hrostOrUnfreezeOrderRTS.setUserId(SessionUtils.getSysUserSession().getId());//创建人
            orderFeignService.frostOrUnfreezeOrder(hrostOrUnfreezeOrderRTS);
        }
        else{
            //修改之前，校验版本号
            if(dataList.get(0).getVersion() == orderInfoBean.getOrderAffiliateVersion()){
                OrderAffiliateModel affiliateModel = dataList.get(0);
                //flag标记是冻结还是解冻 0冻结 1解冻
                if(orderInfoBean.getFlag() == 0){
                    //冻结 1
                    hrostOrUnfreezeOrderRTS.setOrderFrozenState(1);
                    statusAfter = OrderConstant.ORDER_AUDIT_FROZEN;
                    sourceType = OrderConstant.AUDIT_ORDER_REMARK;
                }
                else{
                    //解冻 2
                    hrostOrUnfreezeOrderRTS.setOrderFrozenState(0);
                    statusAfter = OrderConstant.AUDIT_MODIFY;
                    sourceType = OrderConstant.AUDIT_ORDER_FROZEN;
                }
                hrostOrUnfreezeOrderRTS.setOrderId(orderInfoBean.getId());
                hrostOrUnfreezeOrderRTS.setGoodsId(orderInfoBean.getGoodsId());
                hrostOrUnfreezeOrderRTS.setUserId(SessionUtils.getSysUserSession().getId());//修改人
                try{
                    FrostOrUnfreezeOrderVRU frostOrUnfreezeOrderVRU = orderFeignService.frostOrUnfreezeOrder(hrostOrUnfreezeOrderRTS);
                }catch (Exception e){
                    e.printStackTrace();
                }


            }
            else{
                msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
                msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                return msg;
            }
        }
        try { // todo catch 去掉 handerexception 能拦截到这种异常
        //操作人信息写日志
        OrderAuditHistoryModel orderAuditHistoryModel = new OrderAuditHistoryModel();
        orderAuditHistoryModel.setSourceType(sourceType);
        orderAuditHistoryModel.setSourceId(orderInfoBean.getId());
        orderAuditHistoryModel.setStatusAfter(statusAfter);
        orderAuditHistoryModel.setRemark(orderInfoBean.getFrozenRemark());
        orderAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        orderAuditHistoryModel.setCreateTime(DateUtil.now());
        orderAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
        orderAuditHistoryModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        orderAuditHistoryModelMapper.insert(orderAuditHistoryModel);
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        TopJetLog.info("审核日志记录完成:"+orderInfoBean.getId());
        } catch (Exception e) {
            TopJetLog.info("审核日志记录异常:"+orderInfoBean.getId());
        }
        return msg;
    }

    /**
     *获取司机运行轨迹
     */
    public List<UserGpsInfoHistoryModel> getRunninnTrack(OrderInfoBean orderInfoBean){
        return  orderInfoModelMapper.getRunninnTrack(orderInfoBean) ;
    }
    /**
     *获取订单状态轨迹
     */
    public List<OrderParameterInfoModel> getOrderTrack(Integer orderId){
        return  orderInfoModelMapper.getOrderTrack(orderId) ;
    }
}

package com.topjet.manage.service.impl;

import com.topjet.constants.BonusAuditStatus;
import com.topjet.constants.CommonConstant;
import com.topjet.constants.GoodsConstants;
import com.topjet.constants.OrderConstant;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.mapper.readMapper.*;
import com.topjet.manage.mapper.writeMapper.OrderBonusInfoModelMapper;
import com.topjet.manage.service.BlockedBonusInfoService;
import com.topjet.manage.service.OrderBonusInfoService;
import com.topjet.tool.common.util.CityUtil;
import com.topjet.tool.common.util.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-28 13:24
 */

@Service
@Transactional
public class OrderBonusInfoServiceImpl implements OrderBonusInfoService{
    private static Logger log = LoggerFactory.getLogger(OrderBonusInfoServiceImpl.class);
    @Autowired
    private OrderInfoModelEMapper orderInfoModelEMapper;
    @Autowired
    private GoodsDetailInfoModelEMapper goodsDetailInfoModelEMapper;
    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
    @Autowired
    private BlackListInfoModelEMapper blackListInfoModelEMapper;
    @Autowired
    private BlockedBonusInfoService blockedBonusInfoService;
    @Autowired
    private OrderBonusInfoModelEMapper orderBonusInfoModelEMapper;
    @Autowired
    private BonusSettingModelEMapper bonusSettingModelEMapper;
    @Autowired
    private OrderBonusInfoModelMapper orderBonusINfoModeMapper;


    @Override
    public boolean verifyIsOrderBonus(String orderNo, Integer bonusType) {
        boolean flag = true;
        String msg = "";
        OrderInfoModel orderInfoModel = orderInfoModelEMapper.selectOrderByOrderNo(Integer.parseInt(orderNo));
//        GoodsInfoModel goodsInfoModel = goodsInfoModelEMapper.selectByPrimaryKey(orderInfoModel.getGoodsId());
        if(orderInfoModel == null){
            return false;
        }
        GoodsDetailInfoModel goodsDetailInfoModel = goodsDetailInfoModelEMapper.selectGoodsDetailByOrderId(orderInfoModel.getGoodsId());

        //检查司机货主双方身份是否相同
        UserInfoModel owner = userInfoModelEMapper.selectByPrimaryKey(orderInfoModel.getOwnerId());
        UserInfoModel driver = userInfoModelEMapper.selectByPrimaryKey(orderInfoModel.getDriverId());

        if(owner ==null || driver ==null){
            return false;
        }

        BlackListInfoModel blackListInfoModel1 = blackListInfoModelEMapper.selectBlackListInfoByCretirea(orderInfoModel.getDriverId(), Integer.parseInt(CommonConstant.BLACK_LIST_TYPE_TRANSPORT_FEE_BONUS));
        BlackListInfoModel blackListInfoModel2 = blackListInfoModelEMapper.selectBlackListInfoByCretirea(orderInfoModel.getOwnerId(), Integer.parseInt(CommonConstant.BLACK_LIST_TYPE_TRANSPORT_FEE_BONUS));

        if(owner.getUserType().equals(driver.getUserType())){
            //交易双方身份相同，不发放补贴，同时在补贴屏蔽表中插入一条记录

            msg = "同类型不补贴 : 发货方类型=" + this.conversionUserType(owner.getUserType()) + "  与承运方类型=" + this.conversionUserType(driver.getUserType()) + "相同";
            log.info(msg);
            String blockedType = OrderConstant.ORDER_BONUS_TYPE_TRANSPORT.equals(bonusType) ? GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS : GoodsConstants.BLOCKED_LIST_TYPE_AGENCY_FEE_BONUS;
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_USER, blockedType,msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getDriverId(), GoodsConstants.BLOCKED_BONUS_REASON_USER, blockedType,msg);
            return false;

        }

        if(CityUtil.filterLevel2(CityUtil.convertToString(goodsDetailInfoModel.getDepartCityId()), CityUtil.convertToString(goodsDetailInfoModel.getDestinationCityId()))){
            //校验是否符合同城不补贴条件
            msg = "同城不补贴 : 发货地址=" + goodsDetailInfoModel.getDepart1() + goodsDetailInfoModel.getDepart2() + "  与收货地址=" + goodsDetailInfoModel.getDestination1() + goodsDetailInfoModel.getDestination2() + "相同";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_SITE, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }

        if(blackListInfoModel1 != null || blackListInfoModel2 != null){
            //校验是否是在补贴黑名单里面
            msg = "黑名单不补贴 : 用户id=" + (blackListInfoModel1.getUserId()==null?blackListInfoModel2.getUserId():blackListInfoModel1.getUserId())+ "在黑名单中";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }

        //确认提货没有gps地址不补贴
        List<OrderParameterInfoModel> orderParameterInfoModel = orderInfoModelEMapper.selectOrderParameterInfoByOrderNo(Integer.parseInt(orderNo), 7);
        if(orderParameterInfoModel == null || orderParameterInfoModel.size()<1){
            msg = "提货时没有GPS不补贴";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }else if(!(orderParameterInfoModel.get(0).getCityId()+"").substring(0,4).equals(goodsDetailInfoModel.getDepart2())){
            //提货地与订单发货地不同不补贴
            msg = "提货地与订单发货地不同不补贴";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }

        //卸货时没有gps地址不补贴
        List<OrderParameterInfoModel> orderParameterInfoModel1 = orderInfoModelEMapper.selectOrderParameterInfoByOrderNo(Integer.parseInt(orderNo), 8);
        if(orderParameterInfoModel1 == null || orderParameterInfoModel1.size()<1){
            msg = "卸货时没有gps地址不补贴";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }else if(orderParameterInfoModel1.get(0).getUserId().equals(owner.getId())){
            //货主签收不补贴
            msg = "货主签收订单不补贴";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }else if((orderParameterInfoModel1.get(0).getCityId()+"").substring(0,4).equals(goodsDetailInfoModel.getDestination2())){
            //签收时卸货地与订单目的地不同不补贴
            msg = "签收时卸货地与订单目的地不同不补贴";
            log.info("订单："+orderInfoModel.getOrderNo()+"  "+msg);
            blockedBonusInfoService.insterBlockedBonus(orderInfoModel, orderInfoModel.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_BLACK, GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS, msg);
            return false;
        }
        return flag;
    }

    @Override
    public OrderBonusInfoModel createOrderBonusInfo(String orderNo,Integer bonusType) {
        OrderInfoModel orderInfoModel = orderInfoModelEMapper.selectOrderByOrderNo(Integer.parseInt(orderNo));
        OrderBonusInfoModel orderBonusInfoModel = new OrderBonusInfoModel();
        orderBonusInfoModel.setCreateTime(DateUtil.now());
        orderBonusInfoModel.setDeleted(0);
        orderBonusInfoModel.setOrderId(orderInfoModel.getId());
        orderBonusInfoModel.setVersion(1);
        orderBonusInfoModel.setDriverId(orderInfoModel.getDriverId());
        orderBonusInfoModel.setOwnerId(orderInfoModel.getOwnerId());
        orderBonusInfoModel.setAuditStatus(BonusAuditStatus.BONUS_NOT＿AUDIT);
        orderBonusInfoModel.setAuditProcess(BonusAuditStatus.BONUS_AUDIT_FIRST_PROCESS);
        orderBonusInfoModel.setType(1);


        int ownerCount = this.getOrderBonusCountByToday(orderInfoModel.getOwnerId());
        int driverCount = this.getOrderBonusCountByToday(orderInfoModel.getDriverId());

        if(OrderConstant.ORDER_BONUS_TYPE_TRANSPORT.equals(bonusType)){
            int sameTodayCount = this.getSameUserOrderBonusCountByToday(orderInfoModel.getOwnerId(), orderInfoModel.getDriverId(),OrderConstant.ORDER_BONUS_TYPE_TRANSPORT);
            int sameMonthCount = this.getSameUserOrderBonusCountByMonth(orderInfoModel.getOwnerId(), orderInfoModel.getDriverId(),OrderConstant.ORDER_BONUS_TYPE_TRANSPORT);
            BonusSettingModel bonusSettingModel1 = bonusSettingModelEMapper.selectTransportFeeSetting(1);
            BonusSettingModel bonusSettingModel2 = bonusSettingModelEMapper.selectTransportFeeSetting(2);
            BonusSettingModel bonusSettingModel3 = bonusSettingModelEMapper.selectTransportFeeSetting(3);


            BigDecimal bonusFee = new BigDecimal("0.00");
            BigDecimal tuoGuanFee = new BigDecimal("0.00");
            //每个相同用户交易次数低于4次才能进行补贴
            if(new BigDecimal(sameMonthCount+"").compareTo(bonusSettingModel3.getBonusScopeMax())<0){
                if(bonusSettingModel1.getBonusType()==1 ){
                    ////获取托管运费补贴部分
                    if(orderInfoModel.getAheadFeeStatus()==3){
                        tuoGuanFee = tuoGuanFee.add(orderInfoModel.getAheadFee().multiply(bonusSettingModel1.getEveryTimeAmount()));
                    }
                    if(orderInfoModel.getDeliveryFeeStatus()==3){
                        tuoGuanFee = tuoGuanFee.add(orderInfoModel.getDeliveryFee().multiply(bonusSettingModel1.getEveryTimeAmount()));
                    }
                    //比较托管费用是否比补贴上限大，取小者
                    if(tuoGuanFee.compareTo(bonusSettingModel1.getBonusScopeMax())>0){
                        tuoGuanFee = bonusSettingModel1.getBonusScopeMax();
                    }
                    bonusFee = bonusFee.add(tuoGuanFee);

                }
                //校验该用户是否首次交易
                if (orderBonusInfoModelEMapper.selectOrderBonusRecord(1, orderInfoModel.getOwnerId())==0){
                    bonusFee = bonusFee.add(bonusSettingModel2.getEveryTimeAmount());
                }

            }
            orderBonusInfoModel.setOwnerAmount(bonusFee);
            if(bonusFee.compareTo(BigDecimal.ZERO)>0){
                orderBonusINfoModeMapper.insert(orderBonusInfoModel);
            }

        }
        return orderBonusInfoModel;
    }


    /**
     * 查找用户当天已补贴的次数
     *
     * @param userId
     * @return
     */
    private Integer getOrderBonusCountByToday(Integer userId) {

        int countByOwner =orderBonusInfoModelEMapper.countOwnerOrderBonusInfo(userId,DateUtil.getStartTime(),DateUtil.getEndTime());
        int countByDriver =orderBonusInfoModelEMapper.countDriverOrderBonusInfo(userId,DateUtil.getStartTime(),DateUtil.getEndTime());
        return countByOwner + countByDriver;
    }


    /**
     * 查找相同用户当天已补贴的次数
     * @param ownerId
     * @param driverId
     * @return
     */
    private Integer getSameUserOrderBonusCountByToday(Integer ownerId, Integer driverId,Integer type) {
        int countByOwner = orderBonusInfoModelEMapper.countSameUserOrderBonusCountByToday1(ownerId,driverId,type,DateUtil.getStartTime(),DateUtil.getEndTime());
        int countByDriver = orderBonusInfoModelEMapper.countSameUserOrderBonusCountByToday2(ownerId,driverId,type,DateUtil.getStartTime(),DateUtil.getEndTime());
        return countByOwner + countByDriver;
    }

    /**
     * 查找相同用户当月已补贴的次数
     * @param ownerId
     * @param driverId
     * @return
     */
    private Integer getSameUserOrderBonusCountByMonth(Integer ownerId, Integer driverId,Integer type) {
        int countByOwner = orderBonusInfoModelEMapper.countSameUserOrderBonusCountByToday1(ownerId,driverId,type,DateUtil.getMonthStartTime(),DateUtil.getMonthEndTime());
        int countByDriver = orderBonusInfoModelEMapper.countSameUserOrderBonusCountByToday2(ownerId,driverId,type,DateUtil.getMonthStartTime(),DateUtil.getMonthEndTime());
        return countByOwner + countByDriver;
    }


    /**
     * 校验 相同双方 当月补贴次数限制
     */
    private boolean sameUserBounsMonthVerify(OrderInfoModel order, int sameMonthCount, Integer sameMonthUpper,Integer bonusType) {
        String blockedType = OrderConstant.ORDER_BONUS_TYPE_TRANSPORT.equals(bonusType) ? GoodsConstants.BLOCKED_LIST_TYPE_TRANSPORT_FEE_BONUS : GoodsConstants.BLOCKED_LIST_TYPE_AGENCY_FEE_BONUS;
        if (sameMonthCount >= sameMonthUpper) {
            UserInfoModel ownerUser = userInfoModelEMapper.selectByPrimaryKey(order.getOwnerId());
            UserInfoModel driverUser = userInfoModelEMapper.selectByPrimaryKey(order.getDriverId());
            String msg = "相同用户 补贴超过当月上限: 货主:" + ownerUser.getUsername() + " 司机:" + driverUser.getUsername() + "  当月相同用户补贴" + sameMonthCount + "次,已超过相同用户当日上限" + sameMonthUpper + "次!";
            blockedBonusInfoService.insterBlockedBonus(order, order.getOwnerId(), GoodsConstants.BLOCKED_BONUS_REASON_COUNT, blockedType, msg);
            blockedBonusInfoService.insterBlockedBonus(order, order.getDriverId(), GoodsConstants.BLOCKED_BONUS_REASON_COUNT, blockedType, msg);
            return true;
        }
        return false;
    }



    private String conversionUserType(Integer userType){
        String typeString = "";
        if("1".equals(userType)){
            typeString = "司机";
        } else {
            typeString = "货主";
        }
        return typeString;
    }
}

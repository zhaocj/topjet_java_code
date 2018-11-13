package com.topjet.manage.service.impl;

import com.netflix.discovery.converters.Auto;
import com.topjet.common.SessionUtils;
import com.topjet.common.constants.BillConstant;
import com.topjet.common.constants.GoodsConstants;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.util.DateStyle;
import com.topjet.common.util.DateUtil;
import com.topjet.manage.constants.BonusAuditStatus;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.constants.OrderConstant;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.domain.bean.OrderBonusBean;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.OrderBonusListQuery;
import com.topjet.manage.mapper.readMapper.*;
import com.topjet.manage.mapper.writeMapper.BlockedBonusInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.BonusBillInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.OrderAuditHistoryModelMapper;
import com.topjet.manage.mapper.writeMapper.OrderBonusInfoModelMapper;
import com.topjet.manage.service.*;
import com.topjet.tool.common.util.CityUtil;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-10-16 19:43
 */

@Service
@Transactional
public class OrderBonusInfoServiceImpl implements OrderBonusInfoService{
    private static Logger log = LoggerFactory.getLogger(OrderBonusInfoServiceImpl.class);


    @Autowired
    private OrderInfoModelEMapper orderInfoModelEMapper;

    @Autowired
    private BlockedBonusInfoService blockedBonusInfoService;

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;

    @Autowired
    private GoodsInfoModelEMapper goodsInfoModelEMapper;

    @Autowired
    private GoodsDetailInfoModelEMapper goodsDetailInfoModelEMapper;

    @Autowired
    private OrderBonusInfoModelMapper orderBonusInfoModelMapper;

    @Autowired
    private OrderBonusInfoModelEMapper orderBonusInfoModelEMapper;

    @Autowired
    private SysUserService sysUserService;

    @Autowired
    private TaskAssignService taskAssignService;

    @Autowired
    private CityService cityService;

    @Autowired
    private BlackListInfoModelEMapper blackListInfoModelEMapper;

    @Autowired
    private BonusBillInfoModelEMapper bonusBillInfoModelEMapper;

    @Autowired
    private BonusBillInfoModelMapper bonusBillInfoModelMapper;

    @Autowired
    private OrderAuditHistoryModelMapper orderAuditHistoryModelMapper;

    @Autowired
    private OrderAuditHistoryModelEMapper orderAuditHistoryModelEMapper;


    @Override
    public PaginationBean listOrderBonusInfo(OrderBonusListQuery query) {

        if(query.getAuditProcess() != null && query.getAuditStatus().equals("1")){
            List<Integer> statusList = new ArrayList<Integer>();
            switch (Integer.parseInt(query.getAuditProcess())){
                case 1 :
                    statusList.add(0);
                    break;
                case 2 :
                    statusList.add(1);
                    statusList.add(3);
                    break;
                case 3 :
                    statusList.add(4);
                    statusList.add(5);
                    break;
            }
            query.setStatusList(statusList);
        }else if(query.getAuditProcess() != null && query.getAuditStatus().equals("2")){
            switch (Integer.parseInt(query.getAuditProcess())){
                case 1 :
                    query.setAuditStatus("1");
                    break;
                case 2 :
                    query.setAuditStatus("4");
                    break;
                case 3 :
                    query.setAuditStatus("6");
                    break;
            }
        }else if(query.getAuditProcess() != null && query.getAuditStatus().equals("3")){
            query.setAuditStatus("2");
        }else if(query.getAuditProcess() != null && query.getAuditStatus().equals("4")){
            switch (Integer.parseInt(query.getAuditProcess())){
                case 1 :
                    query.setAuditStatus("3");
                    break;
                case 2 :
                    query.setAuditStatus("5");
                    break;
                case 3 :
                    query.setAuditStatus("7");
                    break;
            }
        }

        PaginationBean page = new PaginationBean();
        if(StringUtils.isNotBlank(query.getDepart1()))query.setDepart1(cityService.getCityModel(query.getDepart1()).getCityFullName());
        if(StringUtils.isNotBlank(query.getDepart2()))query.setDepart2(cityService.getCityModel(query.getDepart2()).getCityFullName());
        if(StringUtils.isNotBlank(query.getDestination1()))query.setDestination1(cityService.getCityModel(query.getDestination1()).getCityFullName());
        if(StringUtils.isNotBlank(query.getDestination2()))query.setDestination2(cityService.getCityModel(query.getDestination2()).getCityFullName());
        page.setTotal(orderBonusInfoModelEMapper.countOrderBonusInfo(query));
        page.setRows(orderBonusInfoModelEMapper.listOrderBonusInfo(query));
        return page;
    }

    @Override
    public PaginationBean listAuditOrderBonusInfo(OrderBonusBean orderBonusBean) {
        PaginationBean page = new PaginationBean();
        //因3.0表结构变动，现对审核状态筛选做出修改
        if(orderBonusBean.getFirstAuditStatus() !=null && orderBonusBean.getFirstAuditStatus().equals("0")){
            orderBonusBean.setFirstAuditStatus(null);
            List<Integer> statusList = new ArrayList<Integer>();
            statusList.add(0);
            orderBonusBean.setStatusList(statusList);
        }
        if(orderBonusBean.getSecondAuditStatus() != null && orderBonusBean.getSecondAuditStatus().equals("0")){
            orderBonusBean.setSecondAuditStatus(null);
            List<Integer> statusList = new ArrayList<Integer>();
            statusList.add(3);
            statusList.add(4);
            orderBonusBean.setStatusList(statusList);
        }

        List<OrderBonusBean> dataList = orderBonusInfoModelEMapper.listAuditOrderBonusInfo(orderBonusBean);
        for(int i = 0;i<dataList.size();i++){
            long nd = 1000 * 24 * 60 * 60; //一天的毫秒数
            long nh = 1000 * 60 * 60; // 一小时的毫秒数
            long nm = 1000*60;//一分钟的毫秒数
            long diff;//获得两个时间的毫秒时间差异
            if(dataList.get(i).getTransportTimes() != null && dataList.get(i).getHaulageTime() != null){
                diff = dataList.get(i).getHaulageTime().getTime()-dataList.get(i).getTransportTimes().getTime();
                long day = diff/nd; //计算差多少天
                dataList.get(i).setTransportTime((int)day);
                long hour = diff%nd/nh;// 计算差多少小时
                dataList.get(i).setHour((int)hour);
                long min = diff%nd%nh/nm;//计算差多少分钟
                dataList.get(i).setSecond((int)min);
//                BigDecimal distance = dataList.get(i).getDistance().divide(new BigDecimal("1000"),1,BigDecimal.ROUND_HALF_UP);
                BigDecimal distance = new BigDecimal("0.00");
                dataList.get(i).setDistance(distance);
            }else{
                dataList.get(i).setTransportTime(0);
                dataList.get(i).setHour(0);
                dataList.get(i).setSecond(0);
//                BigDecimal distance = dataList.get(i).getDistance().divide(new BigDecimal("1000"),1,BigDecimal.ROUND_HALF_UP);
                BigDecimal distance = new BigDecimal("0.00");
                dataList.get(i).setDistance(distance);
            }
        }
        page.setRows(dataList);
        page.setTotal(orderBonusInfoModelEMapper.countAuditOrderBonusInfo(orderBonusBean));
        return page;
    }

    @Override
    public OrderBonusBean auditOrderBonus(OrderBonusBean orderBonusBean) throws TopjetExceptionHandler {
        StringBuffer messageBuffer = new StringBuffer();
        OrderBonusBean resultOrderBonusBean = new OrderBonusBean();
        OrderBonusInfoModel orderBonusInfoModel = orderBonusInfoModelEMapper.selectOrderBonusInfoById(orderBonusBean.getId(), CommonConstant.DB_RECORD_DELETED_STATUS_FALSE, orderBonusBean.getOrderBonusVersion());
        orderBonusInfoModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
        orderBonusInfoModel.setUpdateTime(DateUtil.now());
        if(orderBonusInfoModel != null){
            Integer taskType = TaskConstants.FRIGHT_FIRST_AUDIT;
            if(StringUtils.isNotBlank(orderBonusBean.getFirstAuditStatus())){
                orderBonusInfoModel.setAuditStatus(Integer.parseInt(orderBonusBean.getFirstAuditStatus()));
                orderBonusInfoModel.setAuditProcess(orderBonusBean.getFirstAuditStatus().equals("2")?1:2);
                int statusAfter = 0;
                if(orderBonusBean.getFirstAuditStatus().equals("1")){
                    statusAfter  = 4;//审核通过
                }else if(orderBonusBean.getFirstAuditStatus().equals("2")){
                    statusAfter  = 3;//待定
                }else{
                    statusAfter = 5;//驳回
                }
                //记录审核历史到orderAuditHistory表
                this.saveOrderAuditHistory(3,orderBonusInfoModel.getId(),statusAfter,orderBonusBean.getFirstAuditRemark());
            }else if(StringUtils.isNotBlank(orderBonusBean.getSecondAuditStatus())){
                taskType = TaskConstants.FRIGHT_SECOND_AUDIT;
                orderBonusInfoModel.setAuditProcess(3);
                orderBonusInfoModel.setAuditStatus(Integer.parseInt(orderBonusBean.getSecondAuditStatus()));
                //记录审核历史到orderAuditHistory表
                this.saveOrderAuditHistory(4,orderBonusInfoModel.getId(),Integer.parseInt(orderBonusBean.getSecondAuditStatus())==4?4:5,orderBonusBean.getSecondAuditRemark());
            }
            Integer update = orderBonusInfoModelMapper.update(orderBonusInfoModel);

            //一审二审更新taskInfo任务数量
            if(StringUtils.isNotBlank(orderBonusBean.getFirstAuditStatus()) || StringUtils.isNotBlank(orderBonusBean.getSecondAuditStatus())){
                try{
                    List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();
                    if(!taskUser.isEmpty()){
                        taskAssignService.updateTasks(taskUser.get(0).getSysUserId(),taskType,orderBonusInfoModel.getId());
                    }else{
                        taskAssignService.updateTasks(0,taskType,orderBonusInfoModel.getId());
                    }
                }catch (Exception e){
                    log.error("更新审核任务数量异常:"+e.getMessage() +e.getClass().getName()+"orderBonusInfoModel ID:"+orderBonusInfoModel.getId());
                }
            }
            if(StringUtils.isNotBlank(orderBonusBean.getThirdAuditStatus())){
                orderBonusInfoModel.setAuditProcess(3);
                orderBonusInfoModel.setAuditStatus(Integer.parseInt(orderBonusBean.getThirdAuditStatus()));
                //判断该车主或者司机是否为补贴黑名单用户
                BlackListInfoModel driverBlackInfo = blackListInfoModelEMapper.selectBlackListInfoByUserId(orderBonusBean.getDriverId());
                BlackListInfoModel ownerBlackInfo = blackListInfoModelEMapper.selectBlackListInfoByUserId(orderBonusBean.getOwnerId());
                if(driverBlackInfo != null && ownerBlackInfo != null){
                    messageBuffer.append("补贴订单号为" + orderBonusBean.getBonusOrderId() + ":补贴用户是黑名单用户!");
                    resultOrderBonusBean.setReturnMessage(messageBuffer.toString());
                    resultOrderBonusBean.setFailCount(1);
                    return resultOrderBonusBean;
                }else{
                    Integer update1 = orderBonusInfoModelMapper.update(orderBonusInfoModel);
                    if(orderBonusInfoModel.getOwnerAmount() != null && orderBonusInfoModel.getOwnerAmount().compareTo(BigDecimal.ZERO) >0 && orderBonusBean.getThirdAuditStatus().equals(BonusAuditStatus.FREIGHT_BONUS_THIRD_AUDIT_PASS+"")){
                        orderBonusBean.setThirdAuditRemark("审核通过");
                        //生成子账单信息
                        BonusBillInfoModel bonusBillInfoModel = new BonusBillInfoModel();
                        bonusBillInfoModel.setBillNo(getBillNo(2));
                        bonusBillInfoModel.setStatus(BillConstant.BILL_STATUS_NOT_PAID);
                        bonusBillInfoModel.setCreateTime(DateUtil.now());
                        bonusBillInfoModel.setDeleted(0);
                        bonusBillInfoModel.setSourceId(orderBonusInfoModel.getId());
                        bonusBillInfoModel.setBillType(2);
                        bonusBillInfoModel.setPayeeId(orderBonusBean.getOwnerId());
                        bonusBillInfoModel.setAmount(orderBonusInfoModel.getOwnerAmount());
                        bonusBillInfoModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                        bonusBillInfoModelMapper.insert(bonusBillInfoModel);
                        //生成父账单信息
                        Map<String,Object> map = new HashMap<String,Object>();
                        map.put("billType",2);
                        map.put("deleted",0);
                        map.put("status",BillConstant.BILL_STATUS_NOT_PAID);
                        map.put("payeeId",orderBonusBean.getOwnerId());
                        List<BonusBillInfoModel> bonusBillInfoModels = bonusBillInfoModelEMapper.selectListByParam(map);
                        if(bonusBillInfoModels != null && bonusBillInfoModels.size()>0){
                            //存在账单就修改父账单金额
                            BonusBillInfoModel bonusBillInfoModel1 = bonusBillInfoModels.get(0);
                            bonusBillInfoModel1.setAmount(bonusBillInfoModel1.getAmount().add(orderBonusInfoModel.getOwnerAmount()));
                            bonusBillInfoModel1.setUpdateTime(DateUtil.now());
                            bonusBillInfoModel1.setUpdateBy(SessionUtils.getSysUserSession().getId());
                            bonusBillInfoModelMapper.update(bonusBillInfoModel1);
                        }else{
                            //不存在就生成新的父账单
                            BonusBillInfoModel bonusParentBillInfoModel = new BonusBillInfoModel();
                            bonusParentBillInfoModel.setBillNo(getBillNo(1));
                            bonusParentBillInfoModel.setStatus(BillConstant.BILL_STATUS_NOT_PAID);
                            bonusParentBillInfoModel.setCreateTime(DateUtil.now());
                            bonusParentBillInfoModel.setDeleted(0);
                            bonusParentBillInfoModel.setSourceId(0);
                            bonusParentBillInfoModel.setBillType(2);
                            bonusParentBillInfoModel.setPayeeId(orderBonusBean.getOwnerId());
                            bonusParentBillInfoModel.setAmount(orderBonusInfoModel.getOwnerAmount());
                            bonusParentBillInfoModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                            bonusBillInfoModelMapper.insert(bonusParentBillInfoModel);
                            bonusBillInfoModel.setParentId(bonusParentBillInfoModel.getId());
                            bonusBillInfoModelMapper.update(bonusBillInfoModel);
                        }
                    }else{
                        orderBonusBean.setThirdAuditRemark("审核不通过");
                    }
                    //记录审核历史到orderAuditHistory表
                    this.saveOrderAuditHistory(5,orderBonusInfoModel.getId(),Integer.parseInt(orderBonusBean.getThirdAuditStatus())==6?4:5,orderBonusBean.getThirdAuditRemark());
                }
            }
        }else{
            throw new  TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(),ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return resultOrderBonusBean;
    }

    /**
     * 生成运费补贴账单号 type=1 父账单    type=2子账单
     * @param type
     * @return
     */
    private String getBillNo(Integer type) {
        String date = DateUtil.DateToString(DateUtil.now(), DateStyle.YYYYMMDDHHMM);
        String random = RandomStringUtils.random(3, false, true);
        String resultStr = "";
        if(type==1){
            resultStr = "PHYA"+date+random;
        }else{
            resultStr ="PHYB"+ date + random;
        }
        return resultStr;
    }


    /**
     * 保存审核历史记录
     * @param sourceType
     * @param sourceId
     * @param statusAfter
     * @param remark
     * @return
     */
    private Integer saveOrderAuditHistory(Integer sourceType,Integer sourceId,Integer statusAfter,String remark){
         OrderAuditHistoryModel orderAuditHistoryModel = new OrderAuditHistoryModel();
        orderAuditHistoryModel.setSourceType(sourceType);
        orderAuditHistoryModel.setSourceId(sourceId);
        orderAuditHistoryModel.setStatusAfter(statusAfter);
        orderAuditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        orderAuditHistoryModel.setCreateTime(DateUtil.now());
        orderAuditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
        orderAuditHistoryModel.setRemark(remark);
        orderAuditHistoryModel.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        return orderAuditHistoryModelMapper.insert(orderAuditHistoryModel);

    }

}

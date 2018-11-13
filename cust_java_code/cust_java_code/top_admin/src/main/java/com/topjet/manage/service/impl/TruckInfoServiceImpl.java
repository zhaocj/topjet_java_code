package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.basic.TruckFeignService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.constant.PushConstant;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateDriverTruckInfoRTS;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateTurckRTS;
import com.topjet.cloud.manage.mq.message.AppAction;
import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.cloud.manage.service.basic.bean.GeturlRTS;
import com.topjet.cloud.manage.service.basic.bean.SendSmsRTS;
import com.topjet.cloud.manage.service.basic.bean.SendSmsVRU;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.common.util.DateUtil;
import com.topjet.config.MessageConfig;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.domain.bean.PaginationBean;
import com.topjet.manage.domain.bean.TruckDetailBean;
import com.topjet.manage.domain.bean.TruckVerifyBean;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.TruckListVerifyQuery;
import com.topjet.manage.mapper.readMapper.*;
import com.topjet.manage.mapper.writeMapper.BlockedBonusInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.TruckAuditHistoryModelMapper;
import com.topjet.manage.service.TaskAssignService;
import com.topjet.manage.service.TruckInfoService;
import com.topjet.manage.service.TruckService;
import com.topjet.manage.service.UserInfoService;
import com.topjet.tool.common.constant.SystemConstant;
import com.topjet.user.constant.AuditHistoryConstant;
import com.topjet.user.constant.UserStatus;
import com.topjet.util.DateUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-15 13:57
 */

@Service
@Transactional
public class TruckInfoServiceImpl implements TruckInfoService{
    private static Logger log = LoggerFactory.getLogger(TruckInfoServiceImpl.class);

    @Autowired
    private DriverTruckInfoModelEMapper driverTruckInfoModelEMapper;

    @Autowired
    private TruckService truckService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private TruckAuditHistoryModelEMapper truckAuditHistoryModelEMapper;

    @Autowired
    private BasicFeignService basicFeignService;

    @Autowired
    private TaskAssignService taskAssignService;

    @Autowired
    private TruckAuditHistoryModelMapper truckAuditHistoryModelMapper;

    @Autowired
    private RecommendRelationshipInfoModelEMapper recommendRelationshipInfoModelEMapper;

    @Autowired
    private BlockedBonusInfoModelMapper blockedBonusInfoModelMapper;

    @Autowired
    private OwnerTruckInviteInfoModelEMapper ownerTruckInviteInfoModelEMapper;

    @Autowired
    private OwnerTruckInfoModelEMapper ownerTruckInfoModelEMapper;

    @Autowired
    private TruckFeignService truckFeignService;

    @Autowired
    private MessageConfig messageConfig;




    @Override
    public PaginationBean listTruckListVerify(TruckListVerifyQuery query) {
        log.info("开始查询待审核车辆列表");
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();

        boolean taskFlag = false;
        for (TaskBucketInfoModel taskBucketInfoModel : taskUser) {
            if(taskBucketInfoModel.getType().equals(TaskConstants.USER_TRUCK_AUDIT)){
                taskFlag = true;
                break;
            }
        }
        if(taskFlag){
            query.setFlag("1");
            query.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }
        query.setAuditStatus("3");
        PaginationBean page = new PaginationBean();
        page.setRows(driverTruckInfoModelEMapper.listTruckVerifyList(query));
        page.setTotal(driverTruckInfoModelEMapper.countTruckVerifyList(query));
        return page;
    }

    @Override
    public TruckDetailBean getTruckDetil(Integer id) {

        TruckDetailBean truckDetailBean = new TruckDetailBean();

        TruckInfoModel truckInfoModel = truckService.getTruckById(id);
        if(truckInfoModel != null){
            truckDetailBean.setId(truckInfoModel.getId());

            UserInfoModel userInfoModel = userInfoService.selectByPrimaryKey(truckInfoModel.getOwnerId());
            if(userInfoModel != null){
                truckDetailBean.setName(userInfoModel.getUsername());
                truckDetailBean.setMobile(userInfoModel.getMobile());
                truckDetailBean.setUserCreateTime(DateUtils.getDisplayYMDHMS(userInfoModel.getCreateTime()));
            }
        }
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        paramMap.put("truckId",truckInfoModel.getId());
        List<DriverTruckInfoModel> driverTruckInfoModels = driverTruckInfoModelEMapper.selectListByParam(paramMap);
        if(driverTruckInfoModels != null && driverTruckInfoModels.size() >0){
            truckDetailBean.setAuditStatus(driverTruckInfoModels.get(0).getAuditStatus());
            paramMap.clear();
            paramMap.put("sourceType", AuditHistoryConstant.AUDIT_TRUCK);
            paramMap.put("sourceId",driverTruckInfoModels.get(0).getId());
            paramMap.put("statusAfter",driverTruckInfoModels.get(0).getAuditStatus()==2?1:2);
            List<TruckAuditHistoryModel> truckAuditHistoryModels = truckAuditHistoryModelEMapper.selectListByParam(paramMap);
            truckDetailBean.setOperatorRemark(truckAuditHistoryModels!=null&&truckAuditHistoryModels.size()>0?truckAuditHistoryModels.get(0).getRemark():"");
        }
        truckDetailBean.setTruckType(truckInfoModel.getTruckType());
        truckDetailBean.setTruckLength(truckInfoModel.getTruckLength());
        truckDetailBean.setTruckCreateTime(DateUtils.getDisplayYMDHMS(truckInfoModel.getCreateTime()));
        truckDetailBean.setPlateNo(truckInfoModel.getPlateNo1()+truckInfoModel.getPlateNo2()+truckInfoModel.getPlateNo3());
        truckDetailBean.setTruckColor(truckInfoModel.getPlateColor()+"");

        GeturlRTS geturlRTS = new GeturlRTS();
        geturlRTS.setKey(truckInfoModel.getTruckHeadImg());
        geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_TRUCK_PHOTO);
        truckDetailBean.setTruckPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
        geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_DRIVING_LICENSE);
        geturlRTS.setKey(truckInfoModel.getDriverLicenseImg());
        truckDetailBean.setDrivingLicensePhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
        return truckDetailBean;
    }

    @Override
    public ResultBaseMsg updateVerify(TruckVerifyBean bean) throws Exception {
        ResultBaseMsg msg = new ResultBaseMsg();
        TruckInfoModel truckInfoModel = truckService.getTruckById(bean.getId());

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("truckId",truckInfoModel.getId());
        List<DriverTruckInfoModel> driverTruckInfoList = driverTruckInfoModelEMapper.selectListByParam(paramMap);

        DriverTruckInfoModel driverTruckInfo = driverTruckInfoList.get(0);
        if (truckInfoModel == null) {
            msg.setStatus(ExceptionEnum.E_2.getStatus());
            msg.setMsg(ExceptionEnum.E_2.getMsg());
            return msg;
        }

        UpdateTurckRTS rts = new UpdateTurckRTS();
        rts.setPlateColor(bean.getTruckColor());
        rts.setPlateNo1(bean.getPlateNo().charAt(0)+"");
        rts.setPlateNo2(bean.getPlateNo().charAt(1)+"");
        rts.setPlateNo3(bean.getPlateNo().substring(2,6));
        rts.setTruckLength(bean.getTruckLength());
        rts.setTruckType(bean.getTruckType());
        rts.setTruckId(truckInfoModel.getId());
        truckFeignService.updateTruck(rts);

        if (driverTruckInfoList != null && driverTruckInfoList.size() > 0) {
            driverTruckInfo.setAuditStatus(bean.getAuditStatus());
            driverTruckInfo.setAuditRemark(bean.getOperatorRemark());
            UpdateDriverTruckInfoRTS rts1 = new UpdateDriverTruckInfoRTS();
            rts1.setAuditRemark(bean.getOperatorRemark());
            rts1.setAuditStatus(bean.getAuditStatus());
            rts1.setDriverTruckInfoId(driverTruckInfoList.get(0).getId());
            truckFeignService.updateDriverTruckInfo(rts1);

            try {
                List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                if (!taskUser.isEmpty()) {
                    taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.USER_TRUCK_AUDIT, driverTruckInfo.getId());
                }else{
                    taskAssignService.updateTasks(0, TaskConstants.USER_TRUCK_AUDIT, driverTruckInfo.getId());
                }
            }catch (Exception e){
                TopJetLog.error("更新用户车辆审核任务数量异常:"+e.getMessage() +e.getClass().getName()+"用户driverTruckInfo ID:"+driverTruckInfo.getId());
            }
        }
        TruckAuditHistoryModel auditHistoryModel=new TruckAuditHistoryModel();
        auditHistoryModel.setAuditName(SessionUtils.getSysUserSession().getNickName());
        auditHistoryModel.setCreateBy(SessionUtils.getSysUserSession().getId());
        auditHistoryModel.setCreateTime(DateUtil.now());
        auditHistoryModel.setSourceType(AuditHistoryConstant.AUDIT_TRUCK);
        auditHistoryModel.setSourceId(driverTruckInfo.getId());
        auditHistoryModel.setRemark(bean.getOperatorRemark());
        if(bean.getAuditStatus()==2){
            auditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_SUCCESS);
        }else if(bean.getAuditStatus()==4){
            auditHistoryModel.setStatusAfter(AuditHistoryConstant.AUDIT_FAILED);
        }
        truckAuditHistoryModelMapper.insert(auditHistoryModel);


        //处理要请熟车和推送短信
        UserInfoModel userModel=userInfoService.selectByPrimaryKey(driverTruckInfo.getDriverId());
        String title = bean.getAuditStatus()==2?messageConfig.getCarAuthPassTitle():messageConfig.getCarAuthFailTitle();
        if(bean.getAuditStatus()==2){
            //查询推荐关系，查询车牌认证次数
            List<RecommendRelationshipInfoModel> recommendRelationshipModelList = recommendRelationshipInfoModelEMapper.selectRecomRelByRecomedId(driverTruckInfo.getDriverId());
            if(recommendRelationshipModelList!=null && recommendRelationshipModelList.size()>0){
                RecommendRelationshipInfoModel recommendRelationshipModel =  recommendRelationshipModelList.get(0);
                List<DriverTruckInfoModel> listCount = this.countAuditPlateNo(bean.getPlateNo());
                int count5=0,count30=0;
                for(DriverTruckInfoModel driverTruck:listCount){
                    if(driverTruck.getId()==1){
                        count5 = driverTruck.getOrderCount();
                    }
                    if(driverTruck.getId()==2){
                        count30 = driverTruck.getOrderCount();
                    }
                }
                //在车辆认证通过的情况下车牌号重复次数一个月超过5次、总共超过30次）的加入到屏蔽补贴中，并注明被屏蔽补贴的原因（车辆重复次数超过限制）
                if( count5>5 || count30>30 ) {
                    UserInfoModel recommendedUser = userInfoService.selectByPrimaryKey(recommendRelationshipModel.getRecommendUserId());
                    BlockedBonusInfoModel blockedBonusInfoModel = new BlockedBonusInfoModel();
                    blockedBonusInfoModel.setOrderId(0);
                    blockedBonusInfoModel.setCreateTime(DateUtil.now());
                    blockedBonusInfoModel.setUserId(recommendedUser.getId());
                    blockedBonusInfoModel.setUserName(recommendedUser.getUsername());
                    blockedBonusInfoModel.setType(4); // 4: 推荐费补贴
                    blockedBonusInfoModel.setDeleted(0);
                    blockedBonusInfoModel.setVersion(1);
                    blockedBonusInfoModel.setRecommendationId(recommendRelationshipModel.getId());
                    if( count30>30 ){
                        blockedBonusInfoModel.setReason(10); //10车辆认证通过车牌号总共超过30次
                        blockedBonusInfoModel.setRemark("车牌总重复次数超过30次");
                    }else if(count5>5){
                        blockedBonusInfoModel.setReason(9); //9车辆认证通过车牌号重复次数一个月超过5次
                        blockedBonusInfoModel.setRemark("车牌月重复次数超过5次");
                    }
                    blockedBonusInfoModelMapper.insert(blockedBonusInfoModel);
                }
            }
            paramMap.clear(); paramMap.put("deleted",0);
            paramMap.put("driverMobile",userModel.getMobile()); paramMap.put("used",0);


            List<OwnerTruckInviteInfoModel> ownerTruckInfoModels = ownerTruckInviteInfoModelEMapper.selectListByParam(paramMap);

            if(ownerTruckInfoModels!=null && ownerTruckInfoModels.size()>0){
                for (OwnerTruckInviteInfoModel ownerTruckInviteInfoModel : ownerTruckInfoModels) {
                    OwnerTruckInfoModel ownerTruckInfoModel=new OwnerTruckInfoModel();
                    paramMap.clear();
                    paramMap.put("ownerId", ownerTruckInviteInfoModel.getOwnerId());
                    paramMap.put("driverTruckId",driverTruckInfo.getId());

                    List<OwnerTruckInfoModel> ownerTruckInfoModelList=  ownerTruckInfoModelEMapper.selectListByParam(paramMap);

                    if(ownerTruckInfoModelList!=null && ownerTruckInfoModelList.size()>0){
                        ownerTruckInfoModel=ownerTruckInfoModelList.get(0);
                        ownerTruckInfoModel.setDeleted(0);
                        ownerTruckInfoModel.setUpdateTime(DateUtil.now());
                        ownerTruckInfoModel.setCreateTime(DateUtil.now());
                        ownerTruckInfoModel.setTruckId(truckInfoModel.getId());
                        ownerTruckInfoModel.setDriverId(userModel.getId());
                        TopJetLog.info("更新用户车辆信息"+driverTruckInfo.getId());
                        //TODO 更新用户车辆人信息
//                        ownerTruckInfoService.updateByPrimaryKeySelective(ownerTruckInfoModel);
                    }else{
                        ownerTruckInfoModel.setDeleted(0);
                        ownerTruckInfoModel.setUpdateTime(DateUtil.now());
                        ownerTruckInfoModel.setCreateTime(DateUtil.now());
                        ownerTruckInfoModel.setTruckId(truckInfoModel.getId());
                        ownerTruckInfoModel.setOwnerId(ownerTruckInviteInfoModel.getOwnerId());
                        ownerTruckInfoModel.setDriverId(userModel.getId());
                        ownerTruckInfoModel.setDriverTruckId(driverTruckInfo.getId());
                        TopJetLog.info("插入用户车辆信息"+driverTruckInfo.getId());
                        //TODO 新增一条用户车辆信息
//                        ownerTruckInfoService.insertSelective(ownerTruckInfoModel);
                    }

                    ownerTruckInviteInfoModel.setUsed(1);
                    ownerTruckInviteInfoModel.setUpdateTime(DateUtil.now());
//                    ownerTruckInviteInfoService.updateByPrimaryKeySelective(ownerTruckInviteInfoModel);
                    //TODO 修改货主添加熟车记录邀请表
                }
            }
            //TODO 车辆审核通过之后修改积分
            //审核成功之后 进行积分修改
            /*IntegralCompletionInfoModelCriteria icm =new IntegralCompletionInfoModelCriteria();
            icm.createCriteria().andDeletedEqualTo(0).andUserIdEqualTo(userModel.getId()).andRuleIdEqualTo(IntegralCompletionInfoRuleId.RULE_NAME_DRIVER_AUDITSTATUS);
            List<IntegralCompletionInfoModel> infoModelList =integralCompletionInfoModelMapper.selectByCriteria(icm);
            IntegralCompletionInfoModel infoModel =new IntegralCompletionInfoModel();
            IntegralRruleInfoModel rruleInfoModel =integralRruleInfoModelMapper.selectByPrimaryKey(IntegralCompletionInfoRuleId.RULE_NAME_DRIVER_AUDITSTATUS);
            //根据积分规则ID  userID 认证状态 查询表中是否有记录
            if(infoModelList!=null && !infoModelList.isEmpty()){ //有记录
                //不为空  则update
                infoModel=infoModelList.get(0);
                //判断认证状态  只有认证通过才会去修改 否则直接返回 不继续后续操作
                if(!infoModel.getStatus().equals(UserStatus.USERTRUCK_STATUS_APPROVE.getCode())){
                    //审核不通过
                    infoModel.setSurplus(infoModel.getSurplus()-1);
                    infoModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                    infoModel.setUpdateTime(DateUtil.now());
                    infoModel.setVersion(infoModel.getVersion()+1);
                    infoModel.setStatus(UserStatus.USERTRUCK_STATUS_APPROVE.getCode());
                    integralCompletionInfoModelMapper.updateByPrimaryKeySelective(infoModel);
                    //操作ScoreInfo  ScoreCountInfo   user 表
                    scoreService.issueScore(userModel.getId(), UserConstant.SCORE_INTEGRAL_AUDITSTATUS,rruleInfoModel.getName(),rruleInfoModel.getValue());
                }
            }else{// 无记录  insert 一条记录
                infoModel.setRuleId(IntegralCompletionInfoRuleId.RULE_NAME_DRIVER_AUDITSTATUS);
                infoModel.setUserId(userModel.getId());
                infoModel.setTopLimit(rruleInfoModel.getTopLimit());
                infoModel.setSurplus(rruleInfoModel.getTopLimit());
                infoModel.setScore(rruleInfoModel.getValue());
                infoModel.setStatus(UserStatus.USERTRUCK_STATUS_APPROVE.getCode());
                infoModel.setCreateTime(DateUtil.now());
                infoModel.setCreateBy(SessionUtils.getSysUserSession().getId());
                integralCompletionInfoModelMapper.insertSelective(infoModel);
                //操作ScoreInfo  ScoreCountInfo   user 表
                scoreService.issueScore(userModel.getId(), UserConstant.SCORE_INTEGRAL_AUDITSTATUS,rruleInfoModel.getName(),rruleInfoModel.getValue());
            }*/

            try {
                SendSmsRTS sms = new SendSmsRTS();
                sms.setContent(title);
                sms.setMobileNum(userModel.getMobile());
                SendSmsVRU sendSmsVRU = basicFeignService.sendSms(sms);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
                TopJetLog.error("已经审核通过推送信息"+driverTruckInfo.getId()+e.getMessage());
            }

        }else{
            try {
                SendSmsRTS sms = new SendSmsRTS();
                sms.setContent(title);
                sms.setMobileNum(userModel.getMobile());
                SendSmsVRU sendSmsVRU = basicFeignService.sendSms(sms);
            } catch (Exception e) {
                e.printStackTrace();
                log.error(e.getMessage(),e);
                TopJetLog.error("车辆信息认证失败推送信息"+driverTruckInfo.getId()+e.getMessage());
            }
        }

        //车辆认证审核消息推送
        List<String> userAppTypes = userInfoService.getUserAppType(driverTruckInfo.getDriverId());
        if(userAppTypes != null && userAppTypes.size()>0){
            for (String userAppType : userAppTypes) {
                if(userAppType.equals("1") || userAppType.equals("3")){
                    List<AppButton> buttonList = new ArrayList<>();
                    AppButton button = new AppButton();
                    button.setText(messageConfig.getMessagePushButton());
                    if (bean.getAuditStatus()== 4 ) {
                        button.setAction(AppAction.getAppAction(AppAction.ACTION_OWNER_TRUCK_AUTHENTICATION,userAppType));
                    }else{
                        button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                    }
                    buttonList.add(button);

                    try{
                        if (bean.getAuditStatus()== 2) {
                            userInfoService.pushMessage(driverTruckInfo.getDriverId(),"车辆认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList, PushConstant.MESSAGETYPE_SYSTEM,0);
                        }else{
                            userInfoService.pushMessage(driverTruckInfo.getDriverId(),"车辆认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.getAppAction(AppAction.ACTION_OWNER_TRUCK_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                        }
                    }catch (Exception e){
                        log.error("发送车辆推送消息推送失败");
                    }
                }
            }
        }
        msg.setStatus(ExceptionEnum.E_0.getStatus());
        msg.setMsg(ExceptionEnum.E_0.getMsg());
        return msg;
    }

    @Override
    public List<DriverTruckInfoModel> countAuditPlateNo(String plateNo) {

        return driverTruckInfoModelEMapper.countAuditPlateNo(plateNo.charAt(0)+"",plateNo.charAt(1)+"",plateNo.substring(2,6));
    }
}

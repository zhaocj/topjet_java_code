package com.topjet.manage.service.impl;

import com.google.gson.Gson;
import com.topjet.basic.BasicFeignService;
import com.topjet.basic.TruckFeignService;
import com.topjet.basic.UserFeignService;
import com.topjet.basic.WalletFeginService;
import com.topjet.cloud.manage.constant.PictureServerProperties;
import com.topjet.cloud.manage.constant.PushConstant;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateDriverBussinessInfoRTS;
import com.topjet.cloud.manage.custservice.truck.bean.UpdateDriverBussinessInfoVRU;
import com.topjet.cloud.manage.custservice.user.bean.*;
import com.topjet.cloud.manage.mq.constant.QueuesName;
import com.topjet.cloud.manage.mq.message.AppAction;
import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.cloud.manage.mq.message.PushMessageMQBean;
import com.topjet.cloud.manage.service.basic.bean.*;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.auth.service.IDCheckService;
import com.topjet.common.auth.service.dto.CompanyRequestDTO;
import com.topjet.common.auth.service.dto.CompanyResponseDTO;
import com.topjet.common.constants.SystemConfig;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.TopjetException;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.common.util.AESEncodeUtil;
import com.topjet.common.util.CityUtil;
import com.topjet.common.util.DateUtil;
import com.topjet.config.MessageConfig;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.dto.RequestTms;
import com.topjet.manage.domain.model.*;
import com.topjet.manage.domain.vo.UserPushTokenVO;
import com.topjet.manage.mapper.readMapper.*;
import com.topjet.manage.mapper.writeMapper.TmsLogInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.UserAuditHistoryModelMapper;
import com.topjet.manage.mapper.writeMapper.UserInfoModelMapper;
import com.topjet.manage.mapper.writeMapper.UserTrailModelMapper;
import com.topjet.manage.service.*;
import com.topjet.tool.common.constant.SystemConstant;
import com.topjet.tool.common.util.FormatUtil;
import com.topjet.tool.common.util.JsonUtil;
import com.topjet.user.constant.AuditHistoryConstant;
import com.topjet.user.constant.UserStatus;
import com.topjet.util.HttpUtils;
import net.sf.json.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-09 14:28
 **/
@Service
public class UserInfoServiceImpl implements UserInfoService {

    private static Logger log = LoggerFactory.getLogger(UserInfoServiceImpl.class);

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;
    @Autowired
    private  UserInfoModelMapper userInfoModelMapper;

    @Autowired
    private UserService userService;

    @Autowired
    private CityService cityService;

    @Autowired
    private IDCheckService idCheckService;

    @Autowired
    private UserOtherInfoModelEMapper userOtherInfoModelEMapper;

    @Autowired
    private UserAuditHistoryService userAuditHistoryService;

    @Autowired
    private BasicFeignService basicFeignService;

    @Autowired
    private UserTrailModelMapper userTrailModelMapper;

    @Autowired
    private UserTrailModelEMapper userTrailModelEMapper;

    @Autowired
    private UserAuditHistoryModelMapper userAuditHistoryModelMapper;

    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;

    @Autowired
    private DriverBusinessLineInfoModelEMapper driverBusinessLineInfoModelMapper;

    @Autowired
    private UserFeignService userFeignService;

    @Autowired
    private WalletFeginService walletFeginService;

    @Autowired
    private TaskAssignService taskAssignService;

    @Autowired
    private PhotoInfoService photoInfoService;

    @Autowired
    private TaskBucketInfoModelEMapper taskBucketInfoModelEMapper;

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private AmqpTemplate rabbitTemplate;

    @Autowired
    private PhotoInfoModelEMapper photoInfoModelEMapper;
    @Autowired
    private DriverBusinessLineInfoService driverBusinessLineInfoService;
    @Autowired
    private TruckFeignService truckFeignService;
    @Autowired
    private ChangeMobileService  changeMobileService;

    @Autowired
    private SystemConfig systemConfig;


    @Autowired
    private TmsLogInfoModelMapper tmsLogInfoModelMapper;



    @Override
    public List<MemberAuditListResponseBean> getMemberAuditList(MemberAuditListRequestBean memberAuditListRequestBean) {

        return userInfoModelEMapper.getMemberAuditList(memberAuditListRequestBean);
    }

    @Override
    public int getMemberAuditListCount(MemberAuditListRequestBean memberAuditListRequestBean) {

        return userInfoModelEMapper.getMemberAuditListCount(memberAuditListRequestBean);
    }

    @Override
    public UserInfoBean getUserInfoBean(Integer userId, int flag) {
        log.info("***************开始获取用户身份认证详情*******************");
        UserInfoBean userInfoBean = new UserInfoBean();
        UserInfoModel userModel = userService.findById(userId);
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userId);
        UserOtherInfoModel userOtherInfoModel = userOtherInfoModelEMapper.selectListByParam(paramMap).get(0);
        userInfoBean.setWalletId(userModel.getWalletId());
//        userInfoBean.setScore(userOtherInfoModel.getScore());
        userInfoBean.setUserAuthStatus(userModel.getUserAuthStatus());
        userInfoBean.setId(userId);
        userInfoBean.setUseStatus(userModel.getUseStatus());
        userInfoBean.setIdNo(userModel.getIdNo());
//        userInfoBean.setAge(StringUtils.isBlank(userModel.getIdNo())?0:(DateUtil.getYear()-Integer.parseInt(userModel.getIdNo().substring(6,10))));
        //判断用户身份证号是否为空，是否是15或者18位，年份是否有特殊字符
        if (!StringUtils.isBlank(userModel.getIdNo()) && (userModel.getIdNo().length() == 18 || userModel.getIdNo().length() == 15)) {
            userInfoBean.setAge(!StringUtils.isBlank(userModel.getIdNo()) && userModel.getIdNo().substring(6, 10).matches("^[0-9]*$") ? (DateUtil.getYear() - Integer.parseInt(userModel.getIdNo().substring(6, 10))) : 0);
        } else {
            userInfoBean.setAge(0);
        }
        userInfoBean.setMobile(userModel.getMobile());
        userInfoBean.setUsername(userModel.getUsername());
        userInfoBean.setUserType(userModel.getUserType());
        userInfoBean.setCompanyName(userOtherInfoModel.getCompanyName());
        userInfoBean.setCreateTime(userModel.getCreateTime());
//        userInfoBean.setCredit(userOtherInfoModel.getCredit());
        //  userInfoBean.setReditRemark(userModel.getReditRemark());
        if (flag != 2) {
            if (!StringUtils.isBlank(CityUtil.convertToString(userOtherInfoModel.getResidentCityId()))) {
                if (!StringUtils.isBlank(userOtherInfoModel.getResident3())) {
                    userInfoBean.setResident1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(userOtherInfoModel.getResidentCityId())).getAdcode()));
                    userInfoBean.setResident2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(userOtherInfoModel.getResidentCityId())).getAdcode()));
                    userInfoBean.setResident3(CityUtil.convertToString(userOtherInfoModel.getResidentCityId()));
                } else {
                    userInfoBean.setResident1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(userOtherInfoModel.getResidentCityId())).getAdcode()));
                    userInfoBean.setResident2(CityUtil.convertToString(userOtherInfoModel.getResidentCityId()));
                }
            }
            if (!StringUtils.isBlank(CityUtil.convertToString(userOtherInfoModel.getRegisteredCityId()))) {
                List<String> cityList = cityService.getCityIdList(CityUtil.convertToString(userOtherInfoModel.getRegisteredCityId()));
                if (cityList != null && cityList.size() > 1) {
                    userInfoBean.setOrder(cityService.getCityName(cityList.get(1)));//临时存放注册城市
                }
            }
        }
        if (flag == 3) {
            Map<String, Object> paraMap = new HashMap<String, Object>();
            paraMap.put("userAuthStatus", UserStatus.USERAUTH_STATUS_FAILURE.getCode());
            paraMap.put("userId", userModel.getId());

            List<UserTrailModel> ulmList = userTrailModelEMapper.selectListByParam(paraMap);

            if (!ulmList.isEmpty()) {
                UserTrailModel ulm = ulmList.get(0);
                userInfoBean.setCompanyNameOlder(ulm.getCompanyName());
                try {
                    if (!StringUtils.isBlank(CityUtil.convertToString(ulm.getResidentCityId()))) {
                        List<String> cityList = cityService.getCityIdList(CityUtil.convertToString(ulm.getResidentCityId()));
                        if (cityList.size() > 2) {
                            userInfoBean.setResident1Older(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(ulm.getResidentCityId())).getAdcode()));
                            userInfoBean.setResident2Older(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(ulm.getResidentCityId())).getAdcode()));
                            userInfoBean.setResident3Older(CityUtil.convertToString(ulm.getResidentCityId()));
                        } else {
                            userInfoBean.setResident1Older(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(ulm.getResidentCityId())).getAdcode()));
                            userInfoBean.setResident2Older(CityUtil.convertToString(ulm.getResidentCityId()));
                        }
                    }
                } catch (Exception e) {
                    TopJetLog.info("获得更新前的数据失败");
                }
            }
        }
        if (flag == 1) {
            Integer auditStatus = 0;
            if (userModel.getUserAuthStatus().equals(UserStatus.USERAUTH_STATUS_FAILURE.getCode())) {
                auditStatus = AuditHistoryConstant.USER_ID_AUDIT_FAIL;
            } else if (userModel.getUserAuthStatus().equals(UserStatus.USERAUTH_STATUS_APPROVE.getCode())) {
                auditStatus = AuditHistoryConstant.USER_ID_AUDIT_SUCCESS;
            }
            //查询用户审核历史表中身份审核历史
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("sourceId", userId);
            map.put("sourceType", AuditHistoryConstant.AUDIT_IDENTITY_USER);
            map.put("deleted", CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            map.put("statusAfter", auditStatus);
            List<UserAuditHistoryModel> ahm = userAuditHistoryModelEMapper.selectListByParam(map);
            if (ahm != null && ahm.size()>0) {
                userInfoBean.setAuditIDReason(ahm.get(0).getRemark());
            }
            if (userModel.getUseStatus().equals(UserStatus.USERSTATUS_APPROVED.getCode())) {
                auditStatus = AuditHistoryConstant.AUDIT_SUCCESS;
            } else if (userModel.getUseStatus().equals(UserStatus.USERSTATUS_FAILURE.getCode())) {
                auditStatus = AuditHistoryConstant.AUDIT_FAILED;
            } else if (userModel.getUseStatus().equals(UserStatus.USERSTATUS_FROZEN.getCode())) {
                auditStatus = AuditHistoryConstant.AUDIT_FROZEN;
            }

            map.put("statusAfter",auditStatus);
            map.put("sourceType",AuditHistoryConstant.AUDIT_USER);
            List<UserAuditHistoryModel> ahm1 = userAuditHistoryModelEMapper.selectListByParam(map);
            if (ahm1 != null && ahm1.size()>0) {
                userInfoBean.setReditRemark(ahm1.get(0).getRemark());
            }

        }
        userInfoBean.setBusinessAddressCityId(userOtherInfoModel.getBusinessAddressCityId());
        List<String> city = cityService.getCityIdList(CityUtil.convertToString(userOtherInfoModel.getBusinessAddressCityId()));
        String businessAddress1 = cityService.getCityAddress1(city);
        String businessAddress2 = cityService.getCityAddress2(city);
        String businessAddress3 = cityService.getCityAddress3(city);

        userInfoBean.setBusinessAddressCity1(StringUtils.isBlank(businessAddress1) ? "0" : businessAddress1);
        userInfoBean.setBusinessAddressCity2(businessAddress2);
        userInfoBean.setBusinessAddressCity3(businessAddress3);
        userInfoBean.setTelephone(userOtherInfoModel.getTelephone());
//        userInfoBean.setDriverCommentLevel(userOtherInfoModel.getDriverCommentLevel());
//        userInfoBean.setOwnerCommentLevel(userModel.getOwnerCommentLevel());

        userInfoBean.setBusinessAddress(userOtherInfoModel.getBusinessAddress());
//        userInfoBean.setUserInfoVersion(userModel.getVersion());
        userInfoBean.setResidentCityId(userOtherInfoModel.getResidentCityId());

         this.getPhotos(userInfoBean, userId,flag);
         this.getBusinessLine(userInfoBean, userId);
        return userInfoBean;
    }

    @Override
    public UserInfoVerifiedReturn auditAndUpdateIdinfo(UserInfoBean userInfoBean) throws TopjetErrorCodeException, TopjetException {
        UserInfoVerifiedReturn userInfoVerifiedReturn = new UserInfoVerifiedReturn();
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", userInfoBean.getId());
        List<UserInfoModel> uModel = userInfoModelEMapper.selectListByParam(paramMap);
        paramMap.clear();
        paramMap.put("userId", userInfoBean.getId());
        UserOtherInfoModel uoModel = userOtherInfoModelEMapper.selectListByParam(paramMap).get(0);
        if (uModel != null) {
            try {
                userInfoVerifiedReturn = idCheckService.checkAuthInfo(userInfoBean.getIdNo(), userInfoBean.getUsername(), uoModel.getIdAddress());
            } catch (Exception e) {

               e.printStackTrace();
            }
            return userInfoVerifiedReturn;

        }
        userInfoVerifiedReturn.setErrorMessage("数据过期");
        return userInfoVerifiedReturn;
    }

    @Override
    public ResultBaseMsg updateMemberInfo(UserInfoBean userInfoBean) throws TopjetExceptionHandler {
       log.info("********开始实名认证审核***********"+userInfoBean.getMobile());
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",userInfoBean.getId());
        paramMap.put("userType",userInfoBean.getUserType());
        List<UserInfoModel> userInfoModels = userInfoModelEMapper.selectListByParam(paramMap);
        if(userInfoModels == null || userInfoModels.size()<1){
            resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            return resultBaseMsg;
        }
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        if (uModel != null || !userInfoBean.getUserStatusOlder().equals(2)) {
            uModel.setUsername(userInfoBean.getUsername());
            uModel.setIdNo(userInfoBean.getIdNo());
            Integer statusAfter = AuditHistoryConstant.AUDIT_SUCCESS;
//            PushEum pushEum = PushEum.USER_AUDIT_NO;
//            PushBean pushBean = new PushBean();
            String content = "";

            if (userInfoBean.getAuditResult().equals(UserStatus.USERSTATUS_APPROVED.getCode().toString())) {
                Integer idNoUserId = userService.idNoIsEmptyApproved(userInfoBean.getIdNo());
                if (idNoUserId != null && !idNoUserId.equals(userInfoBean.getId())) {
                    resultBaseMsg.setStatus(ExceptionEnum.E_26.getStatus());
                    resultBaseMsg.setMsg(ExceptionEnum.E_26.getMsg());
                    return resultBaseMsg;
                }
                uModel.setUseStatus(UserStatus.USERSTATUS_APPROVED.getCode());
            } else if (userInfoBean.getAuditResult().equals(UserStatus.USERSTATUS_FAILURE.getCode().toString())) {
                statusAfter = AuditHistoryConstant.AUDIT_FAILED;
                uModel.setUseStatus(UserStatus.USERSTATUS_FAILURE.getCode());
            }

            UpdateUserStatusByIdRTS updateUserStatusByIdRTS = new UpdateUserStatusByIdRTS();
            String walletId = "";

            String messageTitle = messageConfig.getIdVerifyFailTitle();

            //审核成功之后
            if (userInfoBean.getAuditResult().equals(UserStatus.USERSTATUS_APPROVED.getCode().toString())) {
                messageTitle = messageConfig.getIdVerifyPassTitle();
                try {
                    log.info("用户请求钱包创建开始" + uModel.getMobile());
                    walletId= this.createWallet(uModel);
                    log.info("用户请求钱包创建结束" + uModel.getMobile());
                } catch (Exception e) {
                    log.error("用户请求创建钱包异常" + uModel.getMobile() + e.getMessage() + e.getClass().getName());
                    resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
                    resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
                    return resultBaseMsg;
                }
                try {
                    //verfiedSuccessUpdatePhoto(userInfoBean);//设置审核通过的身份证和图像照片
                    log.info("设置审核通过的身份证和图像照片完成" + uModel.getMobile());
                    // TODO 更新常用联系人
//                    frequentContactsInfoService.updateUserId(uModel); //更新常用联系人
                    log.info("更新常用联系人完成" + uModel.getMobile());
                   // Integer photoCount = 0;//计算诚信时需要参考用户上传的身份证和图像照片
                } catch (Exception e) {
                    e.printStackTrace();
                    TopJetLog.error("用户审核出现异常:" + e.getMessage() + e.getClass().getName());
                }
            }
            UpdateUserStatusByIdVRU vru;
            //this.updateUserBusinessLine(userInfoBean);
            TopJetLog.info("更新用户经营地址完成" + uModel.getMobile());
//                this.updateUserInfo(userInfoBean, uModel);
            Integer  sex = 2;
            if(userInfoBean.getIdNo() != null && !userInfoBean.getIdNo().equals("")){
                sex = Integer.parseInt(userInfoBean.getIdNo().substring(userInfoBean.getIdNo().length()-2,userInfoBean.getIdNo().length()-1));
            }
            updateUserStatusByIdRTS.setUserSex(sex%2==1?2:1);
            updateUserStatusByIdRTS.setUserId(userInfoBean.getId());
            updateUserStatusByIdRTS.setUseStatus(Integer.valueOf(userInfoBean.getAuditResult()));
            updateUserStatusByIdRTS.setUserType(userInfoBean.getUserType());
            updateUserStatusByIdRTS.setIdNo(FormatUtil.stringIsEmpty(userInfoBean.getIdNo()));
            updateUserStatusByIdRTS.setUsername(FormatUtil.stringIsEmpty(userInfoBean.getUsername()));
            updateUserStatusByIdRTS.setWalletId(walletId.equals("")?0:Integer.valueOf(walletId));
            BigDecimal creditByUserid = new BigDecimal("0");
            try{
                creditByUserid = userInfoModelEMapper.getCreditByUserid(uModel.getId());
            }catch (Exception e){
                log.error("诚信值存储过程调用失败："+e);
            }
            updateUserStatusByIdRTS.setCredit(creditByUserid);
            try {
                vru = userFeignService.updateUserStatusById(updateUserStatusByIdRTS);
                if(vru.getCode() != 1){
                    log.info("服务端实名认证返回码："+vru.getCode()+"返回信息+"+vru.getMsg());
                    log.error("用户："+uModel.getMobile()+"实名审核失败！！！");
                    resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
                    resultBaseMsg.setMsg(vru.getMsg());
                    return resultBaseMsg;
                }
                log.info("更新用户基本信息完成" + uModel.getMobile());
            } catch (Exception e) {
                resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
                resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
                log.error(e.getMessage());
                log.error("更新用户基本信息异常" + uModel.getMobile() + e.getMessage() + e.getClass().getName());
                return resultBaseMsg;
            }
             /*更新任务数量*/
            try {
                List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                if (!taskUser.isEmpty()) {
                    taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.USER_REGISTER_AUDIT, userInfoBean.getId());
                } else {
                    taskAssignService.updateTasks(0, TaskConstants.USER_REGISTER_AUDIT, userInfoBean.getId());
                }
            } catch (Exception e) {
                TopJetLog.error("更新用户审核任务数量异常:" + e.getMessage() + e.getClass().getName() + "用户mobile:" + userInfoBean.getMobile());
            }

            //审核人信息写日志
            UserAuditHistoryModel ahm = new UserAuditHistoryModel();
            ahm.setSourceType(AuditHistoryConstant.AUDIT_USER);
            ahm.setSourceId(uModel.getId());
            ahm.setStatusAfter(statusAfter);
            ahm.setRemark(userInfoBean.getAuditResultMsg());
            if (SessionUtils.getSysUserSession() == null) {
                //地推用户审核
                ahm.setCreateBy(userInfoBean.getAuditUserId());
                ahm.setAuditName(userInfoBean.getAuditName());
            } else {
                ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
                ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
            }

            ahm.setCreateTime(DateUtil.now());
            userAuditHistoryService.insert(ahm);
            TopJetLog.info("审核日志记录完成" + uModel.getMobile());

//            发送消息推送
            List<String> userAppTypes = this.getUserAppType(userInfoBean.getId());
            if(userAppTypes != null && userAppTypes.size()>0){
                for (String userAppType : userAppTypes) {
                    List<AppButton> buttonList = new ArrayList<>();
                    AppButton button = new AppButton();
                    button.setText(messageConfig.getMessagePushButton());
                    if (userInfoBean.getAuditResult().equals(UserStatus.USERSTATUS_FAILURE.getCode().toString())) {
                        button.setAction(AppAction.getAppAction(AppAction.ACTION_OWNER_AUTHENTICATION,userAppType));
                    }else{
                        button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                    }
                    buttonList.add(button);

                    try{
                        if (userInfoBean.getAuditResult().equals(UserStatus.USERSTATUS_APPROVED.getCode().toString())) {
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"实名认证",messageTitle,SystemConstant.BEINGPUSH_OWNER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else{
                                pushMessage(userInfoBean.getId(),"实名认证",messageTitle,SystemConstant.BEINGPUSH_DRIVER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }else{
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"实名认证",messageTitle,SystemConstant.BEINGPUSH_OWNER,AppAction.getAppAction(AppAction.ACTION_OWNER_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else {
                                pushMessage(userInfoBean.getId(),"实名认证",messageTitle,SystemConstant.BEINGPUSH_DRIVER,AppAction.getAppAction(AppAction.ACTION_OWNER_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }
                        log.info("发送实名认证消息推送成功");
                    }catch (Exception e){
                        log.error("发送实名认证消息推送失败");
                    }
                }
            }

            try {
                SendSmsRTS sendSmsRTS = new SendSmsRTS();
                sendSmsRTS.setMobileNum(uModel.getMobile());
                sendSmsRTS.setContent(messageTitle);
                SendSmsVRU sendSmsVRU = basicFeignService.sendSms(sendSmsRTS);
            }catch (Exception e){
                log.error("*****发送实名认证短信失败*******"+uModel.getMobile());
                e.printStackTrace();
            }
        } else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return resultBaseMsg;
    }



    @Override
    public ResultBaseMsg updateUserIDInfo(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler {
        log.info("**********进入身份认证审核**********");
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        if (uModel != null && userInfoBean.getUserStatusOlder().equals("1")) {
            try {
                uModel.setUserAuthStatus(userInfoBean.getUserAuthStatus());
                uModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
                uModel.setUpdateTime(DateUtil.now());
                UpdateUserAuthStatusByIdRTS updateUserAuthStatusByIdRTS = new UpdateUserAuthStatusByIdRTS();
                updateUserAuthStatusByIdRTS.setUserId(userInfoBean.getId());
                updateUserAuthStatusByIdRTS.setUserAuthStatus(userInfoBean.getUserAuthStatus());
                UpdateUserAuthStatusByIdVRU vru =new UpdateUserAuthStatusByIdVRU() ;
                try {
                    vru = userFeignService.updateUserAuthStatusById(updateUserAuthStatusByIdRTS);
                }catch (Exception e){
                    log.error("*****身份认证审核接口调用失败*******"+userInfoBean.getMobile());
                    e.printStackTrace();
                }


                if(vru.getCode() == null ){
                    log.info("服务端身份认证返回码："+vru.getCode()+"返回信息+"+vru.getMsg());
                    log.error("用户："+uModel.getMobile()+"身份认证审核失败！！！");
                    resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
                    resultBaseMsg.setMsg(ExceptionEnum.E_3.getMsg());
                    return resultBaseMsg;
                }else if(vru.getCode()!=1){
                    resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
                    resultBaseMsg.setMsg(vru.getMsg());
                    return resultBaseMsg;
                }
            } catch (Exception e) {
                log.error("身份认证调用服务端接口失败:" + e.getClass().getName() + ":" + e.getMessage() + userInfoBean.getId());
            }
            //审核人信息写日志z
            UserAuditHistoryModel ahm = new UserAuditHistoryModel();
            ahm.setSourceType(AuditHistoryConstant.AUDIT_IDENTITY_USER);
            ahm.setSourceId(uModel.getId());
            if (userInfoBean.getUserAuthStatus().equals(UserStatus.USERAUTH_STATUS_APPROVE.getCode())) {
                ahm.setStatusAfter(AuditHistoryConstant.USER_ID_AUDIT_SUCCESS);
            } else {
                ahm.setStatusAfter(AuditHistoryConstant.USER_ID_AUDIT_FAIL);
            }
            ahm.setRemark(userInfoBean.getAuditIDReason());
            ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
            ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
            userAuditHistoryService.insert(ahm);
            TopJetLog.info("身份审核日志记录完成" + uModel.getMobile());
            try {
                saveUserTrail(uModel,request);//保存用户身份审核记录
            }catch (Exception e){
                TopJetLog.error("保存用户身份审核记录出错:"+e.getClass().getName()+":"+e.getMessage()+userInfoBean.getId());
            }
            /*更新任务数量*/
            try {
                List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                if (!taskUser.isEmpty()) {
                    taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.USER_ID_AUDIT, userInfoBean.getId());
                }else{
                    taskAssignService.updateTasks(0, TaskConstants.USER_ID_AUDIT, userInfoBean.getId());
                }
            }catch (Exception e){
                TopJetLog.error("更新用户审核任务数量异常:"+e.getMessage() +e.getClass().getName()+"用户mobile:"+userInfoBean.getMobile());
            }
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
            String content = "";
            String title = messageConfig.getIdAuthFailTitle();
            String pushType = uModel.getUserType()==1? SystemConstant.BEINGPUSH_DRIVER:SystemConstant.BEINGPUSH_OWNER;
           if (userInfoBean.getUserAuthStatus().equals(UserStatus.USERAUTH_STATUS_APPROVE.getCode())) {
               title = messageConfig.getIdAuthPassTitle();
            }

            //发送推送消息

            List<String> userAppTypes = this.getUserAppType(userInfoBean.getId());
            if(userAppTypes != null && userAppTypes.size()>0){
                for (String userAppType : userAppTypes) {
                    List<AppButton> buttonList = new ArrayList<>();
                    AppButton button = new AppButton();
                    button.setText(messageConfig.getMessagePushButton());
                    if (userInfoBean.getUserAuthStatus() == UserStatus.USERAUTH_STATUS_FAILURE.getCode()) {
                        button.setAction(AppAction.getAppAction(AppAction.ACTION_OWNER_ID_AUTHENTICATION,userAppType));
                    }else{
                        button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                    }
                    buttonList.add(button);

                    try{
                        if (userInfoBean.getUserAuthStatus() == UserStatus.USERAUTH_STATUS_APPROVE.getCode()) {
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"身份认证",title,SystemConstant.BEINGPUSH_OWNER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else{
                                pushMessage(userInfoBean.getId(),"身份认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }else{
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"身份认证",title,SystemConstant.BEINGPUSH_OWNER,AppAction.getAppAction(AppAction.ACTION_OWNER_ID_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else {
                                pushMessage(userInfoBean.getId(),"身份认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.getAppAction(AppAction.ACTION_OWNER_ID_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }
                    }catch (Exception e){
                        log.error("发送身份认证消息推送失败");
                    }
                }
            }

            try{
                SendSmsRTS sendSmsRTS = new SendSmsRTS();
                sendSmsRTS.setContent(title);
                sendSmsRTS.setMobileNum(uModel.getMobile());
                SendSmsVRU sendSmsVRU = basicFeignService.sendSms(sendSmsRTS);
            }catch (Exception e){
                log.error("发送身份认证短信失败");
                e.printStackTrace();}
        } else {
            resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return resultBaseMsg;
    }

    //会员查询列表
    @Override
    public List<MemberListResponseBean> getMemberList(MemberListRequestBean memberListRequestBean) {
        List<MemberListResponseBean> dataList = userInfoModelEMapper.getMemberList(memberListRequestBean);
        for (MemberListResponseBean rb : dataList) {
            if (!StringUtils.isBlank(rb.getResidentCity())) {
                List<String> cityList = cityService.getCityIdList(rb.getResidentCity());
                if (cityList != null && cityList.size() > 1) {
                    rb.setResidentCity(cityService.getCityName(cityList.get(1)));//临时存放注册城市
                }
                else{
                    rb.setResidentCity("上海市");
                }
            }
        }
        return dataList;
    }

    //会员列表页数
    @Override
    public int getMemberCount(MemberListRequestBean memberListRequestBean) {
        return userInfoModelEMapper.getMemberCount(memberListRequestBean);
    }
    //根据手机号码查询

    @Override
    public List<UserInfoModel> getMobile(String mobile) {
        List<UserInfoModel> dataList = userInfoModelEMapper.getMobile(mobile);
        if (dataList != null && dataList.size() > 0) {
            return dataList;
        }
        return null;
    }

    //头像审核查询列表实现
    @Override
    public List<MemberAuditListResponseBean> getHeadList(MemberAuditListRequestBean memberAuditListRequestBean) {
        return userInfoModelEMapper.getHeadList(memberAuditListRequestBean);
    }
    //头像审核查询列表页数

    @Override
    public int getHeadCount(MemberAuditListRequestBean memberAuditListRequestBean) {
        return userInfoModelEMapper.getHeadCount(memberAuditListRequestBean);
    }

    @Override
    public UserInfoBean getUserInfo(Integer id) {
        UserInfoBean userInfoBean = new UserInfoBean();
        UserInfoModel userInfoModel = userService.findById(id);
     //   UserInfoBean userBean = userInfoModelEMapper.getUserInfo(id);
        //用户信息
        userInfoBean.setId(id);
        userInfoBean.setUseStatus(userInfoModel.getUseStatus());
        userInfoBean.setIdNo(userInfoModel.getIdNo());
        userInfoBean.setAge(StringUtils.isBlank(userInfoModel.getIdNo()) ? 0 : (com.topjet.common.util.DateUtil.getYear() - Integer.parseInt(userInfoModel.getIdNo().substring(6, 10))));
        userInfoBean.setMobile(userInfoModel.getMobile());
        userInfoBean.setUsername(userInfoModel.getUsername());
        userInfoBean.setUserType(userInfoModel.getUserType());
        userInfoBean.setCompanyName(userInfoModel.getCompanyName());
        userInfoBean.setCreateTime(userInfoModel.getCreateTime());
        this.getPhotos(userInfoBean,id,0);
        return userInfoBean;
    }
    public UserInfoBean getPhotos(UserInfoBean userInfoBean, int userId,int flag) {
        log.info("***********进入获取照片方法**************");
        List<PhotoInfoModel> photoInfoModelList = photoInfoService.getPhotosByUserId(userId);
        for (PhotoInfoModel photoInfoModel : photoInfoModelList) {
            if (!"".equals(photoInfoModel.getType()) && photoInfoModel.getType() != null) {
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_HEAD_ICON)) {
                    userInfoBean.setHeadPhotoType(PictureServerProperties.PHOTO_TYPE_HEAD_ICON + "");
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_HEAD_ICON);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setHeadPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取头像Url出错**************");
                                e.printStackTrace();
                            }
                            userInfoBean.setHeadPhotoBase64Url(photoInfoModel.getUrl());
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {

                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_HEAD_ICON);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setHeadPhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取头像UrlTobe出错**************");
                                e.printStackTrace();
                            }
                            userInfoBean.setHeadPhotoBase64Url(photoInfoModel.getUrl());
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                        e.printStackTrace();
                    }
                }
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_ID)) {
                    userInfoBean.setIdNoPhotoType(PictureServerProperties.PHOTO_TYPE_ID + "");
                    try {
                        if (!StringUtils.isBlank(photoInfoModel.getUrl())) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_ID);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setIdNoPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取身份证正面图片Url出错**************");
                                e.printStackTrace();
                            }
                            userInfoBean.setIdNoPhotoBase64Url(photoInfoModel.getUrl());
                        }
                        if (!StringUtils.isBlank(photoInfoModel.getUrlTobe())) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_ID);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setIdNoPhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                e.printStackTrace();
                                log.error("***********获取身份证正面图片UrlTobe出错**************");
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }
                //身份证照片反面
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_ID2)) {
                    userInfoBean.setIdNoNegativePhotoType(PictureServerProperties.PHOTO_TYPE_ID2 + "");
                    try {
                        if (!StringUtils.isBlank(photoInfoModel.getUrl())) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_ID2);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setIdNoNegativePhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取身份证照片反面Url出错**************");
                                e.printStackTrace();
                            }
                            userInfoBean.setIdNoNegativePhotoBase64Url(photoInfoModel.getUrl());
                        }
                        if (!StringUtils.isBlank(photoInfoModel.getUrlTobe())) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_ID2);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setIdNoNegativePhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取身份证照片反面UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }

                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_DRIVER_LICENCE)) {
                    userInfoBean.setDirverLicenceType(PictureServerProperties.PHOTO_TYPE_DRIVER_LICENCE + "");
                    userInfoBean.setDriverPhotoId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_DRIVER_LICENCE);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setDirverLicenceUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setDirverLicenceUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取DirverLicenceUrl出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_DRIVER_LICENCE);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setDirverLicenceUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取DirverLicenceUrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }
                // 门店
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_SHOP_FRONT)) {
                    userInfoBean.setShopFrontType(PictureServerProperties.PHOTO_TYPE_SHOP_FRONT + "");
                    userInfoBean.setShopFrontPHotoId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_SHOP_FRONT);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setShopFrontPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setShopFrontPhotoUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取门店照片Url出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_SHOP_FRONT);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setShopFrontPhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取门店照片UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }

                // 营业执照
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_BUSINESS_PHOTO)) {
                    userInfoBean.setBusinessPhotoType(PictureServerProperties.PHOTO_TYPE_BUSINESS_PHOTO + "");
                    userInfoBean.setBusinessPhotoId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BUSINESS_PHOTO);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setBusinessPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setBusinessPhotoUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取营业执照照片Url出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BUSINESS_PHOTO);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setBusinessPhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取营业执照照片UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }

                //营运证
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_OPERATING_PERMITS)) {
                    userInfoBean.setOperatingPermitsPhotoType(PictureServerProperties.PHOTO_TYPE_OPERATING_PERMITS + "");
                    userInfoBean.setOperatingPermitsPhotoId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_OPERATING_PERMITS);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setOperatingPermitsPhotoUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setOperatingPermitsPhotoUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取营运证照片Url出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_OPERATING_PERMITS);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setOperatingPermitsPhotoUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取营运证照片UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }

                // 名片
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_BUSINESS_CARD)) {
                    userInfoBean.setBusinessCardType(PictureServerProperties.PHOTO_TYPE_BUSINESS_CARD + "");
                    userInfoBean.setBusinessCardId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BUSINESS_CARD);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setBusinessCardUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setBusinessCardUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取名片Url出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BUSINESS_CARD);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setBusinessCardUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取名片UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }


                // 机打发票
                if (photoInfoModel.getType().equals(PictureServerProperties.PHOTO_TYPE_BIG_ORDERS)) {
                    userInfoBean.setBigOrdersType(PictureServerProperties.PHOTO_TYPE_BIG_ORDERS + "");
                    userInfoBean.setBigOrdersId(photoInfoModel.getId());
                    try {
                        if (!"".equals(photoInfoModel.getUrl()) && photoInfoModel.getUrl() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BIG_ORDERS);
                                geturlRTS.setKey(photoInfoModel.getUrl());
                                userInfoBean.setBigOrdersUrl(basicFeignService.getUrl(geturlRTS).getObjurl());
                                if(flag == 3){
                                    userInfoBean.setBigOrdersUrlTobe1(photoInfoModel.getUrl());
                                }
                            } catch (TopjetException e) {
                                log.error("***********获取机打发票Url出错**************");
                                e.printStackTrace();
                            }
                        }
                        if (!"".equals(photoInfoModel.getUrlTobe()) && photoInfoModel.getUrlTobe() != null) {
                            try {
                                GeturlRTS geturlRTS = new GeturlRTS();
                                geturlRTS.setType(PictureServerProperties.PHOTO_TYPE_BIG_ORDERS);
                                geturlRTS.setKey(photoInfoModel.getUrlTobe());
                                userInfoBean.setBigOrdersUrlTobe(basicFeignService.getUrl(geturlRTS).getObjurl());
                            } catch (TopjetException e) {
                                log.error("***********获取机打发票UrlTobe出错**************");
                                e.printStackTrace();
                            }
                        }
                    } catch (Exception e) {
                        TopJetLog.error(e.getMessage());
                    }
                }
            }
        }
        return userInfoBean;
    }

    public UserInfoBean getBusinessLine(UserInfoBean userInfoBean, int userId) {

        Map<String, Object> paraMap = new HashMap<String, Object>();
        paraMap.put("driverId", userId);
        paraMap.put("deleted",0);

        List<DriverBusinessLineInfoModel> dblList = driverBusinessLineInfoModelMapper.selectListByParam(paraMap);
        if (dblList != null && dblList.size() > 0) {
            int i = 0;
            for (DriverBusinessLineInfoModel driverBusinessLineInfoModel : dblList) {
                 driverBusinessLineInfoModel = dblList.get(i);
                switch (i){
                    case 0:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineFisrt1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineFisrt1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineFisrt2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineFisrt3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineFisrtId(Integer.parseInt(""));
                        } else{
                            userInfoBean.setBusinessLineFisrtId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 1:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineSecond1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineSecond1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineSecond2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineSecond3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineSecondId(Integer.parseInt(""));
                        } else{
                            userInfoBean.setBusinessLineSecondId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 2:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineThird1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineThird1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineThird2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineThird3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineThirdId(Integer.parseInt(""));
                        } else{
                            userInfoBean.setBusinessLineThirdId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                    case 3:
                        if (StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineForth1("0");
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine1())) {
                            userInfoBean.setBusinessLineForth1(CityUtil.convertToString(cityService.getFirstCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine2())) {
                            userInfoBean.setBusinessLineForth2(CityUtil.convertToString(cityService.getSecondCity(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId())).getAdcode()));
                        }
                        if (!StringUtils.isBlank(driverBusinessLineInfoModel.getBusinessLine3())) {
                            userInfoBean.setBusinessLineForth3(CityUtil.convertToString(driverBusinessLineInfoModel.getBusinessLineCityId()));
                        }
                        if(driverBusinessLineInfoModel.getId() == null){
                            userInfoBean.setBusinessLineForthId(Integer.parseInt(""));
                        } else{
                            userInfoBean.setBusinessLineForthId(driverBusinessLineInfoModel.getId());
                        }
                        break;
                }
                i++;
            }
                    return userInfoBean;

        }
        userInfoBean.setBusinessLineFisrt1("0");
        userInfoBean.setBusinessLineSecond1("0");
        userInfoBean.setBusinessLineThird1("0");
        userInfoBean.setBusinessLineForth1("0");
        return userInfoBean;
    }

    //修改头像资料
    @Override
    public ResultBaseMsg updateHeadDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        if (uModel != null) {
            UserInfoBean userInfoBean1 = userInfoModelEMapper.getUserInfo(userInfoBean.getId());//根据user id查询头像信息
            Integer statusAfter = AuditHistoryConstant.USER_ICON_AUDIT_SUCCESS;//审核成功
           /* PushEum pushEum = PushEum.USER_HEAD_AUDIT_NO;
            PushBean pushBean = new PushBean();*/
            List<PhotoInfoModel> photoInfoModelList = photoInfoService.getPhotoList(userInfoBean.getId());
            String title = messageConfig.getHeadAuthFailTitle();
                           if (userInfoBean.getAuditResult().equals(UserStatus.USERHEAD_STATUS_APPROVE.getCode().toString())) {//页面审核通过
                    title = messageConfig.getHeadAuthPassTitle();

               /* if(!photoInfoModelList.isEmpty()){
                    PhotoInfoModel photo =photoInfoModelList.get(0);
                    //审核成功  将用户新头像替代旧头像
                    photo.setUrl(photo.getUrlTobe());
                    photo.setUrlTobe("");
                    photo.setUpdateTime(com.topjet.common.util.DateUtil.now());
                    photoInfoService.updatePhoto(photo);
                    TopJetLog.info("更新 用户新头像替代旧头像完成"+"----------------");
                    // 更新钱包头像
                  //  UpdateAccountHeadResponseDTO updateAccountHeadResponseDTO = walletService.updateAccountHead(uModel.getWalletId(), photo.getUrl());
                }else {
                    resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
                    resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
                    return resultBaseMsg;
                }*/
                statusAfter = AuditHistoryConstant.USER_ICON_AUDIT_SUCCESS;//审核通过
                UpdateIconStatusByIdRTS updateIconStatusByIdRTS = new UpdateIconStatusByIdRTS();
                updateIconStatusByIdRTS.setUserId(userInfoBean.getId());
                updateIconStatusByIdRTS.setUserHeadStatus(3);
                userFeignService.updateIconStatusById(updateIconStatusByIdRTS);

            } else if (userInfoBean.getAuditResult().equals(UserStatus.USERHEAD_STATUS_FAILURE.getCode().toString())) {//审核状态----审核失败
                /*if(!photoInfoModelList.isEmpty()){
                    PhotoInfoModel photo =photoInfoModelList.get(0);
                    // 审核失败修改urlTobe 为空
                    photo.setUrl(photo.getUrl());
                    photo.setUrlTobe("");
                    photo.setUpdateTime(com.topjet.common.util.DateUtil.now());
                    photoInfoService.updatePhoto(photo);
                    TopJetLog.info("更新 旧头像不变 新头像清空"+"----------------");
                }*/
                statusAfter = AuditHistoryConstant.USER_ICON__AUDIT_FAIL;//审核失败
                UpdateIconStatusByIdRTS updateIconStatusByIdRTS = new UpdateIconStatusByIdRTS();
                updateIconStatusByIdRTS.setUserId(userInfoBean.getId());
                updateIconStatusByIdRTS.setUserHeadStatus(2);
                userFeignService.updateIconStatusById(updateIconStatusByIdRTS);
            }
            //审核人信息写日志
            UserAuditHistoryModel ahm = new UserAuditHistoryModel();
            ahm.setSourceType(AuditHistoryConstant.AUDIT_HEAD_USER);//用户头像审核
            ahm.setSourceId(uModel.getId());
            ahm.setStatusAfter(statusAfter);//审核状态
            ahm.setRemark(userInfoBean.getAuditResultMsg());
            ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
            ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
            userAuditHistoryService.insert(ahm);
            TopJetLog.info("身份审核日志记录完成" + uModel.getMobile());
            log.info("身份审核日志记录完成" + uModel.getMobile());
               // 更新任务数量
                try {
                    List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
                    if (!taskUser.isEmpty()) {
                        taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.USER_HEAD_AUDIT, userInfoBean1.getId());
                    }else{
                        taskAssignService.updateTasks(0, TaskConstants.USER_HEAD_AUDIT,userInfoBean1.getId());
                    }
                }catch (Exception e){
                    TopJetLog.error("更新用户审核任务数量异常:"+e.getMessage() +e.getClass().getName()+"用户mobile:"+userInfoBean.getMobile());
                }
                //头像审核消息推送

            List<String> userAppTypes = this.getUserAppType(userInfoBean.getId());
            if(userAppTypes != null && userAppTypes.size()>0){
                for (String userAppType : userAppTypes) {
                    List<AppButton> buttonList = new ArrayList<>();
                    AppButton button = new AppButton();
                    button.setText(messageConfig.getMessagePushButton());
                    if (userInfoBean.getAuditResult().equals(UserStatus.USERHEAD_STATUS_FAILURE.getCode().toString())) {
                        button.setAction(AppAction.getAppAction(AppAction.ACTION_OWNER_ICON,userAppType));
                    }else{
                        button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                    }
                    buttonList.add(button);

                    try{
                        if (userInfoBean.getAuditResult().equals(UserStatus.USERHEAD_STATUS_APPROVE.getCode().toString())) {
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"头像认证",title,SystemConstant.BEINGPUSH_OWNER,AppAction.ACTION_OWNER_ICON.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else{
                                pushMessage(userInfoBean.getId(),"头像认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.ACTION_OWNER_ICON.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }else{
                            if(userAppType.equals("2") || userAppType.equals("4")){
                                pushMessage(userInfoBean.getId(),"头像认证",title,SystemConstant.BEINGPUSH_OWNER,AppAction.getAppAction(AppAction.ACTION_OWNER_ICON,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }else {
                                pushMessage(userInfoBean.getId(),"头像认证",title,SystemConstant.BEINGPUSH_DRIVER,AppAction.getAppAction(AppAction.ACTION_OWNER_ICON,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                            }
                        }
                    }catch (Exception e){
                        log.error("发送头像认证消息推送失败");
                    }
                }
            }
            } else {
                throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
            }
        return resultBaseMsg;
    }

    @Override
    public UserInfoModel selectByPrimaryKey(Integer id) {
        return userInfoModelEMapper.selectByPrimaryKey(id);

    }

    @Override
    public List<TaskBucketInfoModel> getTaskSysUser(Integer sysUserId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("deleted",0);
        paramMap.put("invalid",1);
        paramMap.put("sysUserId",sysUserId);
        return taskBucketInfoModelEMapper.selectListByParam(paramMap);
    }

    @Override
    public UserInfoModel selectUserByMobile(String mobiles) {
        return userInfoModelEMapper.selectUserByMobile(mobiles);
    }

    @Override
    public void pushMessage(Integer userId, String title, String text, String pushType, String action, String msgTitle, List<AppButton> buttonList,Integer msgType,Integer releatedId) {
        PushMessageMQBean bean = new PushMessageMQBean();
        bean.setText(text);
        bean.setAction(action);
        bean.setMsgTitle(msgTitle);
        bean.setTitle(title);
        bean.setUserId(userId);
        bean.setPushType(pushType);
        bean.setButton(buttonList);
        bean.setRelatedId(releatedId);
        bean.setMsgType(msgType);
        log.info("MQ消息推送通道：" + QueuesName.QUEUES_PUSH_NAME + " "+JsonUtil.toJSON(bean));
        try{
            rabbitTemplate.convertAndSend(QueuesName.QUEUES_PUSH_NAME,bean);
        }catch (Exception e){
            log.error("MQ消息推送失败");
            e.printStackTrace();
        }
    }

    /*会员管理*/
    @Override
    public void updateUserInfo(UserInfoBean userInfoBean) throws TopjetExceptionHandler {
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        UserOtherInfoModel userOtherInfoModel = userService.findOtherById(userInfoBean.getId());
        UpdateUserInformationByIdRTS updateUserInformationByIdRTS = new UpdateUserInformationByIdRTS();
       // uModel.setId(updateUserInformationByIdRTS.getUserId());//userInfo表中的id
        updateUserInformationByIdRTS.setUserId(userInfoBean.getId());//userOtherInfo表中的userId
        if (uModel != null && userOtherInfoModel != null) {
            if (userInfoBean.getResident3() != null && userInfoBean.getResident3() != "") {
                updateUserInformationByIdRTS.setResident3(cityService.getCityName(userInfoBean.getResident3()));
                updateUserInformationByIdRTS.setResidentCityId(CityUtil.convertToInteger(userInfoBean.getResident3()));
            } else {
                updateUserInformationByIdRTS.setResidentCityId(CityUtil.convertToInteger(userInfoBean.getResident2()));
                updateUserInformationByIdRTS.setResident3("");
            }
            updateUserInformationByIdRTS.setUserType(userInfoBean.getUserType());
            updateUserInformationByIdRTS.setCompanyName(userInfoBean.getCompanyName());
            updateUserInformationByIdRTS.setResident1(cityService.getCityName(userInfoBean.getResident1()));
            /*if (uModel.getStatus().equals(UserStatus.USERSTATUS_DATA_WAIT_REVIEWED.getCode())) {
                uModel.setStatus(UserStatus.USERSTATUS_APPROVED.getCode());
            }*/
            updateUserInformationByIdRTS.setResident2(cityService.getCityName(userInfoBean.getResident2()));
            updateUserInformationByIdRTS.setBusinessAddress(userOtherInfoModel.getBusinessAddress());
            if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity1()) && !userInfoBean.getBusinessAddressCity1().equals("0")) {
                updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));
               // uModel.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));
                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity2()) && !userInfoBean.getBusinessAddressCity2().equals("1")) {
                    updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity2())));
                }
                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity3()) && !userInfoBean.getBusinessAddressCity3().equals("1")) {
                    updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity3())));
                }
            }
            else{
                updateUserInformationByIdRTS.setBusinessAddressCityId(userOtherInfoModel.getBusinessAddressCityId());
            }
            try {
                updateUserInformationByIdRTS.setTelephone(userInfoBean.getTelephone());
                UpdateUserInformationByIdVRU updateUserInformationByIdVRU = userFeignService.updateUserInformationById(updateUserInformationByIdRTS);
            } catch (Exception e){
                log.error("*****************会员管理更新用户资料出错*****************");
                e.printStackTrace();

            }

        }
       /* else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }*/
         this.updateUserBusinessLine(userInfoBean);//修改经营路线表
    }

    void updateUserBusinessLine(UserInfoBean userInfoBean) {
        log.info("*****************开始修改经营路线*****************");
      //  DriverBusinessLineInfoModel driverBusinessLineInfoModel = driverBusinessLineInfoService.getDriverBusinessLineById(userInfoBean.getId());
        UpdateDriverBussinessInfoRTS updateDriverBussinessInfoRTS = new UpdateDriverBussinessInfoRTS();
        updateDriverBussinessInfoRTS.setDriverId(userInfoBean.getId());
        //第一条经营路线
        if (userInfoBean.getBusinessLineFisrtId() != null || userInfoBean.getBusinessLineFisrtId() == null) {
            log.info("*****************第一条经营路线*****************");
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineFisrt1()) && !userInfoBean.getBusinessLineFisrt1().equals("0")) {
                CityModel cityModel = cityService.getFirstCity(userInfoBean.getBusinessLineFisrt1());
                updateDriverBussinessInfoRTS.setBusinessLine1(cityService.getCityModel(userInfoBean.getBusinessLineFisrt1()).getCityName());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineFisrt1()) && userInfoBean.getBusinessLineFisrt1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
                updateDriverBussinessInfoRTS.setBusinessLine2("");
                updateDriverBussinessInfoRTS.setBusinessLine3("");
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(""));
            }

            if (StringUtils.isBlank(userInfoBean.getBusinessLineFisrt2()) || userInfoBean.getBusinessLineFisrt2().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine2("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineFisrt2()) && !userInfoBean.getBusinessLineFisrt2().equals("1")) {
                CityModel cityModel = cityService.getSecondCity(userInfoBean.getBusinessLineFisrt2());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
                updateDriverBussinessInfoRTS.setBusinessLine2(cityService.getCityModel(userInfoBean.getBusinessLineFisrt2()).getCityName());
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineFisrt3()) && !userInfoBean.getBusinessLineFisrt3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(userInfoBean.getBusinessLineFisrt3()));
                updateDriverBussinessInfoRTS.setBusinessLine3(cityService.getCityModel(userInfoBean.getBusinessLineFisrt3()).getCityFullName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineFisrt3()) || userInfoBean.getBusinessLineFisrt3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine3("");
            }
            try {
                updateDriverBussinessInfoRTS.setId(userInfoBean.getBusinessLineFisrtId());
                UpdateDriverBussinessInfoVRU updateDriverBussinessInfoVRU = truckFeignService.updateDriverBussinessInfo(updateDriverBussinessInfoRTS);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        //第二条经营路线
        if (userInfoBean.getBusinessLineSecondId() != null || userInfoBean.getBusinessLineSecondId() == null) {
            log.info("*****************第二条经营路线****************");
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineSecond1()) && userInfoBean.getBusinessLineSecond1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
                updateDriverBussinessInfoRTS.setBusinessLine2("");
                updateDriverBussinessInfoRTS.setBusinessLine3("");
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(""));
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineSecond1()) && !userInfoBean.getBusinessLineSecond1().equals("0")) {
                CityModel cityModel = cityService.getFirstCity(userInfoBean.getBusinessLineSecond1());
                updateDriverBussinessInfoRTS.setBusinessLine1(cityService.getCityModel(userInfoBean.getBusinessLineSecond1()).getCityName());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineSecond1()) || userInfoBean.getBusinessLineSecond1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineSecond2()) && !userInfoBean.getBusinessLineSecond2().equals("1")) {
                CityModel cityModel = cityService.getSecondCity(userInfoBean.getBusinessLineSecond2());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
                updateDriverBussinessInfoRTS.setBusinessLine2(cityService.getCityModel(userInfoBean.getBusinessLineSecond2()).getCityName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineSecond2()) || userInfoBean.getBusinessLineSecond2().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine2("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineSecond3()) && !userInfoBean.getBusinessLineSecond3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(userInfoBean.getBusinessLineSecond3()));
                updateDriverBussinessInfoRTS.setBusinessLine3(cityService.getCityModel(userInfoBean.getBusinessLineSecond3()).getCityFullName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineSecond3()) || userInfoBean.getBusinessLineSecond3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine3("");
            }
            try {
                updateDriverBussinessInfoRTS.setId(userInfoBean.getBusinessLineSecondId());
                UpdateDriverBussinessInfoVRU updateDriverBussinessInfoVRU = truckFeignService.updateDriverBussinessInfo(updateDriverBussinessInfoRTS);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }

        ////第三条经营路线
        if (userInfoBean.getBusinessLineThirdId() != null || userInfoBean.getBusinessLineThirdId() == null) {
            log.info("*****************第三条经营路线****************");
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineThird1()) && userInfoBean.getBusinessLineThird1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
                updateDriverBussinessInfoRTS.setBusinessLine2("");
                updateDriverBussinessInfoRTS.setBusinessLine3("");
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(""));
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineThird1()) && !userInfoBean.getBusinessLineThird1().equals("0")) {
                CityModel cityModel = cityService.getFirstCity(userInfoBean.getBusinessLineThird1());
                updateDriverBussinessInfoRTS.setBusinessLine1(cityService.getCityModel(userInfoBean.getBusinessLineThird1()).getCityName());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineThird1()) || userInfoBean.getBusinessLineThird1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineThird2()) && !userInfoBean.getBusinessLineThird2().equals("1")) {
                CityModel cityModel = cityService.getSecondCity(userInfoBean.getBusinessLineThird2());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
                updateDriverBussinessInfoRTS.setBusinessLine2(cityService.getCityModel(userInfoBean.getBusinessLineThird2()).getCityName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineThird2()) || userInfoBean.getBusinessLineThird2().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine2("");
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineThird2()) || userInfoBean.getBusinessLineThird2().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine3("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineThird3()) && !userInfoBean.getBusinessLineThird3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(userInfoBean.getBusinessLineThird3()));
                updateDriverBussinessInfoRTS.setBusinessLine3(cityService.getCityModel(userInfoBean.getBusinessLineThird3()).getCityName());
            }
            try {
                updateDriverBussinessInfoRTS.setId(userInfoBean.getBusinessLineThirdId());
                UpdateDriverBussinessInfoVRU updateDriverBussinessInfoVRU = truckFeignService.updateDriverBussinessInfo(updateDriverBussinessInfoRTS);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }


        //第四条经营路线
        if (userInfoBean.getBusinessLineForthId() != null || userInfoBean.getBusinessLineForthId() == null) {
            log.info("*****************第四条经营路线****************");
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineForth1()) && userInfoBean.getBusinessLineForth1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
                updateDriverBussinessInfoRTS.setBusinessLine2("");
                updateDriverBussinessInfoRTS.setBusinessLine3("");
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(""));
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineForth1()) && !userInfoBean.getBusinessLineForth1().equals("0")) {
                CityModel cityModel = cityService.getFirstCity(userInfoBean.getBusinessLineForth1());
                updateDriverBussinessInfoRTS.setBusinessLine1(cityService.getCityModel(userInfoBean.getBusinessLineForth1()).getCityName());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineForth1()) || userInfoBean.getBusinessLineForth1().equals("0")) {
                updateDriverBussinessInfoRTS.setBusinessLine1("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineForth2()) && !userInfoBean.getBusinessLineForth2().equals("1")) {
                CityModel cityModel = cityService.getSecondCity(userInfoBean.getBusinessLineForth2());
                updateDriverBussinessInfoRTS.setBusinessLineCityId(cityModel.getAdcode());
                updateDriverBussinessInfoRTS.setBusinessLine2(cityService.getCityModel(userInfoBean.getBusinessLineForth2()).getCityName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineForth2()) || userInfoBean.getBusinessLineForth2().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine2("");
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessLineForth3()) && !userInfoBean.getBusinessLineForth3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLineCityId(CityUtil.convertToInteger(userInfoBean.getBusinessLineForth3()));
                updateDriverBussinessInfoRTS.setBusinessLine3(cityService.getCityModel(userInfoBean.getBusinessLineForth3()).getCityName());
            }
            if (StringUtils.isBlank(userInfoBean.getBusinessLineForth3()) || userInfoBean.getBusinessLineForth3().equals("1")) {
                updateDriverBussinessInfoRTS.setBusinessLine3("");
            }
            try {
                  updateDriverBussinessInfoRTS.setId(userInfoBean.getBusinessLineForthId());
                UpdateDriverBussinessInfoVRU updateDriverBussinessInfoVRU = truckFeignService.updateDriverBussinessInfo(updateDriverBussinessInfoRTS);
            } catch (Exception e){
                log.error("*****************修改经营路线出错****************");
                e.printStackTrace();
            }

        }

    }


    @Override
    public UserInfoModel findUserById(Integer id) {
        UserInfoModel userInfoModel = userInfoModelEMapper.findUserById(id);
        if(userInfoModel != null){
            return userInfoModel;
        }
        return null;
    }

    @Override
    public UserInfoModel findById(Integer id) {
        return userInfoModelEMapper.findById(id);
    }

    @Override
    public UserOtherInfoModel findUserOtherById(Integer userId) {
        return userOtherInfoModelEMapper.findUserOtherById(userId);
    }
    //会员冻结或者解冻
    @Override
    public ResultBaseMsg frozenOrDefrozen(Integer userId, String reditRemark,Integer flag,Integer ubid) throws TopjetExceptionHandler {
        ResultBaseMsg msg = new ResultBaseMsg();
        //ubid是黑名单id     userId是用户Id
        UserInfoModel userInfoModel = userInfoModelEMapper.findById(userId);
        Integer sourceType = AuditHistoryConstant.AUDIT_MEMBER_FROZEN;//初始化会员修改备注
        Integer statusAfter = AuditHistoryConstant.AUDIT_FROZEN;//初始化冻结
        if(userInfoModel == null){//为空
            msg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            msg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            return  msg;
        } else{
            msg.setStatus(ExceptionEnum.E_3.getStatus());
            msg.setMsg(ExceptionEnum.E_3.getMsg());
            //flag  1 解冻 0 冻结
            if(flag == 0){
                // flag = 0 冻结
                FrostUserByIdRTS frostUserByIdRTS = new FrostUserByIdRTS();
                frostUserByIdRTS.setUserId(userId);
                frostUserByIdRTS.setId(ubid);
                frostUserByIdRTS.setRemark(reditRemark);
                statusAfter = AuditHistoryConstant.AUDIT_FROZEN;
                sourceType = AuditHistoryConstant.AUDIT_MEMBER_FROZEN;

               try {
                   userFeignService.frostUserById(frostUserByIdRTS);
               }catch (Exception e){
                   e.printStackTrace();
                   TopJetLog.info("冻结操作失败 :"+userId);
                   return msg;
               }
            } else{
                //flag = 1 解冻
                AbolishFrostUserByIdRTS abolishFrostUserByIdRTS = new AbolishFrostUserByIdRTS();
                abolishFrostUserByIdRTS.setUserId(userId);
                abolishFrostUserByIdRTS.setId(ubid);
                abolishFrostUserByIdRTS.setRemark(reditRemark);
                statusAfter = AuditHistoryConstant.AUDIT_MEMBER_MODIFY;
                sourceType = AuditHistoryConstant.AUDIT_MEMBER_REMARK;
                try {
                    userFeignService.abolishFrostUserById(abolishFrostUserByIdRTS);
                }catch (Exception e){
                    e.printStackTrace();
                    TopJetLog.info("冻结操作失败 :"+userId);
                    return msg;
                }

            }
        }
        try { // todo catch 去掉 handerexception 能拦截到这种异常
            //审核人信息写日志
            UserAuditHistoryModel ahm = new UserAuditHistoryModel();
            ahm.setSourceType(sourceType);//填入冻结日志枚举常量
            ahm.setSourceId(userId);
            ahm.setStatusAfter(statusAfter);
            ahm.setRemark(reditRemark);
            ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
            ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
            ahm.setDeleted(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
            userAuditHistoryModelMapper.insert(ahm);
            msg.setStatus(ExceptionEnum.E_0.getStatus());
            msg.setMsg(ExceptionEnum.E_0.getMsg());
            TopJetLog.info("审核日志记录完成:"+userId);
        } catch (Exception e) {
            TopJetLog.info("审核日志记录异常:"+userId);
        }
        return msg;

    }

    public void saveUserTrail(UserInfoModel uModel, Object object){
        UserTrailModel ut = new UserTrailModel();
        ut.setUserId(uModel.getId());
        ut.setUserAuthStatus(uModel.getUserAuthStatus());
        ut.setUseStatus(uModel.getUseStatus());
//        ut.setBusinessAddress(uModel.getBusinessAddress());
        ut.setMobile(uModel.getMobile());
        ut.setCompanyName(uModel.getCompanyName());
//        ut.setResidentCityId(uModel.getResidentCityId());
        HttpServletRequest request = (HttpServletRequest) object;
        if(!StringUtils.isBlank(request.getParameter("businessPhotoUrl"))){
            ut.setBusinessPhoto(request.getParameter("businessPhotoUrl"));//营业执照图片
        }
        if(!StringUtils.isBlank(request.getParameter("shopFrontPhotoUrl"))){
            ut.setShopFrontPhoto(request.getParameter("shopFrontPhotoUrl"));//门头照图片
        }
        if(!StringUtils.isBlank(request.getParameter("dirverLicenceUrl"))){
            ut.setDriverLicencePhoto(request.getParameter("dirverLicenceUrl"));//	驾驶证图片
        }
        if(!StringUtils.isBlank(request.getParameter("operatingPermitsPhotoUrl"))){
            ut.setOperatingPermitsPhoto(request.getParameter("operatingPermitsPhotoUrl"));//	营运证
        }if(!StringUtils.isBlank(request.getParameter("businessCardUrl"))){
            ut.setBusinessCard(request.getParameter("businessCardUrl"));//	名片
        }if(!StringUtils.isBlank(request.getParameter("bigOrdersUrl"))){
            ut.setBigOrders(request.getParameter("bigOrdersUrl"));//	机打发票
        }
        ut.setCreateBy(SessionUtils.getSysUserSession().getId());
        ut.setCreateTime(com.topjet.common.util.DateUtil.now());
        userTrailModelMapper.insert(ut);
    }
    @Override
    public List<UserAuditHistoryModel> getUserOperationLog(UserAuditHistoryModel userAuditHistoryModel) {
        return userInfoModelEMapper.getUserOperationLog(userAuditHistoryModel);
    }

    private String  createWallet(UserInfoModel userInfoModel){
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("userId",userInfoModel.getId());
        paramMap.put("deleted",CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
        UserOtherInfoModel userOtherInfoModel = userOtherInfoModelEMapper.selectListByParam(paramMap).get(0);


        CreateWalletRTS rts = new CreateWalletRTS();
        if(userInfoModel.getUserNature()==1){
            rts.setName(userInfoModel.getCompanyName()==null?"":userInfoModel.getCompanyName());
            rts.setUserType(2);
        }else{
            rts.setName(userInfoModel.getUsername()==null?"":userInfoModel.getUsername());
            rts.setUserType(1);
        }
        rts.setBusinessAddress(userOtherInfoModel.getBusinessAddress()==null?"":userOtherInfoModel.getBusinessAddress());
        rts.setIdNo(userInfoModel.getIdNo()==null?"":userInfoModel.getIdNo());
        rts.setMobile(userInfoModel.getMobile()==null?"":userInfoModel.getMobile());

        rts.setResident1(userOtherInfoModel.getResident1()==null?"":userOtherInfoModel.getResident1());
        rts.setResident2(userOtherInfoModel.getResident2()==null?"":userOtherInfoModel.getResident2());
        rts.setUserId(userInfoModel.getId()+"");
        rts.setBusinessname(userInfoModel.getCompanyName()==null?"":userInfoModel.getCompanyName());
        rts.setImei(userOtherInfoModel.getRegisteredIMEI()==null?"":userOtherInfoModel.getRegisteredIMEI());
        String userPic = photoInfoService.getUserPhotoInfo(userInfoModel.getId(),PictureServerProperties.PHOTO_TYPE_HEAD_ICON);
        String idCartPic = photoInfoService.getUserPhotoInfo(userInfoModel.getId(),PictureServerProperties.PHOTO_TYPE_ID);
        rts.setUserPic(userPic==null?"":userPic);
        rts.setIdCardpic(idCartPic==null?"":idCartPic);
        CreateWalletVRU wallet = walletFeginService.createWallet(rts);
        if(wallet != null && wallet.getCode()==1){
            log.info("用户:"+userInfoModel.getMobile()+"，生成钱包ID："+wallet.getWalletId());
            return wallet.getWalletId()+"";
        }else{
            return "";
        }
    }

    @Override
    public UserInfoModel findUserByVersion(Integer userId, Integer version) {
        return userInfoModelEMapper.findUserByVersion(userId,version);
    }

    @Override
    public Integer findDeletedByUserId(Integer id) {
        return userInfoModelEMapper.findDeletedByUserId(id);
    }

    @Override
    public List<UserInfoModel> getListByMobile(String mobile) {
        return userInfoModelEMapper.getListByMobile(mobile);
    }

    @Override
    public void updateUser(UserInfoBean userInfoBean) throws TopjetExceptionHandler {
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        UserOtherInfoModel userOtherInfoModel = userService.findOtherById(userInfoBean.getId());
        UpdateUserInformationByIdRTS updateUserInformationByIdRTS = new UpdateUserInformationByIdRTS();
        // uModel.setId(updateUserInformationByIdRTS.getUserId());//userInfo表中的id
        updateUserInformationByIdRTS.setUserId(userInfoBean.getId());//userOtherInfo表中的userId
        if (uModel != null && userOtherInfoModel != null) {
            if (userInfoBean.getResident3() != null && userInfoBean.getResident3() != "") {
                updateUserInformationByIdRTS.setResident3(cityService.getCityName(userInfoBean.getResident3()));
                updateUserInformationByIdRTS.setResidentCityId(CityUtil.convertToInteger(userInfoBean.getResident3()));
            } else {
                updateUserInformationByIdRTS.setResidentCityId(CityUtil.convertToInteger(userInfoBean.getResident2()));
                updateUserInformationByIdRTS.setResident3("");
            }
            updateUserInformationByIdRTS.setUserType(userInfoBean.getUserType());
            updateUserInformationByIdRTS.setCompanyName(userInfoBean.getCompanyName());
            updateUserInformationByIdRTS.setResident1(cityService.getCityName(userInfoBean.getResident1()));
           /* if (uModel.getUseStatus().equals(UserStatus.USERSTATUS_DATA_WAIT_REVIEWED.getCode())) {
                uModel.setUseStatus(UserStatus.USERSTATUS_APPROVED.getCode());
            }*/
            updateUserInformationByIdRTS.setResident2(cityService.getCityName(userInfoBean.getResident2()));
            updateUserInformationByIdRTS.setBusinessAddress(userOtherInfoModel.getBusinessAddress());
            if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity1()) && !userInfoBean.getBusinessAddressCity1().equals("0")) {
                updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));
                // uModel.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));
                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity2()) && !userInfoBean.getBusinessAddressCity2().equals("1")) {
                    updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity2())));
                }
                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity3()) && !userInfoBean.getBusinessAddressCity3().equals("1")) {
                    updateUserInformationByIdRTS.setBusinessAddressCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity3())));
                }
            }
            else{
                updateUserInformationByIdRTS.setBusinessAddressCityId(userOtherInfoModel.getBusinessAddressCityId());
            }
            try {
                updateUserInformationByIdRTS.setTelephone(userInfoBean.getTelephone());
                UpdateUserInformationByIdVRU updateUserInformationByIdVRU = userFeignService.updateUserInformationById(updateUserInformationByIdRTS);
            }
            catch (Exception e){
                e.printStackTrace();
            }

        }
        this.updateUserBusinessLine(userInfoBean);//修改经营路线表
    }
        @Override
        public List<String> getUserAppType(Integer userId) {
            List<String> strList = new ArrayList<>();
            UserPushTokenVO userPushToken = userInfoModelEMapper.getUserPushToken(userId);
            if (userPushToken==null) return strList;
            if(StringUtils.isNotBlank(userPushToken.getDriverToken())){
                strList.add(userPushToken.getDriverToken().split(",")[0]);
            }
            if(StringUtils.isNotBlank(userPushToken.getOwnerToken())){
                strList.add(userPushToken.getOwnerToken().split(",")[0]);
            }
            return strList;
    }

    @Override
    public String getUserAppTokenType(Integer userId) {

        UserPushTokenVO userPushToken = userInfoModelEMapper.getUserPushToken(userId);
        if(userPushToken == null){
            return "";
        }
        return userPushToken.getDriverToken().split(",")[0];
    }

    @Override
    public void updateMobile(UserInfoBean userInfoBean) {
        try {
            userInfoModelMapper.updateMobile(userInfoBean);
        }catch (Exception e){
            log.error("修改配货user手机号存储过程调用失败："+e);
        }
        try {
            changeMobileService.changeMobile(userInfoBean.getOldMobile(),userInfoBean.getNewMobile());
        }catch (Exception e){
            log.error("修改钱包user手机号存储过程调用失败："+e);
        }
    }


    /**
     * 企业认证列表展示
     * @param companyAuditBean
     * @return
     */
    @Override
    public List<CompanyAuditBean> companyAuditList(CompanyAuditBean companyAuditBean) {
        return userInfoModelEMapper.companyAuditList(companyAuditBean);
    }

    /**
     * 企业认证列表count
     * @param companyAuditBean
     * @return
     */
    @Override
    public int getCompanyAuditCount(CompanyAuditBean companyAuditBean) {
        return userInfoModelEMapper.getCompanyAuditCount(companyAuditBean);
    }


    @Override
    public UserInfoBean getCompanyUserInfo(CompanyAuditBean companyAuditBean) {
        log.info("***************开始获取企业认证详情*******************");
        UserInfoBean userInfoBean = new UserInfoBean();
        UserInfoModel userModel = userService.findById(companyAuditBean.getId());
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("userId", userModel.getId());
        UserOtherInfoModel userOtherInfoModel = userOtherInfoModelEMapper.selectListByParam(paramMap).get(0);
        userInfoBean.setId(userOtherInfoModel.getUserId());
        userInfoBean.setCompanyName(userOtherInfoModel.getCompanyName());
        userInfoBean.setIdentityType(userOtherInfoModel.getIdentityType());
        userInfoBean.setCreateTime(userModel.getCreateTime());
        userInfoBean.setCompanyStatus(userModel.getCompanyStatus());
        userInfoBean.setMobile(userModel.getMobile());
        userInfoBean.setUsername(userModel.getUsername());
        userInfoBean.setCreditCode(userOtherInfoModel.getCreditCode());
        userInfoBean.setInternalRemark(userOtherInfoModel.getInternalRemark());
        userInfoBean.setTelephone(userOtherInfoModel.getTelephone());
        userInfoBean.setBusinessAddress(userOtherInfoModel.getBusinessAddress());
        userInfoBean.setBusinessAddressLatitude(userOtherInfoModel.getBusinessAddressLatitude());
        userInfoBean.setBusinessAddressLongitude(userOtherInfoModel.getBusinessAddressLongitude());
        userInfoBean.setBusinessAddressCityId(userOtherInfoModel.getBusinessAddressCityId());
        List<String> city = cityService.getCityIdList(CityUtil.convertToString(userOtherInfoModel.getBusinessAddressCityId()));
        String businessAddress1 = cityService.getCityAddress1(city);
        String businessAddress2 = cityService.getCityAddress2(city);
        String businessAddress3 = cityService.getCityAddress3(city);

        userInfoBean.setBusinessAddressCity1(StringUtils.isBlank(businessAddress1) ? "0" : businessAddress1);
        userInfoBean.setBusinessAddressCity2(businessAddress2);
        userInfoBean.setBusinessAddressCity3(businessAddress3);
        this.getPhotos(userInfoBean,userInfoBean.getId(),0);
        return userInfoBean;
    }

    /**
     * 提交企业认证审核
     * @param userInfoBean
     * @param request
     * @return
     * @throws TopjetExceptionHandler
     */
    @Override
    public ResultBaseMsg updateCompanyDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler {
        log.info("********开始企业认证审核***********"+userInfoBean.getMobile()+"******企业名称******"+userInfoBean.getCompanyName());
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        //检查当前数据是否 信息已过期
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",userInfoBean.getId());
        paramMap.put("userNature",userInfoBean.getUserNature());
        List<UserInfoModel> userInfoModels = userInfoModelEMapper.selectListByParam(paramMap);
        if(userInfoModels == null || userInfoModels.size()<1){
            resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            return resultBaseMsg;
        }
        //根据id查询 数据 再去update
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        UserOtherInfoModel userOtherInfoModel = userService.findOtherById(userInfoBean.getId());
      if(uModel != null  &&  !userInfoBean.getCompanyStatus().equals(3)){
          log.info("********进入审核***********");
        Integer statusAfter = AuditHistoryConstant.COMPANY_AUDIT_SUCCESS; //默认审核通过
         if(userInfoBean.getAuditResult().equals(UserStatus.USERCOMPANY_STATUS_APPROVE.getCode().toString())){
            //审核通过
             uModel.setCompanyStatus(UserStatus.USERCOMPANY_STATUS_APPROVE.getCode());
         }else{
             statusAfter = AuditHistoryConstant.COMPANY_AUDIT_FAILED;
             uModel.setCompanyStatus(UserStatus.USERCOMPANY_STATUS_FAILURE.getCode());
         }

          String walletId = ""; //钱包id
        //推送文案
        String messageTitle = messageConfig.getCompanyAuditFailTitle();
        //审核成功
        if (userInfoBean.getAuditResult().equals(UserStatus.USERCOMPANY_STATUS_APPROVE.getCode().toString())) {
            messageTitle = messageConfig.getCompanyAuditPassTitle();
            try {
                log.info("企业用户请求钱包创建开始" + uModel.getMobile());
                uModel.setCompanyName(userInfoBean.getCompanyName());
                uModel.setIdNo(userInfoBean.getCreditCode());
                walletId= this.createWallet(uModel);
                log.info("企业用户请求钱包创建结束" + uModel.getMobile());
            } catch (Exception e) {
                log.error("用户请求钱包创建钱包异常" + uModel.getMobile() + e.getMessage() + e.getClass().getName());
                resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
                resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
                return resultBaseMsg;
            }
        }
        //----------------------------------------------
       log.info("企业用户审核调用服务端接口开始" + uModel.getMobile());

          if (userInfoBean.getBusinessPhotoUrl().startsWith("http")) {
              userInfoBean.setBusinessPhotoUrl(null);
          }else{
              userInfoBean.setBusinessPhotoUrl(userInfoBean.getBusinessPhotoUrl().substring(userInfoBean.getBusinessPhotoUrl().indexOf(",") + 1));
          }
          if (userInfoBean.getShopFrontPhotoUrl().startsWith("http")) {
              userInfoBean.setShopFrontPhotoUrl(null);
          }else{
              userInfoBean.setShopFrontPhotoUrl(userInfoBean.getShopFrontPhotoUrl().substring(userInfoBean.getShopFrontPhotoUrl().indexOf(",")+1));
          }

        UpdateCompanyStatusByIdRTS updateCompanyStatusByIdRTS = new UpdateCompanyStatusByIdRTS();
        updateCompanyStatusByIdRTS.setCompanyStatus(uModel.getCompanyStatus());

        updateCompanyStatusByIdRTS.setId(uModel.getId());

        try{
            if(!walletId.equals("") ){
                updateCompanyStatusByIdRTS.setWalletId(Integer.parseInt(walletId));
            }else{
                updateCompanyStatusByIdRTS.setWalletId(0);
            }

        }catch (Exception e){
            log.error("用户："+uModel.getMobile()+"生成钱包账号失败！！！");
            resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_3.getMsg());
            return resultBaseMsg;
        }


        updateCompanyStatusByIdRTS.setVersion(uModel.getVersion());
        updateCompanyStatusByIdRTS.setCompanyName(userInfoBean.getCompanyName());
        updateCompanyStatusByIdRTS.setUsername(userInfoBean.getUsername());
        updateCompanyStatusByIdRTS.setCreditCode(userInfoBean.getCreditCode());
        updateCompanyStatusByIdRTS.setTelephone(userInfoBean.getTelephone());
         //经营城市id
        if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity1()) && !userInfoBean.getBusinessAddressCity1().equals("0")) {
            updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));

            if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity2()) && !userInfoBean.getBusinessAddressCity2().equals("1")) {
                updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity2())));
            }
            if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity3()) && !userInfoBean.getBusinessAddressCity3().equals("1")) {
                updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity3())));
            }
        } else{
            updateCompanyStatusByIdRTS.setRegisteredCityId(userOtherInfoModel.getBusinessAddressCityId());
        }
        updateCompanyStatusByIdRTS.setInternalRemark(userInfoBean.getInternalRemark());
        updateCompanyStatusByIdRTS.setBusinessAddress(userInfoBean.getBusinessAddress());
        updateCompanyStatusByIdRTS.setBusinessAddressLongitude(userInfoBean.getBusinessAddressLongitude());
        updateCompanyStatusByIdRTS.setBusinessAddressLatitude(userInfoBean.getBusinessAddressLatitude());
        updateCompanyStatusByIdRTS.setBusiness_license_photo(userInfoBean.getBusinessPhotoUrl());
        updateCompanyStatusByIdRTS.setDoor_photo(userInfoBean.getShopFrontPhotoUrl());


        UpdateCompanyStatusByIdVRU vru;
        try {
            vru = userFeignService.updateCompanyStatusById(updateCompanyStatusByIdRTS);
            if(vru.getCode() != 1){
                log.info("服务端企业认证返回码："+vru.getCode()+"返回信息+"+vru.getMsg());
                log.error("用户："+uModel.getMobile()+"企业认证审核失败！！！");
                resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
                resultBaseMsg.setMsg(vru.getMsg());
                return resultBaseMsg;
            }
            log.info("更新企业用户信息完成" + uModel.getMobile());
        } catch (Exception e) {
            resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
            log.error(e.getMessage());
            log.error("更新企业用户信息异常" + uModel.getMobile() + e.getMessage() + e.getClass().getName());
            return resultBaseMsg;
        }
        log.info("企业用户审核调用服务端接口结束" + uModel.getMobile());
        //----------------------------------------------

      /*  更新任务数量*/
        try {
            List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
            if (!taskUser.isEmpty()) {
                taskAssignService.updateTasks(taskUser.get(0).getSysUserId(), TaskConstants.COMPANY_USER_AUDIT, userInfoBean.getId());
            } else {
                taskAssignService.updateTasks(0, TaskConstants.COMPANY_USER_AUDIT, userInfoBean.getId());
            }
        } catch (Exception e) {
            TopJetLog.error("更新企业用户审核任务数量异常:" + e.getMessage() + e.getClass().getName() + "用户mobile:" + userInfoBean.getMobile());
        }
        //审核人信息写日志
        UserAuditHistoryModel ahm = new UserAuditHistoryModel();
        ahm.setSourceType(AuditHistoryConstant.AUDIT_COMPANY);
        ahm.setSourceId(uModel.getId());
        ahm.setStatusAfter(statusAfter);
        ahm.setRemark(userInfoBean.getAuditResultMsg());
        ahm.setCreateBy(SessionUtils.getSysUserSession().getId());
        ahm.setAuditName(SessionUtils.getSysUserSession().getNickName());
        ahm.setCreateTime(DateUtil.now());
        userAuditHistoryService.insert(ahm);
        log.info("审核日志记录完成" + uModel.getMobile());

        //------------------------------------------------

          if(userInfoBean.getAuditResult().equals(UserStatus.USERCOMPANY_STATUS_APPROVE.getCode().toString())){
              log.info("开始调用tms接口********************" );
              //根据uid 查询用户信息

              UserInfoBean userinfo = userService.getTmsUserInfo(userInfoBean.getId());
              CompanyRequestDTO companyRequestDTO =new CompanyRequestDTO();
              try {
                  companyRequestDTO.setOuterAccount(userinfo.getId()+"");
                  companyRequestDTO.setEnterpriseName(userinfo.getCompanyName());
                  companyRequestDTO.setMobile(userinfo.getMobile());
                  companyRequestDTO.setRealName(userinfo.getUsername());
                  companyRequestDTO.setRegDateTime(DateUtil.DateToString(userinfo.getCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                  companyRequestDTO.setRegCity(cityService.getCityName(userinfo.getRegisteredCityId()+""));
                  companyRequestDTO.setBusinessLicense(userinfo.getBusinessPhotoUrlTobe());
                  companyRequestDTO.setBusinessFacade(userinfo.getShopFrontPhotoUrlTobe());
                  companyRequestDTO.setTelephone(userinfo.getTelephone());
                  if(userinfo.getBusinessAddressCityId() ==0){
                      companyRequestDTO.setProvince("");
                      companyRequestDTO.setCity("");
                  }else{
                      companyRequestDTO.setProvince(cityService.getFirstCity(userinfo.getBusinessAddressCityId()+"").getCityName());
                      companyRequestDTO.setCity(cityService.getSecondCity(userinfo.getBusinessAddressCityId()+"").getCityName());
                  }

                  companyRequestDTO.setArea(cityService.getCityName(userinfo.getBusinessAddressCityId()+""));
                  companyRequestDTO.setEntAddress(userinfo.getBusinessAddress());
                  companyRequestDTO.setLongitude(userinfo.getBusinessAddressLongitude()+"");
                  companyRequestDTO.setLatitude(userinfo.getBusinessAddressLatitude()+"");
                  companyRequestDTO.setWalletId(userinfo.getWalletId()+"");
                  companyRequestDTO.setPassword(userinfo.getUserPass());
                  companyRequestDTO.setEntCode(userinfo.getCreditCode());
                  companyRequestDTO.setAuditUser(userinfo.getAuditName());
                  companyRequestDTO.setAuditTime(DateUtil.DateToString(userinfo.getAuditCreateTime(),"yyyy-MM-dd HH:mm:ss"));
                  companyRequestDTO.setAuditRemark(userinfo.getRemark());
                  companyRequestDTO.setRemark(userinfo.getInternalRemark());
              }catch (Exception e){
                  log.error("调用tms接口参数 set值时报错********************" );
              }
              try {
                  updateTms(companyRequestDTO);
              }catch (Exception e){
                  log.error("调用tms接口出错********************" +e.getMessage());
                  e.printStackTrace();
              }
              log.info("调用tms接口结束********************" );
              //------------------------------------------------
          }


        //发送消息推送   ????
        List<String> userAppTypes = this.getUserAppType(userInfoBean.getId());
        if(userAppTypes != null && userAppTypes.size()>0){
            for (String userAppType : userAppTypes) {
                List<AppButton> buttonList = new ArrayList<>();

                AppButton button = new AppButton();
                button.setText(messageConfig.getMessagePushButton());//   我知道了

                if (userInfoBean.getAuditResult().equals(UserStatus.USERCOMPANY_STATUS_FAILURE.getCode().toString())) {
                    //认证失败
                    button.setAction(AppAction.getAppAction(AppAction.ACTION_COMPANY_AUTHENTICATION,userAppType));
                }else{
                    button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                }
                buttonList.add(button);
                try{
                    if (userInfoBean.getAuditResult().equals(UserStatus.USERCOMPANY_STATUS_APPROVE.getCode().toString())) {
                        //审核成功
                        if(userAppType.equals("2") || userAppType.equals("4")){
                            pushMessage(userInfoBean.getId(),"企业认证",messageTitle,SystemConstant.BEINGPUSH_OWNER,AppAction.ACTION_OWNER_EMPTY.getAction(),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                        }
                    }else{
                        //审核失败
                        if(userAppType.equals("2") || userAppType.equals("4")){
                            pushMessage(userInfoBean.getId(),"企业认证",messageTitle,SystemConstant.BEINGPUSH_OWNER,AppAction.getAppAction(AppAction.ACTION_COMPANY_AUTHENTICATION,userAppType),"",buttonList,PushConstant.MESSAGETYPE_SYSTEM,0);
                        }
                    }
                    log.info("发送企业认证消息推送成功");
                }catch (Exception e){
                    log.error("发送企业认证消息推送失败");
                }
            }
        }

        try {
            SendSmsRTS sendSmsRTS = new SendSmsRTS();
            sendSmsRTS.setMobileNum(uModel.getMobile());
            sendSmsRTS.setContent(messageTitle);
            SendSmsVRU sendSmsVRU = basicFeignService.sendSms(sendSmsRTS);
        }catch (Exception e){
            log.error("*****发送企业审核认证短信失败*******"+uModel.getMobile());
            e.printStackTrace();
        }
    }else {
        throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
      }
        return resultBaseMsg;

    }



    public CompanyResponseDTO updateTms(CompanyRequestDTO companyRequestDTO) throws Exception{
        log.info("*****  updateTms  *******");
        CompanyResponseDTO responseDTO = new CompanyResponseDTO();
        String requestUrl = systemConfig.getTmsRquestUrl();
        RequestTms request = new RequestTms();
        request.setAppId(systemConfig.getTmsAppid());
        Gson gson  = new Gson();
        String body = gson.toJson(companyRequestDTO);
        request.setParameter(body);
        String jsonData = JsonUtil.toJSON(request);
        log.info("*****调用tms接口  url*******"+requestUrl);
        log.info("*****调用tms接口  参数*******"+jsonData);
        String enString = AESEncodeUtil.encrypt(jsonData);
        log.info("*****调用tms接口  加密后参数*******"+enString);
        // 发送 POST 请求
        log.info("*****调用tms接口 发送 POST 请求");
        String stringObject = HttpUtils.httpPost(requestUrl, enString);
        log.info("*****调用tms接口  POST 请求结束");
        stringObject =stringObject.replace("\"","");
        String DeString = AESEncodeUtil.decrypt(stringObject);
        log.info("*****解密前的结果*******"+stringObject);
        log.info("*****解密后的结果*******"+DeString);
        if(!org.apache.commons.lang3.StringUtils.isEmpty(DeString)){
            if(DeString == "" ||DeString == null){
                return responseDTO;
            } else{
                //1、使用JSONObject
                JSONObject jsonObject=JSONObject.fromObject(DeString);
                responseDTO=(CompanyResponseDTO)JSONObject.toBean(jsonObject, CompanyResponseDTO.class);
                log.info("*****调用后 code*******"+responseDTO.getResultCode());
                TmsLogInfoModel tmsLogInfoModel = new TmsLogInfoModel();
                if(responseDTO.getResultCode() !=200){
                    tmsLogInfoModel.setSuccess(0);
                }else{
                    tmsLogInfoModel.setSuccess(1);
                }
                tmsLogInfoModel.setRequest("\'"+jsonData+"\'");
                tmsLogInfoModel.setResponse("\'"+DeString+"\'");
                tmsLogInfoModel.setType("\'"+requestUrl+"\'");
                tmsLogInfoModel.setInOrOut(1);
                tmsLogInfoModel.setCreateTime(DateUtil.now());
                tmsLogInfoModel.setCostTime(0L);
                insert(tmsLogInfoModel);
                return responseDTO;
            }
        }
        return responseDTO;
    }



    //记录日志
    public  void  insert(TmsLogInfoModel tmsLogInfoModel){
        log.info("*****tms接口调用失败  记录日志*******");
        tmsLogInfoModelMapper.insertTmsLog(tmsLogInfoModel);


    }




    /**
     * 维护企业提交
     * @param userInfoBean
     * @param request
     * @return
     * @throws TopjetExceptionHandler
     */
    @Override
    public ResultBaseMsg updateDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler{
        log.info("********开始维护企业***********"+userInfoBean.getMobile()+"******企业名称******"+userInfoBean.getCompanyName());
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
        resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        //检查当前数据是否 信息已过期
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("id",userInfoBean.getId());
        paramMap.put("userNature",userInfoBean.getUserNature());
        List<UserInfoModel> userInfoModels = userInfoModelEMapper.selectListByParam(paramMap);
        if(userInfoModels == null || userInfoModels.size()<1){
            resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            return resultBaseMsg;
        }
        //根据id查询 数据 再去update
        UserInfoModel uModel = userService.findById(userInfoBean.getId());
        UserOtherInfoModel userOtherInfoModel = userService.findOtherById(userInfoBean.getId());
        if(uModel != null  &&  !userInfoBean.getCompanyStatus().equals(3)){
            log.info("********进入维护***********");
            log.info("企业用户维护调用服务端接口开始" + uModel.getMobile());
            if (userInfoBean.getBusinessPhotoUrl().startsWith("http")) {
                userInfoBean.setBusinessPhotoUrl(null);
            }else{
                userInfoBean.setBusinessPhotoUrl(userInfoBean.getBusinessPhotoUrl().substring(userInfoBean.getBusinessPhotoUrl().indexOf(",") + 1));
            }
            if (userInfoBean.getShopFrontPhotoUrl().startsWith("http")) {
                userInfoBean.setShopFrontPhotoUrl(null);
            }else{
                userInfoBean.setShopFrontPhotoUrl(userInfoBean.getShopFrontPhotoUrl().substring(userInfoBean.getShopFrontPhotoUrl().indexOf(",")+1));
            }

            UpdateCompanyStatusByIdRTS updateCompanyStatusByIdRTS = new UpdateCompanyStatusByIdRTS();
            updateCompanyStatusByIdRTS.setId(uModel.getId());
            updateCompanyStatusByIdRTS.setVersion(uModel.getVersion());
            updateCompanyStatusByIdRTS.setCompanyName(userInfoBean.getCompanyName());
            updateCompanyStatusByIdRTS.setUsername(userInfoBean.getUsername());
            updateCompanyStatusByIdRTS.setCreditCode(userInfoBean.getCreditCode());
            updateCompanyStatusByIdRTS.setTelephone(userInfoBean.getTelephone());
            //经营城市id
            if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity1()) && !userInfoBean.getBusinessAddressCity1().equals("0")) {
                updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity1())));

                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity2()) && !userInfoBean.getBusinessAddressCity2().equals("1")) {
                    updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity2())));
                }
                if (!StringUtils.isBlank(userInfoBean.getBusinessAddressCity3()) && !userInfoBean.getBusinessAddressCity3().equals("1")) {
                    updateCompanyStatusByIdRTS.setRegisteredCityId(CityUtil.convertToInteger((userInfoBean.getBusinessAddressCity3())));
                }
            } else{
                updateCompanyStatusByIdRTS.setRegisteredCityId(userOtherInfoModel.getBusinessAddressCityId());
            }
            updateCompanyStatusByIdRTS.setInternalRemark(userInfoBean.getInternalRemark());
            updateCompanyStatusByIdRTS.setBusinessAddress(userInfoBean.getBusinessAddress());
            updateCompanyStatusByIdRTS.setBusinessAddressLongitude(userInfoBean.getBusinessAddressLongitude());
            updateCompanyStatusByIdRTS.setBusinessAddressLatitude(userInfoBean.getBusinessAddressLatitude());
            updateCompanyStatusByIdRTS.setBusiness_license_photo(userInfoBean.getBusinessPhotoUrl());
            updateCompanyStatusByIdRTS.setDoor_photo(userInfoBean.getShopFrontPhotoUrl());

            UpdateCompanyStatusByIdVRU vru;
            try {
                vru = userFeignService.updateCompanyStatusById(updateCompanyStatusByIdRTS);
                if(vru.getCode() != 1){
                    log.info("服务端企业维护返回码："+vru.getCode()+"返回信息+"+vru.getMsg());
                    log.error("用户："+uModel.getMobile()+"企业维护失败！！！");
                    resultBaseMsg.setStatus(ExceptionEnum.E_3.getStatus());
                    resultBaseMsg.setMsg(vru.getMsg());
                    return resultBaseMsg;
                }
                log.info("更新企业用户信息完成" + uModel.getMobile());
            } catch (Exception e) {
                resultBaseMsg.setStatus(ExceptionEnum.E_38.getStatus());
                resultBaseMsg.setMsg(ExceptionEnum.E_38.getMsg());
                log.error(e.getMessage());
                log.error("更新企业用户信息异常" + uModel.getMobile() + e.getMessage() + e.getClass().getName());
                return resultBaseMsg;
            }
            log.info("企业维护调用服务端接口结束" + uModel.getMobile());
        }else {
            throw new TopjetExceptionHandler(ExceptionEnum.E_DATA_EXPIRED.getStatus(), ExceptionEnum.E_DATA_EXPIRED.getMsg());
        }
        return resultBaseMsg;

    }




}
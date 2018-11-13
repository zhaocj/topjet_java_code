package com.topjet.manage.controller;

import com.topjet.common.ResultBaseMsg;
import com.topjet.common.SessionUtils;
import com.topjet.common.auth.service.IDCheckResult;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.common.topjetlog.TopJetLog;
import com.topjet.manage.constants.TaskConstants;
import com.topjet.manage.controller.base.BaseController;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.mapper.readMapper.UserAuditHistoryModelEMapper;
import com.topjet.manage.mapper.writeMapper.UserAuditHistoryModelMapper;
import com.topjet.manage.service.UserInfoService;
import com.topjet.manage.service.UserService;
import com.topjet.user.constant.AuditHistoryConstant;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author zhaocj
 * @create 2017-08-08 11:05
 **/
@Controller
@RequestMapping("/memberAudit/")
public class MemberAuditController extends BaseController {

    private final static Logger log = Logger.getLogger(MemberAuditController.class);

    @Autowired
    private UserService userService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private UserAuditHistoryModelMapper userAuditHistoryModelMapper;

    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;

    @Resource(name = "stringRedisTemplate")
    private RedisTemplate<String,String> stringRedisTemplate;

    private final ReentrantLock lock = new ReentrantLock();



    @RequestMapping("list")
    @ResponseBody
    public Object dataList(MemberAuditListRequestBean memberAuditListRequestBean) throws Exception {
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
        PaginationBean bean = new PaginationBean();
        memberAuditListRequestBean.setRegisterOrAuditFlag("1");
        boolean taskFlag = false;
        if (taskUser != null) {
            for (TaskBucketInfoModel tb : taskUser) {
                if (tb.getType().equals(TaskConstants.USER_REGISTER_AUDIT)) {
                    taskFlag = true;
                    break;
                }
            }
        }
        if (taskFlag) {
            memberAuditListRequestBean.setFlag("1");
            memberAuditListRequestBean.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }
        List<MemberAuditListResponseBean> memberAuditList = userInfoService.getMemberAuditList(memberAuditListRequestBean);
        int total = userInfoService.getMemberAuditListCount(memberAuditListRequestBean);
        bean.setRows(memberAuditList);
        bean.setTotal(total);
        return bean;
    }

    /**
     * 展示用户详情
     *
     * @param userInfoBean
     * @param request
     * @return
     * @throws Exception
     */
    @RequestMapping("memberDetail")
    public ModelAndView modifiedInfo(UserInfoBean userInfoBean, HttpServletRequest request, UserAuditHistoryModel auditHistoryModel) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/memberAudit/memberDetail");
        userInfoBean = userInfoService.getUserInfoBean(userInfoBean.getId(), 2);
        auditHistoryModel.setSourceId(userInfoBean.getId());
        auditHistoryModel.setSourceType(1);
        List<UserAuditHistoryModel> dataOne = userService.getOperation(auditHistoryModel);
        mv.addObject("userInfoBean", userInfoBean);
        mv.addObject("memberDetails", dataOne);
        mv.addObject("type", "实名认证审核");
        return mv;
    }

    /**
     * 审核用户实名认证
     *
     * @param userInfoBean
     * @return
     * @throws Exception
     */
    @RequestMapping("verifyIdNo")
    @ResponseBody
    public ResultBaseMsg verifyIdNo(UserInfoBean userInfoBean) throws Exception {

        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        UserInfoVerifiedReturn userInfoVerifiedReturn = new UserInfoVerifiedReturn();

        // 校验用户身份证号是否已注册
        Integer idNoUserId = userService.idNoIsEmptyApproved(userInfoBean.getIdNo());
        if (idNoUserId != null && !idNoUserId.equals(userInfoBean.getId())) {
            userInfoVerifiedReturn.setIdCheckResult(IDCheckResult.AUTH_USER_EXISTING);
        } else {
            try {
                userInfoVerifiedReturn = userInfoService.auditAndUpdateIdinfo(userInfoBean);
            } catch (TopjetErrorCodeException e) {
                log.error("**************认证失败********************"+userInfoBean.getMobile());
                resultBaseMsg.setMsg(ExceptionEnum.E_25.getMsg());
                return resultBaseMsg;
            }
        }
        // 校验本地 和校验第三方  两者只能一种  所以用||
        if (userInfoVerifiedReturn != null && ((userInfoVerifiedReturn.getIdCheckResult()!= null && userInfoVerifiedReturn.getIdCheckResult().equals(IDCheckResult.AUTH_ID_SUCC)) || ( userInfoVerifiedReturn.getResponseDTO()!= null && userInfoVerifiedReturn.getResponseDTO().getCompStatus().equals(IDCheckResult.AUTH_ID_SUCC.getCode())))) {
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());//身份证号与姓名验证通过
        } else {
            resultBaseMsg.setStatus(ExceptionEnum.E_25.getStatus());//身份证号与姓名验证不通过
        }
        if(userInfoVerifiedReturn.getResponseDTO()!= null){
            resultBaseMsg.setMsg("校验结果 ："+userInfoVerifiedReturn.getResponseDTO().getCompResult());
        }

        if (userInfoVerifiedReturn.getIdCheckResult() != null) {
            resultBaseMsg.setMsg(userInfoVerifiedReturn.getIdCheckResult().getValue());
        }

        return resultBaseMsg;
    }

    /**
     * 实名认证审核
     * @param userInfoBean
     * @return
     * @throws TopjetExceptionHandler
     */
    @RequestMapping("updateUserInfo")
    @ResponseBody
    public ResultBaseMsg updateUserInfo(UserInfoBean userInfoBean) throws TopjetExceptionHandler {

        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        lock.lock();
        try {
            resultBaseMsg = userInfoService.updateMemberInfo(userInfoBean);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            TopJetLog.error("用户注册审核出现异常:" + e.getMessage() + e.getClass().getName() + "用户mobile:" + userInfoBean.getMobile());
        } finally {
            lock.unlock();
        }

        return resultBaseMsg;
    }

    /**
     * 会员身份审核列表
     *
     * @param memberAuditListRequestBean
     * @return
     * @throws Exception
     */
    @RequestMapping("auditList")
    @ResponseBody
    public Object auditList(MemberAuditListRequestBean memberAuditListRequestBean) throws Exception {
        PaginationBean bean = new PaginationBean();
        memberAuditListRequestBean.setRegisterOrAuditFlag("2");
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者

        boolean taskFlag = false;
        if (taskUser != null && taskUser.size() > 0) {
            for (TaskBucketInfoModel tb : taskUser) {
                if (tb.getType().equals(TaskConstants.USER_ID_AUDIT)) {
                    taskFlag = true;
                    break;
                }
            }
        }

        if (taskFlag) {
            memberAuditListRequestBean.setFlag("2");
            memberAuditListRequestBean.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }

        List<MemberAuditListResponseBean> memberAuditList = userInfoService.getMemberAuditList(memberAuditListRequestBean);
        int total = userInfoService.getMemberAuditListCount(memberAuditListRequestBean);
        bean.setRows(memberAuditList);
        bean.setTotal(total);
        return bean;
    }

    /**
     * 展示用户身份认证详情
     *
     * @param userInfoBean
     * @param request      mobile不为空表示为全局搜索
     * @return
     * @throws Exception
     */
    @RequestMapping("auditIDDetail")
    public Object auditIDDetail(UserInfoBean userInfoBean, HttpServletRequest request, UserAuditHistoryModel auditHistoryModel) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/memberAudit/IDCardDetail");
        userInfoBean = userInfoService.getUserInfoBean(userInfoBean.getId(), 3);
        // UserInfoBean IDCardDetails = userInfoService.getIDCardOperation(userInfoBean.getId());
        auditHistoryModel.setSourceId(userInfoBean.getId());
        auditHistoryModel.setSourceType(2);
        List<UserAuditHistoryModel> dataOne = this.getOperation(auditHistoryModel);
        mv.addObject("IDCardDetails", dataOne);
        mv.addObject("userInfoBean", userInfoBean);
        mv.addObject("type", "身份认证审核");
        return mv;
    }

    //身份审核操作日志
    private List<UserAuditHistoryModel> getOperation(UserAuditHistoryModel auditHistoryModel) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sourceId", auditHistoryModel.getSourceId());
        paramMap.put("statusAfter", "3,4");
        paramMap.put("deleted", 0);
        List<UserAuditHistoryModel> dataList = userAuditHistoryModelEMapper.listHistory(paramMap);
        return dataList;
    }

    //头像认证审核操作日志
    private List<UserAuditHistoryModel> getHeadOperation(UserAuditHistoryModel auditHistoryModel) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("sourceId", auditHistoryModel.getSourceId());
        paramMap.put("sourceType", AuditHistoryConstant.AUDIT_HEAD_USER);
        paramMap.put("deleted", 0);
        List<UserAuditHistoryModel> dataList = userAuditHistoryModelEMapper.listHistory(paramMap);
        return dataList;
    }

    /*
   * 身份认证审核
   * */
    @RequestMapping("updateIDCardInfo")
    @ResponseBody
    public ResultBaseMsg updateIDCardInfo(UserInfoBean userInfoBean, HttpServletRequest request) throws Exception {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        try {
            resultBaseMsg = userInfoService.updateUserIDInfo(userInfoBean, request);
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            resultBaseMsg.setStatus(ExceptionEnum.E_DATA_EXPIRED.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_DATA_EXPIRED.getMsg());
            TopJetLog.error("审核用户身份资料出错:" + e.getMessage() + e.getClass().getName() + "用户mobile:" + userInfoBean.getMobile());
        }
        return resultBaseMsg;
    }

    @ResponseBody
    @RequestMapping("headList")
    public Object headList(MemberAuditListRequestBean memberAuditListRequestBean) {
        PaginationBean bean = new PaginationBean();
        List<TaskBucketInfoModel> taskUser = SessionUtils.getAssignSysUserSession();//是否为分配任务者
        boolean taskFlag = false;
        if (taskUser != null && taskUser.size() > 0) {
            for (TaskBucketInfoModel taskBucketInfoModel : taskUser) {
                if (taskBucketInfoModel.getType().equals(TaskConstants.USER_HEAD_AUDIT)) {
                    taskFlag = true;
                    break;
                }
            }
        }

        if (taskFlag) {
            memberAuditListRequestBean.setFlag("3");
            memberAuditListRequestBean.setSysUserId(taskUser.get(0).getSysUserId().toString());
        }
        List<MemberAuditListResponseBean> memberHeadList = userInfoService.getHeadList(memberAuditListRequestBean);
        int total = userInfoService.getHeadCount(memberAuditListRequestBean);
        bean.setRows(memberHeadList);
        bean.setTotal(total);
        return bean;
    }

    //头像详情
    @RequestMapping("headDetail")
    public ModelAndView headDetailInfo(UserInfoBean userInfoBean, HttpServletRequest request, MemberAuditListRequestBean memberAuditListRequestBean, UserAuditHistoryModel auditHistoryModel) throws Exception {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("view/memberAudit/headDetail");
        userInfoBean = userInfoService.getUserInfo(userInfoBean.getId());
        auditHistoryModel.setSourceId(userInfoBean.getId());
        auditHistoryModel.setSourceType(3);
        List<UserAuditHistoryModel> dataOne = this.getHeadOperation(auditHistoryModel);
        mv.addObject("headDetails", dataOne);
        mv.addObject("userInfoBean", userInfoBean);
        mv.addObject("type", "头像认证审核");
        return mv;
    }

    /*
   * 头像认证审核
   * */
    @RequestMapping("updateHeadDetail")
    @ResponseBody
    public ResultBaseMsg updateHeadDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws Exception {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        try {
            resultBaseMsg = userInfoService.updateHeadDetail(userInfoBean, request);//头像审核
            resultBaseMsg.setStatus(ExceptionEnum.E_0.getStatus());
            resultBaseMsg.setMsg(ExceptionEnum.E_0.getMsg());
        } catch (Exception e) {
            e.printStackTrace();
        }
        return resultBaseMsg;
    }

    @RequestMapping("/auditLock")
    @ResponseBody
    public ResultBaseMsg auditLock(HttpServletRequest request){
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        String auditDataId = "Data_"+request.getParameter("auditDataId");
        String userId = SessionUtils.getSysUserSession().getId()+"";
        String userInfo = SessionUtils.getSysUserSession().getId()+"_"+SessionUtils.getSysUserSession().getNickName();

        ValueOperations<String, String> opsForValue = this.stringRedisTemplate.opsForValue();
        String lockMessage = opsForValue.get(auditDataId);
        if(lockMessage == null){
            opsForValue.set(auditDataId,userInfo);
            stringRedisTemplate.expire(auditDataId,5*60*1000,TimeUnit.MILLISECONDS);
            resultBaseMsg.setStatus("0");
        }else{
            String[] userInfoArr = lockMessage.split("_");
            if(!userId.equals(userInfoArr[0])){
                resultBaseMsg.setStatus("1");
                resultBaseMsg.setMsg(userInfoArr[1]);
            }else{
                resultBaseMsg.setStatus("0");
            }
        }
        return resultBaseMsg;
    }


}

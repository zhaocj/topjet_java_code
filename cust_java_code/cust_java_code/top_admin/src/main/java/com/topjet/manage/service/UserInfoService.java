package com.topjet.manage.service;

import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.common.ResultBaseMsg;
import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.TopjetException;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.manage.domain.bean.*;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.domain.model.UserOtherInfoModel;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by bjb074 on 2017/8/9.
 */
public interface UserInfoService {

    public List<MemberAuditListResponseBean> getMemberAuditList(MemberAuditListRequestBean memberAuditListRequestBean);

    public int getMemberAuditListCount(MemberAuditListRequestBean memberAuditListRequestBean);

    public UserInfoBean getUserInfoBean(Integer userId, int flag);

    UserInfoVerifiedReturn auditAndUpdateIdinfo(UserInfoBean  userInfoBean) throws TopjetErrorCodeException, TopjetException;

    public ResultBaseMsg updateMemberInfo(UserInfoBean  userInfoBean) throws TopjetExceptionHandler;

    /*身份认证审核*/
    ResultBaseMsg  updateUserIDInfo(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler;
    //会员查询列表
    public List<MemberListResponseBean> getMemberList(MemberListRequestBean memberListRequestBean);
    //会员查询页数
    public int getMemberCount(MemberListRequestBean memberListRequestBean);
    //根据手机号码查询
    public List<UserInfoModel> getMobile(String mobile);
    /*会员管理*/
    public void   updateUserInfo(UserInfoBean userInfoBean) throws TopjetExceptionHandler;
    //会员冻结日志
    public List<UserAuditHistoryModel> getUserOperationLog(UserAuditHistoryModel userAuditHistoryModel);
    //根据userid 查询用户
    public UserInfoModel findUserById(Integer id);

    public UserInfoModel findById(Integer id);
    //根据user id查询
    public UserOtherInfoModel findUserOtherById(Integer userId);
    //冻结或者解冻
    public  ResultBaseMsg frozenOrDefrozen(Integer id,String reditRemark,Integer flag,Integer userId) throws TopjetExceptionHandler;


    //头像认证审核查询列表
    public List<MemberAuditListResponseBean> getHeadList(MemberAuditListRequestBean memberAuditListRequestBean);
    //头像认证审核查询列表页数
    public int getHeadCount(MemberAuditListRequestBean memberAuditListRequestBean);
    //详情
    public UserInfoBean getUserInfo(Integer id);
    //修改头像审核
    public ResultBaseMsg   updateHeadDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler;

    UserInfoModel selectByPrimaryKey(Integer id);

    /*用户登陆时获得用户分配任务项*/
    public List<TaskBucketInfoModel> getTaskSysUser(Integer sysUserId);

    UserInfoModel selectUserByMobile(String mobiles);

    public void pushMessage(Integer userId, String title, String text, String pushType, String action, String msgTitle, List<AppButton> buttonList,Integer msgType, Integer releatId);
    /**
     *弹屏用户信息修改
     */
    public void updateUser(UserInfoBean userInfoBean) throws TopjetExceptionHandler;
    /**
     * 根据userid version查询
     */
    public UserInfoModel findUserByVersion(Integer userId, Integer version);
    /**
     * 根据userid 查询冻结状态
     */
    public Integer findDeletedByUserId(Integer id);
    /**
     * 根据手机号查询  返回userInfo
     */
    public List<UserInfoModel> getListByMobile(String mobile);

    List<String> getUserAppType(Integer userId);

    String getUserAppTokenType(Integer userId);

    /**
     * 修改用户手机号码
     * @param userInfoBean
     * @return
     */
    public  void updateMobile(UserInfoBean userInfoBean);


    //企业认证审核查询列表
    public List<CompanyAuditBean> companyAuditList(CompanyAuditBean companyAuditBean);
    //企业认证审核查询列表页数
    public int getCompanyAuditCount(CompanyAuditBean companyAuditBean);



    //获取企业用户详情
    public UserInfoBean getCompanyUserInfo(CompanyAuditBean companyAuditBean);
    //企业用户详情
    public ResultBaseMsg   updateCompanyDetail(UserInfoBean userInfoBean, HttpServletRequest request) throws TopjetExceptionHandler;
    //企业用户维护
    public ResultBaseMsg   updateDetail(UserInfoBean userInfoBean, HttpServletRequest request)throws TopjetExceptionHandler ;
}

package com.topjet.manage.service;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.domain.model.UserOtherInfoModel;

import java.util.List;

/**
 * Created by zhaocj on 2017/8/8.
 */
public interface UserService {
    public UserInfoModel findById(Integer userId);

    //查看实名审核日志
    public List<UserAuditHistoryModel> getOperation(UserAuditHistoryModel auditHistoryModel);

    public Integer idNoIsEmptyApproved(String idNo);


    /*管理平台用户详情里的所有Count*/
    public UserInfoBean getAllCountById(Integer id);
    public UserOtherInfoModel findOtherById(Integer userId);
    //查看会员日志
    public List<UserAuditHistoryModel> getUserOperation(UserAuditHistoryModel auditHistoryModel);

    /*用户登陆时获得用户分配任务项*/
    public List<TaskBucketInfoModel> getTaskSysUser(Integer sysUserId);

    public  UserInfoBean getTmsUserInfo(Integer id);



}

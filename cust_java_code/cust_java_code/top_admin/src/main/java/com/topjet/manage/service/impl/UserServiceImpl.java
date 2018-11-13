package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.model.TaskBucketInfoModel;
import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.domain.model.UserOtherInfoModel;
import com.topjet.manage.mapper.readMapper.TaskBucketInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserAuditHistoryModelEMapper;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.manage.mapper.readMapper.UserOtherInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.UserAuditHistoryModelMapper;
import com.topjet.manage.service.UserService;
import com.topjet.user.constant.UserStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhaocj
 * @create 2017-08-08 15:21
 **/
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserInfoModelEMapper userInfoModelMapper;

    @Autowired
    private UserOtherInfoModelEMapper otherInfoModelMapper;

    @Autowired
    private UserAuditHistoryModelMapper userAuditHistoryModelMapper;
    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;

    @Autowired
    private TaskBucketInfoModelEMapper taskBucketInfoModelEMapper;

    @Override
    public UserInfoModel findById(Integer userId) {
        return userInfoModelMapper.selectByPrimaryKey(userId);
    }

    @Override
    public List<UserAuditHistoryModel> getOperation(UserAuditHistoryModel auditHistoryModel) {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sourceId",auditHistoryModel.getSourceId());
        paramMap.put("sourceType",1);
        List<UserAuditHistoryModel> dataList = userAuditHistoryModelEMapper.selectListByParam(paramMap);
        return dataList;
    }

    /**
     * 验证该用户身份证号是否已注册存在并且审核通过
     *
     * @param idNo
     * @return 存在返回该用户 不存在返回null
     */
    @Override
    public Integer idNoIsEmptyApproved(String idNo) {

        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("idNo",idNo);
        paramMap.put("deleted",0);
        paramMap.put("code",UserStatus.USERSTATUS_APPROVED.getCode() + "," + UserStatus.USERSTATUS_DATA_WAIT_REVIEWED.getCode());
        List<UserInfoModel> memberList = userInfoModelMapper.selectUserInfoByIdNo(paramMap);
        if (!memberList.isEmpty()) {
            return memberList.get(0).getId();
        }
        return null;
    }
    /*管理平台用户详情里的所有Count*/
    @Override
    public UserInfoBean getAllCountById(Integer id) {
        return userInfoModelMapper.getAllCountById(id);
    }

    @Override
    public UserOtherInfoModel findOtherById(Integer userId) {
        return otherInfoModelMapper.selectByPrimaryKey(userId);
    }

//会员日志
    @Override
    public List<UserAuditHistoryModel> getUserOperation(UserAuditHistoryModel auditHistoryModel) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sourceId",auditHistoryModel.getSourceId());
        paramMap.put("sourceType",6);
        List<UserAuditHistoryModel> dataList = userAuditHistoryModelEMapper.selectListByParam(paramMap);
        return dataList;
    }

    @Override
    public List<TaskBucketInfoModel> getTaskSysUser(Integer sysUserId) {
        Map<String,Object> paramMap = new HashMap<String,Object>();
        paramMap.put("sysUserId",sysUserId);
        paramMap.put("deleted",0);
        paramMap.put("invalid",1);
        return taskBucketInfoModelEMapper.selectListByParam(paramMap);
    }


    @Override
    public UserInfoBean getTmsUserInfo(Integer id) {
        return userInfoModelMapper.getTmsUserInfo(id);
    }
}

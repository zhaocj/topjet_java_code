package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.UserAuditHistoryModel;
import com.topjet.manage.mapper.readMapper.UserAuditHistoryModelEMapper;
import com.topjet.manage.mapper.writeMapper.UserAuditHistoryModelMapper;
import com.topjet.manage.service.UserAuditHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author zhaocj
 * @create 2017-08-10 11:18
 **/
@Transactional
@Service
public class UserAuditHistoryServiceImpl implements UserAuditHistoryService{
    @Autowired
    private UserAuditHistoryModelMapper userAuditHistoryModelMapper;
    @Autowired
    private UserAuditHistoryModelEMapper userAuditHistoryModelEMapper;

    @Override
    public int insert(UserAuditHistoryModel userAuditHistoryModel) {
        return userAuditHistoryModelMapper.insert(userAuditHistoryModel);
    }

    @Override
    public List<UserAuditHistoryModel> listUserAuditHistory(UserAuditHistoryModel userAuditHistoryModel) {

        return userAuditHistoryModelEMapper.selectListByEntity(userAuditHistoryModel);
    }
}

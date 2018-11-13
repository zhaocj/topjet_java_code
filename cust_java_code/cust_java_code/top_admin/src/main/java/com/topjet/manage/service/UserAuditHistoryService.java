package com.topjet.manage.service;

import com.topjet.manage.domain.model.UserAuditHistoryModel;

import java.util.List;

/**
 * Created by bjb074 on 2017/8/10.
 */
public interface UserAuditHistoryService {

    int insert(UserAuditHistoryModel userAuditHistoryModel);

    public List<UserAuditHistoryModel> listUserAuditHistory(UserAuditHistoryModel userAuditHistoryModel);
}

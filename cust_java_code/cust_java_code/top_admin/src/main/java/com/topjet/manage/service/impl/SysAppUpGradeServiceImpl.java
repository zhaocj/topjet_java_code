package com.topjet.manage.service.impl;

import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.cloud.manage.mq.message.PushBroadcastMQBean;
import com.topjet.common.SessionUtils;
import com.topjet.common.exception.pms.TopjetExceptionHandler;
import com.topjet.config.MessageConfig;
import com.topjet.manage.service.MessageSendService;
import com.topjet.manage.service.SysAppUpGradeService;
import com.topjet.system.domain.model.AppUpgradeHistoryModel;
import com.topjet.system.domain.model.AppUpgradeInfoModel;
import com.topjet.system.mapper.AppUpgradeHistoryModelMapper;
import com.topjet.system.mapper.AppUpgradeInfoModelMapper;
import com.topjet.tool.common.util.DateUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by liyj on 2017/8/8.
 */
@Service
@Transactional
public class SysAppUpGradeServiceImpl implements SysAppUpGradeService {
    private final static Logger log = Logger.getLogger(SysAppUpGradeServiceImpl.class);
    @Autowired
    private AppUpgradeInfoModelMapper appUpgradeInfoModelMapper;
    @Autowired
    private AppUpgradeHistoryModelMapper appUpgradeHistoryModelMapper;
    @Autowired
    private MessageSendService messageSendService;
    @Autowired
    private MessageConfig messageConfig;
    @Override
    public List<AppUpgradeInfoModel> getAppUpGradeList(AppUpgradeInfoModel appUpgradeInfoModel) {
        return appUpgradeInfoModelMapper.getAppUpGradeList(appUpgradeInfoModel);
    }

    @Override
    public Integer getCount(AppUpgradeInfoModel appUpgradeInfoModel) {
        return appUpgradeInfoModelMapper.getCount(appUpgradeInfoModel);
    }

    @Override
    public AppUpgradeInfoModel findAppById(Integer id) {
        if(id != null){
            return appUpgradeInfoModelMapper.findAppById(id);
        }
        return  null;

    }

    @Override
    public void saveOrUpdate(AppUpgradeInfoModel appModel) throws TopjetExceptionHandler {
        if(appModel.getId() != null){
            //执行修改操作
            appModel.setUpdateTime(DateUtil.now());
            appUpgradeInfoModelMapper.update(appModel);
        }
        else{
            //执行添加操作
            AppUpgradeInfoModel saveModel=new AppUpgradeInfoModel();
            saveModel.setSystemVersion(appModel.getSystemVersion());
            saveModel.setInnoVersion(appModel.getInnoVersion());
            saveModel.setDescription(appModel.getDescription());
            saveModel.setDownloadAddress(appModel.getDownloadAddress());
            saveModel.setIsForced(appModel.getIsForced());
            saveModel.setUpdateBy(SessionUtils.getSysUserSession().getId());
            saveModel.setUpdateTime(DateUtil.now());
            saveModel.setCreateBy(SessionUtils.getSysUserSession().getId());
            saveModel.setCreateTime(DateUtil.now());
            saveModel.setType(appModel.getType());
            saveModel.setDeleted(0);
            saveModel.setIsNotified(0);
            appUpgradeInfoModelMapper.insert(saveModel);
        }
        AppUpgradeHistoryModel historyModel=new AppUpgradeHistoryModel();
        historyModel.setSystemVersion(appModel.getSystemVersion());
        historyModel.setInnoVersion(appModel.getInnoVersion());
        historyModel.setDescription(appModel.getDescription());
        historyModel.setDownloadAddress(appModel.getDownloadAddress());
        historyModel.setIsForced(appModel.getIsForced());
        historyModel.setCreateTime(DateUtil.now());
        historyModel.setType(appModel.getType());
        historyModel.setDeleted(0);
        historyModel.setIsNotified(0);
        appUpgradeHistoryModelMapper.insert(historyModel);

    }

    @Override
    public List<AppUpgradeHistoryModel> getAppHistoryList(AppUpgradeHistoryModel appUpgradeHistoryModel) {
        return appUpgradeInfoModelMapper.getAppHistoryList(appUpgradeHistoryModel);
    }

    @Override
    public Integer getHistoryCount(AppUpgradeHistoryModel appUpgradeHistoryModel){
        return appUpgradeInfoModelMapper.getHistoryCount(appUpgradeHistoryModel);
    }

    @Override
    public List<AppUpgradeInfoModel> findAppByType(String type) {
        return appUpgradeInfoModelMapper.findAppByType(type);
    }

    @Override
    public Integer send(AppUpgradeInfoModel appUpgradeInfoModel) {
        AppUpgradeInfoModel updateAppUpgradeInfoModel = appUpgradeInfoModelMapper.findAppById(appUpgradeInfoModel.getId());
        PushBroadcastMQBean pushBroadcastMQBean = new PushBroadcastMQBean();
        pushBroadcastMQBean.setTitle("560交运配货有新版本更新");
        pushBroadcastMQBean.setText("560交运配货有新版本更新");
        pushBroadcastMQBean.setAction("");
        AppButton appButton = new AppButton();
        appButton.setText(messageConfig.getMessagePushButton());
        List<AppButton> buttonList = new ArrayList<>();
        buttonList.add(appButton);
        pushBroadcastMQBean.setButton(buttonList);
        pushBroadcastMQBean.setSystemType(updateAppUpgradeInfoModel.getType());
        //发起推送
        messageSendService.pushBroadMessage(pushBroadcastMQBean);

        updateAppUpgradeInfoModel.setUpdateTime(com.topjet.common.util.DateUtil.now());
        updateAppUpgradeInfoModel.setIsNotified(updateAppUpgradeInfoModel.getIsNotified()+1);
        appUpgradeInfoModelMapper.update(updateAppUpgradeInfoModel);

        AppUpgradeHistoryModel appUpgradeHistoryModel = new AppUpgradeHistoryModel();
        appUpgradeHistoryModel.setType(updateAppUpgradeInfoModel.getType());
        List<AppUpgradeHistoryModel> dataList = appUpgradeInfoModelMapper.getAppHistoryList(appUpgradeHistoryModel);
        if(dataList != null && dataList.size() > 0){
            AppUpgradeHistoryModel appHistoryModel = new AppUpgradeHistoryModel();
            appHistoryModel.setIsNotified(1);
            appUpgradeHistoryModelMapper.update(appHistoryModel);
        }
        return 1;
    }
}

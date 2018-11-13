package com.topjet.manage.service.impl;

import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.cloud.manage.mq.message.PushBroadcastMQBean;
import com.topjet.common.exception.pms.ExceptionEnum;
import com.topjet.config.MessageConfig;
import com.topjet.manage.domain.model.AnnouncementInfoModel;
import com.topjet.manage.mapper.readMapper.AnnouncementInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.AnnouncementInfoModelMapper;
import com.topjet.manage.service.AnnouncementInfoService;
import com.topjet.manage.service.MessageSendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by yuzh on 2017/11/13.
 */
@Service
public class AnnouncementInfoServiceImpl implements AnnouncementInfoService {

    @Autowired
    private AnnouncementInfoModelEMapper announcementInfoModelEMapper;
    @Autowired
    private AnnouncementInfoModelMapper announcementInfoModelMapper;
    @Autowired
    private MessageConfig messageConfig;
    @Autowired
    private MessageSendService messageSendService;

    @Override
    public List<AnnouncementInfoModel> getAnnounceList(AnnouncementInfoModel announcementInfoModel) {
        return announcementInfoModelEMapper.getAnnounceList(announcementInfoModel);
    }

    @Override
    public Integer getAnnounceCount(AnnouncementInfoModel announcementInfoModel) {
        return announcementInfoModelEMapper.getAnnounceCount(announcementInfoModel);
    }

    @Override
    public AnnouncementInfoModel getAnnounceById(Integer id) {
        return announcementInfoModelEMapper.getAnnounceById(id);
    }

    @Override
    public Integer updateAnnounceInfo(AnnouncementInfoModel announcementInfoModel) {
        return announcementInfoModelMapper.updateAnnounceInfo(announcementInfoModel);
    }

    @Override
    public Integer addAnnounceInfo(AnnouncementInfoModel announcementInfoModel) {
        return announcementInfoModelMapper.addAnnounceInfo(announcementInfoModel);
    }

    @Override
    public Integer deleteAnnounceInfo(Integer id) {
        return announcementInfoModelMapper.deleteAnnounceInfo(id);
    }

    @Override
    public void sendAnno(AnnouncementInfoModel model) {
        PushBroadcastMQBean pushBroadcastMQBean = new PushBroadcastMQBean();
        String content = messageConfig.getAnnounceMentMessage();
        String systemType = Integer.toString(model.getType());
        pushBroadcastMQBean.setTitle(content);
        pushBroadcastMQBean.setText(content);
        pushBroadcastMQBean.setAction("");
        AppButton appButton = new AppButton();
        appButton.setText(messageConfig.getMessagePushButton());
        List<AppButton> buttonList = new ArrayList<>();
        buttonList.add(appButton);
        pushBroadcastMQBean.setButton(buttonList);
        pushBroadcastMQBean.setSystemType(systemType);
        messageSendService.pushBroadMessage(pushBroadcastMQBean);
    }
}

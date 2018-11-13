package com.topjet.manage.service;

import com.topjet.cloud.manage.mq.message.PushBroadcastMQBean;
import com.topjet.common.ResultBaseMsg;
import com.topjet.manage.domain.model.MessageSendModel;

import java.util.List;

/**
 * @Author: zhaocj
 * @Description: 消息推送功能service
 * @Date: 2017-11-15 9:43
 */

public interface MessageSendService {

    List<MessageSendModel> listMessageSend(MessageSendModel messageSendModel);

    int countMessageSend(Integer deleted);

    int insertMessageSend(MessageSendModel model);

    MessageSendModel getMessageSendDetail(Integer id);

    int deleteMessageSend(Integer id);

    MessageSendModel selectMessageSend(Integer id, Integer version);

    int update(MessageSendModel messageSendModel);

    ResultBaseMsg sendMessage(MessageSendModel messageSendModel);

    void pushBroadMessage(PushBroadcastMQBean broadcastMQBean);

}

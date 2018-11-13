package com.topjet.manage.service.impl;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.constant.PushConstant;
import com.topjet.cloud.manage.mq.constant.QueuesName;
import com.topjet.cloud.manage.mq.message.AppAction;
import com.topjet.cloud.manage.mq.message.AppButton;
import com.topjet.cloud.manage.mq.message.PushBroadcastMQBean;
import com.topjet.cloud.manage.mq.message.PushMessageMQBean;
import com.topjet.cloud.manage.service.basic.bean.SendSmsRTS;
import com.topjet.common.ResultBaseMsg;
import com.topjet.config.MessageConfig;
import com.topjet.manage.constants.CommonConstant;
import com.topjet.manage.domain.model.MessageSendModel;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.readMapper.MessageSendModelEMapper;
import com.topjet.manage.mapper.writeMapper.MessageSendModelMapper;
import com.topjet.manage.service.MessageSendService;
import com.topjet.manage.service.UserInfoService;
import com.topjet.tool.common.constant.SystemConstant;
import com.topjet.tool.common.util.JsonUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-15 9:44
 */

@Transactional
@Service
public class MessageSendServiceImpl implements MessageSendService {
    private static Logger log = LoggerFactory.getLogger(MessageSendServiceImpl.class);

    @Autowired
    private BasicFeignService basicFeignService;

    @Autowired
    private UserInfoService userInfoService;

    @Autowired
    private MessageSendModelEMapper messageSendModelEMapper;

    @Autowired
    private MessageSendModelMapper messageSendModelMapper;

    @Autowired
    private MessageConfig messageConfig;

    @Autowired
    private AmqpTemplate rabbitTemplate;


    @Override
    public List<MessageSendModel> listMessageSend(MessageSendModel messageSendModel) {
        return messageSendModelEMapper.listMessageSend(messageSendModel);
    }

    @Override
    public int countMessageSend(Integer deleted) {
        return messageSendModelEMapper.countMessageSend(CommonConstant.DB_RECORD_DELETED_STATUS_FALSE);
    }

    @Override
    public int insertMessageSend(MessageSendModel model) {
        model.setStatus(2);
        return messageSendModelMapper.insert(model);
    }

    @Override
    public MessageSendModel getMessageSendDetail(Integer id) {
        return messageSendModelEMapper.getMessageSendDetail(id);
    }

    @Override
    public int deleteMessageSend(Integer id) {
        return messageSendModelMapper.deleteMessageSend(id);
    }

    @Override
    public MessageSendModel selectMessageSend(Integer id, Integer version) {
        return messageSendModelEMapper.selectMessageSend(id,version);
    }

    @Override
    public int update(MessageSendModel messageSendModel) {
        return messageSendModelMapper.update(messageSendModel);
    }

    /**
     * sendType: 1：app推送   2：短信推送'
     * memberType 1：全部用户；2：ios\android货主；3：ios货主；4：andriod货主；5：ios\android司机；6：ios司机；7：android司机; 8:单个用户'
     * @param model
     * @return
     */
    @Override
    public ResultBaseMsg sendMessage(MessageSendModel model) {
        ResultBaseMsg resultBaseMsg = new ResultBaseMsg();
        MessageSendModel messageSendModel = messageSendModelEMapper.selectMessageSendModelByPrimarykey(model.getId());
        //单人推送
        if(messageSendModel.getMemberType()==8){
            List<UserInfoModel> users = userInfoService.getListByMobile(messageSendModel.getMobile());
            if(messageSendModel.getSendType() == 2){
                SendSmsRTS rts = new SendSmsRTS();rts.setMobileNum(messageSendModel.getMobile());rts.setContent(messageSendModel.getContent());
                try{
                    basicFeignService.sendSms(rts);
                }catch (Exception e){
                    log.error("短信发送异常："+" "+e.getCause() + e.getMessage());
                }
                basicFeignService.sendSms(rts);
            }else{
                List<AppButton> buttons = new ArrayList<>();
                AppButton button = new AppButton();
                button.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
                button.setText(messageConfig.getMessagePushButton());buttons.add(button);
                try{
                    userInfoService.pushMessage(users.get(0).getId(),"公告消息",messageSendModel.getContent(), SystemConstant.BEINGPUSH_DRIVER,"","",buttons,PushConstant.MESSAGETYPE_SYSTEM,0);
                    userInfoService.pushMessage(users.get(0).getId(),"公告消息",messageSendModel.getContent(), SystemConstant.BEINGPUSH_OWNER,"","",buttons,PushConstant.MESSAGETYPE_SYSTEM,0);
                }catch (Exception e){
                    log.error("自定义消息推送异常："+" "+e.getCause() + e.getMessage());
                }
            }
        }else{
            pushMessage(messageSendModel.getMemberType(),messageSendModel.getContent());
        }


        return resultBaseMsg;
    }

    /**
     * sendType: 1：app推送   2：短信推送'
     * memberType 1：全部用户；2：ios\android货主；3：ios货主；4：andriod货主；5：ios\android司机；6：ios司机；7：android司机; 8:单个用户'
     * @param memberType
     * @return
     */
    private void pushMessage(Integer memberType, String content){
        PushBroadcastMQBean broadcastMQBean = new PushBroadcastMQBean();
        List<AppButton> appButtons = new ArrayList<>();
        AppButton appButton = new AppButton();
        broadcastMQBean.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
        appButton.setAction(AppAction.ACTION_OWNER_EMPTY.getAction());
        appButton.setText(messageConfig.getMessagePushButton());
        appButtons.add(appButton);
        broadcastMQBean.setButton(appButtons);
        broadcastMQBean.setText(content);
        broadcastMQBean.setTitle(content);
        switch (memberType){
            case 1:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 2:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 3:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 4:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_OWNER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 5:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 6:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_IOS_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                break;
            case 7:
                broadcastMQBean.setSystemType(SystemConstant.SYSTEMTYPE_ANDROID_DRIVER);
                this.pushBroadMessage(broadcastMQBean);
                break;
        }
    }


    @Override
    public void pushBroadMessage(PushBroadcastMQBean broadcastMQBean) {
        log.info("MQ广播消息推送通道：" + QueuesName.QUEUES_PUSH_BROADCAST_NAME + " " + "广播消息内容：" + JsonUtil.toJSON(broadcastMQBean));
        try{
            rabbitTemplate.convertAndSend(QueuesName.QUEUES_PUSH_BROADCAST_NAME,broadcastMQBean);
        }catch (Exception e){
            log.error("MQ消息推送失败");
            e.printStackTrace();
        }
    }



}

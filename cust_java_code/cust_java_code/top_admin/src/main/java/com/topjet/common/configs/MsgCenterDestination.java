package com.topjet.common.configs;

/**
 * Created by liyj on 2017/11/15.
 */
public enum MsgCenterDestination {
    /**
     * 手机三元素审核
     */
    CheckMobileAndName("CheckIdcardAndMobile","AuthMessage");

    private String actionName;
    private String messageType;

    MsgCenterDestination(String actionName, String messageType) {
        this.actionName = actionName;
        this.messageType = messageType;
    }

    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getMessageType() {
        return messageType;
    }

    public void setMessageType(String messageType) {
        this.messageType = messageType;
    }
}

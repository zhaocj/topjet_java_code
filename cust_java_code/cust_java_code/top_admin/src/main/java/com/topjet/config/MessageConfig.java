package com.topjet.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-11-15 15:05
 */

@Configuration
public class MessageConfig {

    @Value("${message.push.title.idverifypass}")
    private String idVerifyPassTitle;

    @Value("${message.push.title.idverifyfail}")
    private String idVerifyFailTitle;

    @Value("${message.push.title.idauthpass}")
    private String idAuthPassTitle;

    @Value("${message.push.title.idauthfail}")
    private String idAuthFailTitle;

    @Value("${message.push.title.headauthpass}")
    private String headAuthPassTitle;

    @Value("${message.push.title.headauthfail}")
    private String headAuthFailTitle;

    @Value("${message.push.title.carauthpass}")
    private String carAuthPassTitle;

    @Value("${message.push.title.carauthfail}")
    private String carAuthFailTitle;

    @Value("${message.push.title.goodspushmessage}")
    private String goodsPushMessage;

    @Value("${message.push.title.announcementmessage}")
    private String announceMentMessage;

    @Value("${message.push.button.text}")
    private String messagePushButton;


    @Value("${message.push.title.companypass}")
    private String companyAuditPassTitle;

    @Value("${message.push.title.companyfail}")
    private String companyAuditFailTitle;

    public String getCompanyAuditPassTitle() {
        return companyAuditPassTitle;
    }

    public void setCompanyAuditPassTitle(String companyAuditPassTitle) {
        this.companyAuditPassTitle = companyAuditPassTitle;
    }

    public String getCompanyAuditFailTitle() {
        return companyAuditFailTitle;
    }

    public void setCompanyAuditFailTitle(String companyAuditFailTitle) {
        this.companyAuditFailTitle = companyAuditFailTitle;
    }

    public String getIdVerifyPassTitle() {
        return idVerifyPassTitle;
    }

    public void setIdVerifyPassTitle(String idVerifyPassTitle) {
        this.idVerifyPassTitle = idVerifyPassTitle;
    }

    public String getIdVerifyFailTitle() {
        return idVerifyFailTitle;
    }

    public void setIdVerifyFailTitle(String idVerifyFailTitle) {
        this.idVerifyFailTitle = idVerifyFailTitle;
    }

    public String getIdAuthPassTitle() {
        return idAuthPassTitle;
    }

    public void setIdAuthPassTitle(String idAuthPassTitle) {
        this.idAuthPassTitle = idAuthPassTitle;
    }

    public String getIdAuthFailTitle() {
        return idAuthFailTitle;
    }

    public void setIdAuthFailTitle(String idAuthFailTitle) {
        this.idAuthFailTitle = idAuthFailTitle;
    }

    public String getHeadAuthPassTitle() {
        return headAuthPassTitle;
    }

    public void setHeadAuthPassTitle(String headAuthPassTitle) {
        this.headAuthPassTitle = headAuthPassTitle;
    }

    public String getHeadAuthFailTitle() {
        return headAuthFailTitle;
    }

    public void setHeadAuthFailTitle(String headAuthFailTitle) {
        this.headAuthFailTitle = headAuthFailTitle;
    }

    public String getCarAuthPassTitle() {
        return carAuthPassTitle;
    }

    public void setCarAuthPassTitle(String carAuthPassTitle) {
        this.carAuthPassTitle = carAuthPassTitle;
    }

    public String getCarAuthFailTitle() {
        return carAuthFailTitle;
    }

    public void setCarAuthFailTitle(String carAuthFailTitle) {
        this.carAuthFailTitle = carAuthFailTitle;
    }

    public String getMessagePushButton() {
        return messagePushButton;
    }

    public void setMessagePushButton(String messagePushButton) {
        this.messagePushButton = messagePushButton;
    }

    public String getGoodsPushMessage() {
        return goodsPushMessage;
    }

    public void setGoodsPushMessage(String goodsPushMessage) {
        this.goodsPushMessage = goodsPushMessage;
    }

    public String getAnnounceMentMessage() {
        return announceMentMessage;
    }

    public void setAnnounceMentMessage(String announceMentMessage) {
        this.announceMentMessage = announceMentMessage;
    }
}

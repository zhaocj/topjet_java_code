package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.model.BaseModel;

/**
 * Created by zhaocj on 2016/10/9.
 */
public class MemberAuditListRequestBean extends BaseModel {

    private static final long serialVersionUID = 1816428392261999936L;

    private String auditName;

    private String mobile;

    private String status;

    private String headStatus;

    public String getHeadStatus() {
        return headStatus;
    }

    public void setHeadStatus(String headStatus) {
        this.headStatus = headStatus;
    }

    private String flag;//标记是否为分配任务客服   1：注册 2：身份 3.头像

    private String sysUserId;//任务分配者ID

    private String registerOrAuditFlag;//注册还是身份审核标记 1：注册 2：身份

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getSysUserId() {
        return sysUserId;
    }

    public void setSysUserId(String sysUserId) {
        this.sysUserId = sysUserId;
    }

    public String getRegisterOrAuditFlag() {
        return registerOrAuditFlag;
    }

    public void setRegisterOrAuditFlag(String registerOrAuditFlag) {
        this.registerOrAuditFlag = registerOrAuditFlag;
    }
}

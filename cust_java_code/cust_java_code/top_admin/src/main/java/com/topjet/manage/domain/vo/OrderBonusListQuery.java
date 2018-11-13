package com.topjet.manage.domain.vo;


import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;
import java.util.List;

/**
 * Created by zhaocj on 2017/10/16.
 */
public class OrderBonusListQuery extends BaseModel {
    private Integer type;//1运费补贴2中介费补贴
    private String senderMobile;
    private String receiverMobile;
    private Date startDate;
    private Date endDate;
    private String serialNo;
    private String auditName;
    private String auditProcess;//审核进度 1一审，2二审，3三审
    private String auditStatus;//审核状态  对应上面的审核进度
    private String destination1;
    private String destination2;
    private String destination3;
    private String depart1;
    private String depart2;
    private String depart3;
    private String reditRemark;

    private List<Integer> statusList;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public Integer getType() {
        return type;
    }
    public void setType(Integer type) {
        this.type = type;
    }

    public String getSenderMobile() {
        return senderMobile;
    }

    public void setSenderMobile(String senderMobile) {
        this.senderMobile = senderMobile;
    }

    public String getReceiverMobile() {
        return receiverMobile;
    }

    public void setReceiverMobile(String receiverMobile) {
        this.receiverMobile = receiverMobile;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getAuditProcess() {
        return auditProcess;
    }

    public void setAuditProcess(String auditProcess) {
        this.auditProcess = auditProcess;
    }

    public String getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(String auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getDestination1() {
        return destination1;
    }

    public void setDestination1(String destination1) {
        this.destination1 = destination1;
    }

    public String getDestination2() {
        return destination2;
    }

    public void setDestination2(String destination2) {
        this.destination2 = destination2;
    }

    public String getDestination3() {
        return destination3;
    }

    public void setDestination3(String destination3) {
        this.destination3 = destination3;
    }

    public String getDepart1() {
        return depart1;
    }

    public void setDepart1(String depart1) {
        this.depart1 = depart1;
    }

    public String getDepart2() {
        return depart2;
    }

    public void setDepart2(String depart2) {
        this.depart2 = depart2;
    }

    public String getDepart3() {
        return depart3;
    }

    public void setDepart3(String depart3) {
        this.depart3 = depart3;
    }

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public List<Integer> getStatusList() {
        return statusList;
    }

    public void setStatusList(List<Integer> statusList) {
        this.statusList = statusList;
    }
}

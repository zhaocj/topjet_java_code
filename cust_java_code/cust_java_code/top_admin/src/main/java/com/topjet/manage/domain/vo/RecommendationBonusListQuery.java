package com.topjet.manage.domain.vo;

import com.topjet.manage.domain.model.BaseModel;

import java.util.Date;

/**
 * Created by tsj006 on 2016/8/30.
 */
public class RecommendationBonusListQuery extends BaseModel{

    private String  recommendMobile;
    private String  recommendedMobile;
    private Date startDate;
    private Date endDate;
    private String recommendedIEMI;
    private String auditName;
    private Integer secondAuditStatus;
    private Integer firstAuditStatus;
    private Integer auditStatus;
    private String isValid;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public Integer getAuditStatus() {
        return auditStatus;
    }

    public void setAuditStatus(Integer auditStatus) {
        this.auditStatus = auditStatus;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }



    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
    }

    public String getRecommendedMobile() {
        return recommendedMobile;
    }

    public void setRecommendedMobile(String recommendedMobile) {
        this.recommendedMobile = recommendedMobile;
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

    public String getAuditName() {
        return auditName;
    }

    public void setAuditName(String auditName) {
        this.auditName = auditName;
    }

    public Integer getSecondAuditStatus() {
        return secondAuditStatus;
    }

    public void setSecondAuditStatus(Integer secondAuditStatus) {
        this.secondAuditStatus = secondAuditStatus;
    }

    public Integer getFirstAuditStatus() {
        return firstAuditStatus;
    }

    public void setFirstAuditStatus(Integer firstAuditStatus) {
        this.firstAuditStatus = firstAuditStatus;
    }

    public String getRecommendedIEMI() {
        return recommendedIEMI;
    }

    public void setRecommendedIEMI(String recommendedIEMI) {
        this.recommendedIEMI = recommendedIEMI;
    }
}

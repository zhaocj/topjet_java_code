package com.topjet.manage.domain.vo;

/**
 * Created by tsj006 on 2016/8/30.
 */
public class RecommendationBonusListVO {
    private Integer id;
    private Integer version;
    private Integer recommendedId;
    private Integer recommendId;
    private String createDate;
    private String recommendName;
    private String recommendMobile;
    private String recommendedName;
    private String recommendedMobile;
    private String recommendedIEMI;
    private String firstAuditUserName;
    private String secondAuditUserName;
    private String firstAuditStatus;
    private String secondAuditStatus;
    private String firstAutidRemark;
    private String secondAuditRemark;
    private String userType;
    private String isValid;
    private String reditRemark;

    public String getReditRemark() {
        return reditRemark;
    }

    public void setReditRemark(String reditRemark) {
        this.reditRemark = reditRemark;
    }

    public String getIsValid() {
        return isValid;
    }

    public void setIsValid(String isValid) {
        this.isValid = isValid;
    }

    public String getUserType() {
        return userType;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }

    public String getRecommendName() {
        return recommendName;
    }

    public void setRecommendName(String recommendName) {
        this.recommendName = recommendName;
    }

    public String getRecommendMobile() {
        return recommendMobile;
    }

    public void setRecommendMobile(String recommendMobile) {
        this.recommendMobile = recommendMobile;
    }

    public String getRecommendedName() {
        return recommendedName;
    }

    public void setRecommendedName(String recommendedName) {
        this.recommendedName = recommendedName;
    }

    public String getRecommendedMobile() {
        return recommendedMobile;
    }

    public void setRecommendedMobile(String recommendedMobile) {
        this.recommendedMobile = recommendedMobile;
    }

    public String getRecommendedIEMI() {
        return recommendedIEMI;
    }

    public void setRecommendedIEMI(String recommendedIEMI) {
        this.recommendedIEMI = recommendedIEMI;
    }

    public String getFirstAuditUserName() {
        return firstAuditUserName;
    }

    public void setFirstAuditUserName(String firstAuditUserName) {
        this.firstAuditUserName = firstAuditUserName;
    }

    public String getSecondAuditUserName() {
        return secondAuditUserName;
    }

    public void setSecondAuditUserName(String secondAuditUserName) {
        this.secondAuditUserName = secondAuditUserName;
    }

    public String getFirstAuditStatus() {
        return firstAuditStatus;
    }

    public void setFirstAuditStatus(String firstAuditStatus) {
        this.firstAuditStatus = firstAuditStatus;
    }

    public String getSecondAuditStatus() {
        return secondAuditStatus;
    }

    public void setSecondAuditStatus(String secondAuditStatus) {
        this.secondAuditStatus = secondAuditStatus;
    }

    public String getFirstAutidRemark() {
        return firstAutidRemark;
    }

    public void setFirstAutidRemark(String firstAutidRemark) {
        this.firstAutidRemark = firstAutidRemark;
    }

    public String getSecondAuditRemark() {
        return secondAuditRemark;
    }

    public void setSecondAuditRemark(String secondAuditRemark) {
        this.secondAuditRemark = secondAuditRemark;
    }

    public Integer getRecommendedId() {
        return recommendedId;
    }

    public void setRecommendedId(Integer recommendedId) {
        this.recommendedId = recommendedId;
    }

    public Integer getRecommendId() {
        return recommendId;
    }

    public void setRecommendId(Integer recommendId) {
        this.recommendId = recommendId;
    }
}

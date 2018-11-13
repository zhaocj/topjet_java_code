package com.topjet.manage.domain.model;

public class ActivityPageInfoModel {
    private Integer id;

    private Integer showPage;

    private Integer regularActivityId;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getShowPage() {
        return showPage;
    }

    public void setShowPage(Integer showPage) {
        this.showPage = showPage;
    }

    public Integer getRegularActivityId() {
        return regularActivityId;
    }

    public void setRegularActivityId(Integer regularActivityId) {
        this.regularActivityId = regularActivityId;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
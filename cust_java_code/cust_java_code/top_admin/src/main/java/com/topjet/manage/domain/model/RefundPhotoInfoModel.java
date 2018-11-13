package com.topjet.manage.domain.model;

public class RefundPhotoInfoModel extends BaseModel {
    private Integer id;

    private Integer refundId;

    private Integer refundType;

    private String pictureUrl;

    private Integer deleted;

    private static final long serialVersionUID = 1L;

    public RefundPhotoInfoModel(Integer id, Integer refundId, Integer refundType, String pictureUrl,Integer deleted) {
        this.id = id;
        this.refundId = refundId;
        this.refundType = refundType;
        this.pictureUrl = pictureUrl;
        this.deleted = deleted;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }

    public RefundPhotoInfoModel() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getRefundId() {
        return refundId;
    }

    public void setRefundId(Integer refundId) {
        this.refundId = refundId;
    }

    public Integer getRefundType() {
        return refundType;
    }

    public void setRefundType(Integer refundType) {
        this.refundType = refundType;
    }

    public String getPictureUrl() {
        return pictureUrl;
    }

    public void setPictureUrl(String pictureUrl) {
        this.pictureUrl = pictureUrl == null ? null : pictureUrl.trim();
    }
}
package com.topjet.manage.domain.model;

import java.math.BigDecimal;
import java.util.Date;

public class TruckLengthDictionaryModel extends BaseModel{
    private Integer id;

    private String displayName;

    private BigDecimal length;

    private Integer lengthOrder;

    private Integer valid;

    private Date updateTime;

    private Date createTime;

    private Integer deleted;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Integer getLengthOrder() {
        return lengthOrder;
    }

    public void setLengthOrder(Integer lengthOrder) {
        this.lengthOrder = lengthOrder;
    }

    public Integer getValid() {
        return valid;
    }

    public void setValid(Integer valid) {
        this.valid = valid;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}
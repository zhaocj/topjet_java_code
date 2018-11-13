package com.topjet.manage.domain.model;

import java.util.Date;

/**
 * Created by liyj on 2017/9/20.
 */
public class CollocationInfoModel extends BaseModel{
    private Integer id;

    private String collocationName;

    private Integer collocationType;

    private String typeName;

    private Integer categoryId;

    private Date createTime;

    private Date updateTime;

    private Integer deleted;

    @Override
    public Integer getId() {
        return id;
    }

    @Override
    public void setId(Integer id) {
        this.id = id;
    }

    public String getCollocationName() {
        return collocationName;
    }

    public void setCollocationName(String collocationName) {
        this.collocationName = collocationName;
    }

    public Integer getCollocationType() {
        return collocationType;
    }

    public void setCollocationType(Integer collocationType) {
        this.collocationType = collocationType;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getDeleted() {
        return deleted;
    }

    public void setDeleted(Integer deleted) {
        this.deleted = deleted;
    }
}

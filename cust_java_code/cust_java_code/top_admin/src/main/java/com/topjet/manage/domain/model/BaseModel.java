package com.topjet.manage.domain.model;


import com.topjet.manage.domain.bean.BaseTopPageBean;
import com.topjet.manage.domain.bean.Pager;
import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.Date;

public class BaseModel extends BaseTopPageBean implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private Integer page = 1;

    private Integer rows = 10;

    private String sort;

    private String order;

    private Integer offset;

    private Integer limit;

    private Integer mainId;

    private Integer operation;

    private Date createTime;

    private Integer createBy;

    private Integer id;

    private Integer updateBy;

    private Date updateTime;

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public Integer getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(Integer updateBy) {
        this.updateBy = updateBy;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public Integer getMainId() {
        return mainId;
    }

    public void setMainId(Integer mainId) {
        this.mainId = mainId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Integer createBy) {
        this.createBy = createBy;
    }


    public Integer getLimit() {
        return rows;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }


    /**
     * 分页导航
     */
    private Pager pager = new Pager();


    public Integer getOffset() {
        return (page - 1) * rows;
    }

    public Pager getPager() {
        pager.setPageId(getPage());
        pager.setPageSize(getRows());
        String orderField = "";
        if (StringUtils.isNotBlank(sort)) {
            orderField = sort;
        }
        if (StringUtils.isNotBlank(orderField) && StringUtils.isNotBlank(order)) {
            orderField += " " + order;
        }
        pager.setOrderField(orderField);
        return pager;
    }

    public void setPager(Pager pager) {
        this.pager = pager;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public String getOrder() {
        return order;
    }

    public void setOrder(String order) {
        this.order = order;
    }


    public void setOffset(Integer offset) {
        offset = (page - 1) * rows;
        this.offset = offset;
    }


}

package com.topjet.manage.domain.model;

import java.util.Date;

/**
 * Created by tsj010 on 2018/5/21.
 */
public class TmsLogInfoModel {

    private Integer id;

    private String request;

    private String response;

    private String type;

    private Integer inOrOut;

    private Date createTime;

    private Integer success;

    private Long costTime;

    private static final long serialVersionUID = 1L;

    public TmsLogInfoModel(Integer id, String request, String response, String type, Integer inOrOut, Date createTime, Integer success, Long costTime) {
        this.id = id;
        this.request = request;
        this.response = response;
        this.type = type;
        this.inOrOut = inOrOut;
        this.createTime = createTime;
        this.success = success;
        this.costTime = costTime;
    }

    public TmsLogInfoModel() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRequest() {
        return request;
    }

    public void setRequest(String request) {
        this.request = request == null ? null : request.trim();
    }

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response == null ? null : response.trim();
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    public Integer getInOrOut() {
        return inOrOut;
    }

    public void setInOrOut(Integer inOrOut) {
        this.inOrOut = inOrOut;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getSuccess() {
        return success;
    }

    public void setSuccess(Integer success) {
        this.success = success;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }




}

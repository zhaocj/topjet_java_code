package com.topjet.common.auth.service.dto;

/**
 * Created by tsj010 on 2018/3/28.
 */
public class IDCheckResponseDTO {

    private  int     status;
    private  String  description;
    private  String     compStatus;//比对结果 1.无身份证信息     2.不一致        3.一致
    private  String  compResult;//比对结果文字描述

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCompStatus() {
        return compStatus;
    }

    public void setCompStatus(String compStatus) {
        this.compStatus = compStatus;
    }

    public String getCompResult() {
        return compResult;
    }

    public void setCompResult(String compResult) {
        this.compResult = compResult;
    }
}

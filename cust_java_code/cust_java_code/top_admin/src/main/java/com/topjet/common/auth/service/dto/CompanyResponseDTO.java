package com.topjet.common.auth.service.dto;

/**
 * Created by tsj010 on 2018/5/11.
 */
public class CompanyResponseDTO {

    private  int    resultCode;   //返回执行结果代码
    private String  resultMsg;  //执行结果描述

    private String  resultData;  //数据体，也是一个json对象

    public int getResultCode() {
        return resultCode;
    }

    public void setResultCode(int resultCode) {
        this.resultCode = resultCode;
    }

    public String getResultMsg() {
        return resultMsg;
    }

    public void setResultMsg(String resultMsg) {
        this.resultMsg = resultMsg;
    }

    public String getResultData() {
        return resultData;
    }

    public void setResultData(String resultData) {
        this.resultData = resultData;
    }
}

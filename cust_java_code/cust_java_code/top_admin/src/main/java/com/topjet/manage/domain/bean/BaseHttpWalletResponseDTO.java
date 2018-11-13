package com.topjet.manage.domain.bean;

import com.topjet.manage.domain.vo.WalletResponseDTO;

public class BaseHttpWalletResponseDTO {

    private String resultcode;

    private String sign;
    
    private String data;

    private WalletResponseDTO response;

    public String getResultcode() {
        return resultcode;
    }

    public void setResultcode(String resultcode) {
        this.resultcode = resultcode;
    }

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public WalletResponseDTO getResponse() {
        return response;
    }

    public void setResponse(WalletResponseDTO response) {
        this.response = response;
    }


    
}

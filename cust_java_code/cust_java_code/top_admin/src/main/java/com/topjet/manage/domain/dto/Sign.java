package com.topjet.manage.domain.dto;

/**
 * Created by liyj on 1/22/16.
 */
public class Sign {
    /**
     * 签名算法
     */
    private String signmethod;

    /**
     * 签名字串 md5
     */
    private String signstr;

    public String getSignmethod() {
        return signmethod;
    }

    public void setSignmethod(String signmethod) {
        this.signmethod = signmethod;
    }

    public String getSignstr() {
        return signstr;
    }

    public void setSignstr(String signstr) {
        this.signstr = signstr;
    }
}

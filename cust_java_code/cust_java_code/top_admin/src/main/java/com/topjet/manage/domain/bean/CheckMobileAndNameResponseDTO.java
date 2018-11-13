package com.topjet.manage.domain.bean;



public class CheckMobileAndNameResponseDTO{
	private  String matchcode;
	private  String operatortext;

    public String getMatchcode() {
        return matchcode;
    }

    public void setMatchcode(String matchcode) {
        this.matchcode = matchcode;
    }

    public String getOperatortext() {
        return operatortext;
    }

    public void setOperatortext(String operatortext) {
        this.operatortext = operatortext;
    }
}

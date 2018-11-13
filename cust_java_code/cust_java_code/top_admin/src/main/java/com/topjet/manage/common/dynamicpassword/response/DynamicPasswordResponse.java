package com.topjet.manage.common.dynamicpassword.response;

public class DynamicPasswordResponse {

	private Integer retcode;

	private String retmsg;

	private String data;

	private DynamicPasswordResponseData dynamicPasswordResponseData;

	public DynamicPasswordResponseData getDynamicPasswordResponseData() {
		return dynamicPasswordResponseData;
	}

	public void setDynamicPasswordResponseData(DynamicPasswordResponseData dynamicPasswordResponseData) {
		this.dynamicPasswordResponseData = dynamicPasswordResponseData;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public Integer getRetcode() {
		return retcode;
	}

	public void setRetcode(Integer retcode) {
		this.retcode = retcode;
	}

	public String getRetmsg() {
		return retmsg;
	}

	public void setRetmsg(String retmsg) {
		this.retmsg = retmsg;
	}

}

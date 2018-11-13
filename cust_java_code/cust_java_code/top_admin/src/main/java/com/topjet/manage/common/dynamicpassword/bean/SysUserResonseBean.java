package com.topjet.manage.common.dynamicpassword.bean;

public class SysUserResonseBean {

	/*
	 * 1 绑定 0 为未定
	 */
	private Integer dynamicpasswordBind;

	private String url;
	
	private Integer isBindSuccess;
	
	

	public Integer getIsBindSuccess() {
		return isBindSuccess;
	}

	public void setIsBindSuccess(Integer isBindSuccess) {
		this.isBindSuccess = isBindSuccess;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Integer getDynamicpasswordBind() {
		return dynamicpasswordBind;
	}

	public void setDynamicpasswordBind(Integer dynamicpasswordBind) {
		this.dynamicpasswordBind = dynamicpasswordBind;
	}

}
package com.topjet.user.constant;

public enum UserStatus {
 /*
  * 证件未提交
  * V200:未认证
  */
	USERSTATUS_NOTCOMMIT(0,"证件未提交"),

	/*
	 * 待审核
	 * V200 : 认证中
	 */
	USERSTATUS_WAITAUCIT(1,"待审核"),

	/*
	 * 审核通过
	 * V200 : 认证通过
	 */
	USERSTATUS_APPROVED(2,"审核通过"),

	/*
	 *资料修改待审核
	 */
	USERSTATUS_DATA_WAIT_REVIEWED(3,"资料修改待审核"),

	/*
	 * V200: 认证失败
	 */
	USERSTATUS_FAILURE(4,"认证失败!"),

	/*
	 * 已冻结
	 */
	USERSTATUS_FROZEN(5,"已冻结"),

	/*
	 * V210: 身份认证未认证
	 */
	USERAUTH_STATUS_NOTCOMMIT(0,"未认证!"),
	/*
	 * V210: 身份认证失败
	 */
	USERAUTH_STATUS_WAITAUDIT(1,"认证中!"),
	/*
	 * V210: 身份认证失败
	 */
	USERAUTH_STATUS_FAILURE(2,"认证失败!"),
	/*
	 * V210: 身份认证失败
	 */
	USERAUTH_STATUS_APPROVE(3,"认证通过!"),

	/*
	 * V210: 头像审核成功
	 */
	USERHEAD_STATUS_APPROVE(3,"审核通过!"),
	/*
	 * V210: 头像审核失败
	 */
	USERHEAD_STATUS_FAILURE(2,"审核失败!"),

	/*
	 *  企业审核成功
	 */
	USERCOMPANY_STATUS_APPROVE(3,"审核通过!"),
	/*
	 *  企业审核失败
	 */
	USERCOMPANY_STATUS_FAILURE(2,"审核失败!");

	private  Integer code;
    private  String  value;
    UserStatus(Integer code, String value) {
    	this.code = code;
    	this.value = value;
    }
	public Integer getCode() {
		return code;
	}
	public void setCode(Integer code) {
		this.code = code;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
   
   
   
 
   
}
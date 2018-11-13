package com.topjet.common;


public class ResultBaseMsg {

	private String status=ExceptionEnum.E_0.getStatus();
	private String msg=ExceptionEnum.E_0.getMsg();

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public ResultBaseMsg() {
		super();
	}

	public ResultBaseMsg(String status, String msg) {
		super();
		this.status = status;
		this.msg = msg;
	}

}

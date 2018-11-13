
package com.topjet.common.exception.pms;

public class TopjetExceptionHandler extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String status;
	private String msg;
	
	public TopjetExceptionHandler(){
		super();
	}
	
	public TopjetExceptionHandler(String message){
		super(message);
	}

	public TopjetExceptionHandler(Throwable throwable){
		super(throwable);
	}
	
	public TopjetExceptionHandler(String message, Throwable throwable){
		super(message,throwable);
	}
	
	public TopjetExceptionHandler(String status, String msg){
		this.status=status;
		this.msg=msg;
	}
	
/*	public String getMessage(){
		return  "{'status':"+getStatus()+",'msg':"+getMsg()+"}";
	}*/

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
	
	
}

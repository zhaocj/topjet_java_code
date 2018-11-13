package com.topjet.common.exception;

public class TopjetErrorCodeException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    private ErrorCode errorcode;

    public TopjetErrorCodeException(ErrorCode errorCode) {
	this.setErrorcode(errorCode);
    }

    public ErrorCode getErrorcode() {
	return errorcode;
    }

    private void setErrorcode(ErrorCode errorcode) {
	this.errorcode = errorcode;
    }

    @Override
    public String getMessage() {
	String message = this.errorcode.getCode() + ":" + this.errorcode.getMsg() + "\n\r";
	return message + super.getMessage();
    }

}

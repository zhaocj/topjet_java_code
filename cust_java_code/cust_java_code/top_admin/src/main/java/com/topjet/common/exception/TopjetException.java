package com.topjet.common.exception;

import org.apache.commons.lang3.StringUtils;

/**
 * 捕获无法定义异常代码的异常时需转换为此异常
 *
 * @author pengtao
 */
public class TopjetException extends RuntimeException {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private String msg;

	private Exception e;

	public TopjetException(String msg) {
		this.setMsg(msg);
	}

	public TopjetException(Exception e) {
		super(e);
		this.e = e;
	}

	@Override
	public String getMessage() {
		String message = msg;
		if (e != null) {
			message = "\r\n" + e.getMessage() + "\r\n";
		}
		return message + StringUtils.stripToEmpty(super.getMessage());
	}

	public String getMsg() {
		return msg;
	}

	private void setMsg(String msg) {
		this.msg = msg;
	}

	public Exception getE() {
		return e;
	}

	public void setE(Exception e) {
		this.e = e;
	}

	public static String printCallStatck(Throwable ex) {
		StackTraceElement[] stackElements = ex.getStackTrace();
		StringBuilder sb = new StringBuilder();
		if (stackElements != null) {
			for (int i = 0; i < stackElements.length; i++) {
				sb.append(stackElements[i].getClassName() + "/t" + stackElements[i].getFileName() + "/t"
						+ stackElements[i].getLineNumber() + "/t" + stackElements[i].getMethodName());
				sb.append(System.getProperty("line.separator"));
			}
		}
		return sb.toString();
	}
}

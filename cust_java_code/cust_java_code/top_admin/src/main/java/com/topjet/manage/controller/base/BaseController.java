package com.topjet.manage.controller.base;

import com.topjet.common.ResultBaseMsg;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class BaseController {

	private static HttpServletRequest request;
	public ResultBaseMsg msg=new ResultBaseMsg();
	
	public static HttpSession getSession() {
		return getRequest().getSession();
	}

	public static HttpServletRequest getRequest() {
		return ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

}

package com.topjet.manage.common.dynamicpassword.service.impl;

import com.topjet.common.constants.SystemConfig;
import com.topjet.common.util.SystemConfiguration;
import com.topjet.manage.common.dynamicpassword.constant.DynamicPasswordConstant;
import com.topjet.manage.common.dynamicpassword.generateRQCode.RQCode;
import com.topjet.manage.common.dynamicpassword.request.DynamicPasswordRequest;
import com.topjet.manage.common.dynamicpassword.response.DynamicPasswordResponse;
import com.topjet.manage.common.dynamicpassword.service.DynamicPasswordService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DynamicPasswordServiceImpl implements DynamicPasswordService {

	@Autowired
	private SystemConfig systemConfig;

	private final static Logger log = Logger.getLogger(DynamicPasswordServiceImpl.class);
	
	@Override
	public Integer bind(Integer userId,String path) {
		// TODO Auto-generated method stub
		StringBuffer sb = new StringBuffer();
		sb.append(systemConfig.getDynamicPassWordUrl()).append("bind?biz=").append(DynamicPasswordConstant.BIZ).append("&bid=").append(userId);
		try {
			DynamicPasswordResponse dynamicPasswordResponse = DynamicPasswordRequest.doPost(sb.toString());
			if (dynamicPasswordResponse.getRetcode() == 0) {
				String initData = dynamicPasswordResponse.getDynamicPasswordResponseData().getInitdata();// 要保存到用户表里
				RQCode.generateRQcode(path,initData); // 生成二维码
				return dynamicPasswordResponse.getRetcode();
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	
	}

	@Override
	public Integer isbind(Integer userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(systemConfig.getDynamicPassWordUrl()).append("isbind?biz=").append(DynamicPasswordConstant.BIZ).append("&bid=").append(userId);
		try {
			DynamicPasswordResponse dynamicPasswordResponse = DynamicPasswordRequest.doPost(sb.toString());
				return dynamicPasswordResponse.getRetcode();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public DynamicPasswordResponse unbind(Integer userId) {
		StringBuffer sb = new StringBuffer();
		sb.append(systemConfig.getDynamicPassWordUrl()).append("unbind?biz=").append(DynamicPasswordConstant.BIZ).append("&bid=").append(userId);
		try {
			DynamicPasswordResponse dynamicPasswordResponse = DynamicPasswordRequest.doPost(sb.toString());
			return dynamicPasswordResponse;
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}

	@Override
	public Integer verify(Integer userId, String v) {
		StringBuffer sb = new StringBuffer();
		sb.append(systemConfig.getDynamicPassWordUrl()).append("verify?biz=").append(DynamicPasswordConstant.BIZ).append("&bid=").append(userId).append("&v=").append(v);
		try {
			DynamicPasswordResponse dynamicPasswordResponse = DynamicPasswordRequest.doPost(sb.toString());
			return dynamicPasswordResponse.getRetcode();
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return null;
	}
}

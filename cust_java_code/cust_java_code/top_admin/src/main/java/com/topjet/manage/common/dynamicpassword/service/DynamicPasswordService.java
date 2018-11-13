package com.topjet.manage.common.dynamicpassword.service;

import com.topjet.manage.common.dynamicpassword.response.DynamicPasswordResponse;

public interface DynamicPasswordService {

	public Integer bind(Integer userId, String path);
	
	public Integer isbind(Integer userId);
	
	public DynamicPasswordResponse unbind(Integer userId);
	
	public Integer verify(Integer userId, String v) ;
	
}

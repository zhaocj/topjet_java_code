package com.topjet.common.auth.service;

import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.TopjetException;
import com.topjet.manage.domain.bean.UserInfoVerifiedReturn;

public interface IDCheckService {

    /**
     * 身份证认证方法
     * 
     * @param idCard
     *            身份证号
     * @param name
     *            身份认证名称
     * @param name idAddress
     * @return 身份认证结果
     * @throws TopjetException
     */

    public IDCheckResult validateIdentityCard(String idCard, String name, String idAddress)
	    throws TopjetErrorCodeException, TopjetException;

    /**
     * 保存身份证信息1
     *
     * @param idNo
     * @param name
     * @param idAddress
     * @throws TopjetException
     */
    public void save(String idNo, String name, String idAddress) throws TopjetException;



    public UserInfoVerifiedReturn checkAuthInfo(String idCard, String name, String idAddress);


}

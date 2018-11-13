package com.topjet.manage.service;

import com.topjet.common.exception.TopjetErrorCodeException;
import com.topjet.common.exception.TopjetException;
import com.topjet.manage.domain.bean.CheckMobileAndNameResponseDTO;
import com.topjet.manage.domain.dto.Request;
import com.topjet.manage.domain.dto.Sign;

/**
 * Created by liyj on 2017/11/15.
 */
public interface CheckMobileAndNameService {

    /**
     * 检查手机号和身份证号姓名
     */
    public CheckMobileAndNameResponseDTO checkMobileAndName(String name,String mobile,String pid);




}

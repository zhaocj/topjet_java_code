package com.topjet.manage.service;

/**
 * @Author: zhaocj
 * @Description:
 * @Date: 2017-09-04 17:32
 */

public interface BlackListInfoService {

    boolean isBlackByType(Integer userId, String type);
}

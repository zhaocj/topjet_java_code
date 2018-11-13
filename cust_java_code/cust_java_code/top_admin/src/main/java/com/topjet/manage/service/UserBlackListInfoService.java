package com.topjet.manage.service;

import com.topjet.manage.domain.model.UserBlackListInfoModel;

/**
 * Created by liyj on 2017/10/16.
 */
public interface UserBlackListInfoService {

    /**
     * 根据userid查询黑名单
     * @param userId
     * @return
     */
    public UserBlackListInfoModel getBlackListInfoByUserId(Integer userId);

}

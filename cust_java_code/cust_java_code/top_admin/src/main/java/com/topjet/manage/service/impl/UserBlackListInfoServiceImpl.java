package com.topjet.manage.service.impl;

import com.topjet.manage.domain.model.UserBlackListInfoModel;
import com.topjet.manage.mapper.readMapper.UserBlackListInfoModelEMapper;
import com.topjet.manage.service.UserBlackListInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by yuzh on 2017/10/16.
 */
@Service
public class UserBlackListInfoServiceImpl implements UserBlackListInfoService {

    @Autowired
    private UserBlackListInfoModelEMapper userBlackListInfoModelEMapper;

    @Override
    public UserBlackListInfoModel getBlackListInfoByUserId(Integer userId) {
        UserBlackListInfoModel userBlackListInfoModel = userBlackListInfoModelEMapper.getBlackListInfoByUserId(userId);
        if(userBlackListInfoModel != null){
            return userBlackListInfoModel;
        }
        return null;
    }
}

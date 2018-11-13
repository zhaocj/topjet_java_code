package com.topjet.manage.service.impl;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.bean.WhiteUserBean;
import com.topjet.manage.domain.model.WhiteUserInfoModel;
import com.topjet.manage.mapper.readMapper.WhiteUserInfoModelEMapper;
import com.topjet.manage.mapper.writeMapper.WhiteUserInfoModelMapper;
import com.topjet.manage.service.WhiteUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by tsj010 on 2018/4/25.
 */
@Service
public class WhiteUserServiceImpl implements WhiteUserService {

    private static Logger log = LoggerFactory.getLogger(WhiteUserServiceImpl.class);

    @Autowired
    private WhiteUserInfoModelEMapper whiteUserInfoModelEMapper ;

    @Autowired
    private WhiteUserInfoModelMapper whiteUserInfoModelMapper ;

    @Override
    public UserInfoBean getUserInfo(String mobile) {
        return whiteUserInfoModelEMapper.getUserInfo(mobile);
    }

    @Override
    public void update(int id) {
        whiteUserInfoModelMapper.update(id);
    }

    @Override
    public void insert(WhiteUserInfoModel whiteUserInfoModel) {
        whiteUserInfoModelMapper.insert(whiteUserInfoModel);
    }


    @Override
    public List<WhiteUserBean> getWhiteUserList() {
        return whiteUserInfoModelEMapper.getWhiteUserList();
    }


    @Override
    public int getWhiteUserListCount() {
        return whiteUserInfoModelEMapper.getWhiteUserListCount();
    }


    @Override
    public WhiteUserInfoModel getWhiteUserInfo(WhiteUserInfoModel whiteUserInfoModel) {
        return whiteUserInfoModelEMapper.getWhiteUserInfo(whiteUserInfoModel);
    }
}

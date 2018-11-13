package com.topjet.manage.service;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.bean.WhiteUserBean;
import com.topjet.manage.domain.model.WhiteUserInfoModel;

import java.util.List;

/**
 * Created by tsj010 on 2018/4/25.
 */
public interface WhiteUserService {


    /**
     * 通过手机号码 获取用户基本信息
     */

    public UserInfoBean  getUserInfo(String  mobile);
   //新增
    public void   insert(WhiteUserInfoModel  whiteUserInfoModel);
   //删除
    public void   update(int   id);
    //列表
    public List<WhiteUserBean> getWhiteUserList();

    //列表
    public int getWhiteUserListCount();


    /**
     * 通过手机号码 获取白名单用户
     */

    public WhiteUserInfoModel  getWhiteUserInfo(WhiteUserInfoModel  whiteUserInfoModel);


}

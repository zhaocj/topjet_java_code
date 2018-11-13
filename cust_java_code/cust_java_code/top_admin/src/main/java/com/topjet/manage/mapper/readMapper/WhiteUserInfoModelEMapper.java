package com.topjet.manage.mapper.readMapper;

import com.topjet.manage.domain.bean.UserInfoBean;
import com.topjet.manage.domain.bean.WhiteUserBean;
import com.topjet.manage.domain.model.WhiteUserInfoModel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by tsj010 on 2018/4/25.
 */
@Mapper
@Repository
public interface WhiteUserInfoModelEMapper {


    public UserInfoBean getUserInfo(String mobile);
    public List<WhiteUserBean> getWhiteUserList();

    public int getWhiteUserListCount();

    public WhiteUserInfoModel getWhiteUserInfo(WhiteUserInfoModel  whiteUserInfoModel);



}

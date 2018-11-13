package com.topjet.cloud.user.serivce;

import com.topjet.cloud.manage.custservice.user.bean.*;

/**
 * Created by hongtaoer-win on 2017/8/4.
 *
 */
public interface UserService {

     //修改用户实名审核状态
     UpdateUserStatusByIdVRU updateUserStatusById(UpdateUserStatusByIdRTS requestDTO);

     //修改用户身份审核状态
     UpdateUserAuthStatusByIdVRU updateUserAuthStatusById(UpdateUserAuthStatusByIdRTS requestDTO) ;

    //修改用户头像审核状态
    UpdateIconStatusByIdVRU updateIconStatusById(UpdateIconStatusByIdRTS requestDTO);

    //修改用户信息
    UpdateUserInformationByIdVRU updateUserInformationById(UpdateUserInformationByIdRTS requestDTO);

     //冻结用户
    FrostUserByIdVRU frostUserById(FrostUserByIdRTS frostUserByIdRTS);

    //取消用户冻结
    AbolishFrostUserByIdVRU abolishFrostUserById(AbolishFrostUserByIdRTS abolishFrostUserByIdRTS);

}

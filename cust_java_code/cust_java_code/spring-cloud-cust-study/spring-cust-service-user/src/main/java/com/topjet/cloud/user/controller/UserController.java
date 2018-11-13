package com.topjet.cloud.user.controller;

import com.topjet.cloud.manage.custservice.user.UserCustService;
import com.topjet.cloud.manage.custservice.user.bean.*;
import com.topjet.cloud.user.serivce.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by zhangn on 2017/8/2.
 */
@RestController
@RequestMapping("/cust-user")
public class UserController  implements UserCustService {

    private static Logger log = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;


    /**
     * 修改用户实名审核状态
     * @param requestDTO
     * @return
     */
    @PostMapping("/updateUserStatusById")
    public UpdateUserStatusByIdVRU updateUserStatusById(@RequestBody UpdateUserStatusByIdRTS requestDTO) {
        return userService.updateUserStatusById(requestDTO);
    }

    /**
     * 修改用户身份审核状态
     * @param requestDTO
     * @return
     */
    @PostMapping("/updateUserAuthStatusById")
    public UpdateUserAuthStatusByIdVRU updateUserAuthStatusById(@RequestBody UpdateUserAuthStatusByIdRTS requestDTO) {
        return userService.updateUserAuthStatusById(requestDTO);
    }

    /**
     * 修改用户头像审核状态
     * @param requestDTO
     * @return
     */
    @PostMapping("/updateIconStatusById")
    public UpdateIconStatusByIdVRU updateIconStatusById(@RequestBody UpdateIconStatusByIdRTS requestDTO) {
       return userService.updateIconStatusById(requestDTO);
    }


    /**
     * 修改用户信息
     * @param requestDTO
     * @return
     */
    @Override
    @PostMapping("/updateUserInformationById")
    public UpdateUserInformationByIdVRU updateUserInformationById(@RequestBody UpdateUserInformationByIdRTS requestDTO) {

        return userService.updateUserInformationById(requestDTO);

    }

    /**
     * 冻结用户
     * @param frostUserByIdRTS
     * @return
     */
    @Override
    @PostMapping("/frostuserbyid")
    public FrostUserByIdVRU frostUserById(@RequestBody FrostUserByIdRTS frostUserByIdRTS) {
        return userService.frostUserById(frostUserByIdRTS);
    }

    /**
     * 取消用户冻结
     * @param abolishFrostUserByIdRTS
     * @return
     */
    @Override
    @PostMapping("/abolishfrostuserbyid")
    public AbolishFrostUserByIdVRU abolishFrostUserById(@RequestBody AbolishFrostUserByIdRTS abolishFrostUserByIdRTS) {
        return userService.abolishFrostUserById(abolishFrostUserByIdRTS);
    }


}

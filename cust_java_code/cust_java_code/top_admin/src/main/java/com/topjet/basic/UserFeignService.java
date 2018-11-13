package com.topjet.basic;

import com.topjet.basic.fallback.UserFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.custservice.user.bean.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by bjb074 on 2017/8/15.
 */
@FeignClient(name = ManageServiceConstant.USER_SERVICE_NAME ,fallback = UserFeignFallBack.class)
public interface UserFeignService {

    //修改用户实名审核状态
    @PostMapping({"/cust-user/updateUserStatusById"})
    UpdateUserStatusByIdVRU updateUserStatusById(@RequestBody UpdateUserStatusByIdRTS var1);

    //修改用户身份审核状态
    @PostMapping({"/cust-user/updateUserAuthStatusById"})
    UpdateUserAuthStatusByIdVRU updateUserAuthStatusById(@RequestBody UpdateUserAuthStatusByIdRTS var1);

    //修改用户头像审核状态
    @PostMapping({"/cust-user/updateIconStatusById"})
    UpdateIconStatusByIdVRU updateIconStatusById(@RequestBody UpdateIconStatusByIdRTS var1);

    //修改用户信息
    @PostMapping({"/cust-user/updateUserInformationById"})
    UpdateUserInformationByIdVRU updateUserInformationById(@RequestBody UpdateUserInformationByIdRTS var1);

    //冻结用户
    @PostMapping({"/cust-user/frostuserbyid"})
    FrostUserByIdVRU frostUserById(@RequestBody FrostUserByIdRTS var1);

    //解冻用户
    @PostMapping({"/cust-user/abolishfrostuserbyid"})
    AbolishFrostUserByIdVRU abolishFrostUserById(@RequestBody AbolishFrostUserByIdRTS var1);

    @PostMapping({"/cust-user/updatefeedbackinfo"})
    UpdateFeedbackInfoVRU updateFeedbackInfo(@RequestBody UpdateFeedbackInfoRTS updateFeedbackInfoRTS);

    /**
     * 评价操作接口
     */
    @PostMapping({"/cust-user/updateusercommentinfo"})
    UpdateUserCommentInfoVRU updateUserCommentInfo(@RequestBody UpdateUserCommentInfoRTS updateUserCommentInfoRTS);

    /**
     * 删除评价接口
     */
    @PostMapping({"/cust-user/deleteusercommentinfo"})
    DeleteUserCommentInfoVRU deleteUserCommentInfo(@RequestBody DeleteUserCommentInfoRTS deleteUserCommentInfoRTS);

    @PostMapping("/cust-user/updatecomplaintinfo")
    UpdateComplaintInfoVRU updateComplaintInfo(@RequestBody UpdateComplaintInfoRTS request);


    //修改企業用户信息
    @PostMapping({"/cust-user/updateCompanyStatusById"})
    UpdateCompanyStatusByIdVRU updateCompanyStatusById(@RequestBody UpdateCompanyStatusByIdRTS var1);




}

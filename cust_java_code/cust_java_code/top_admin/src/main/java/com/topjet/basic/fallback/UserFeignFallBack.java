package com.topjet.basic.fallback;/**
 * Created by bjb074 on 2017/8/15.
 */

import com.topjet.basic.UserFeignService;
import com.topjet.cloud.manage.custservice.user.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author zhaocj
 * @create 2017-08-15 18:39
 **/
@Component
public class UserFeignFallBack implements UserFeignService{

    private static Logger log = LoggerFactory.getLogger(UserFeignFallBack.class);

    @Override
    public UpdateUserStatusByIdVRU updateUserStatusById(UpdateUserStatusByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: updateUserStatusById" + rts );
        return null;
    }

    @Override
    public UpdateUserAuthStatusByIdVRU updateUserAuthStatusById(UpdateUserAuthStatusByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: updateUserAuthStatusById" + rts );
        return null;
    }

    @Override
    public UpdateIconStatusByIdVRU updateIconStatusById(UpdateIconStatusByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: updateIconStatusById" + rts );
        return null;
    }

    @Override
    public UpdateUserInformationByIdVRU updateUserInformationById(UpdateUserInformationByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: updateUserInformationById" + rts );
        return null;
    }

    @Override
    public FrostUserByIdVRU frostUserById( FrostUserByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: frostUserById" + rts );
        return null;
    }

    @Override
    public AbolishFrostUserByIdVRU abolishFrostUserById(AbolishFrostUserByIdRTS rts) {
        log.error("UserFeignFallBack ERROR: abolishFrostUserById" + rts );
        return null;
    }

    @Override
    public UpdateFeedbackInfoVRU updateFeedbackInfo(UpdateFeedbackInfoRTS rts) {
        log.error("UserFeignFallBack ERROR: updateFeedbackInfo" + rts );
        return null;
    }

    @Override
    public UpdateUserCommentInfoVRU updateUserCommentInfo(UpdateUserCommentInfoRTS rts) {
        log.error("UserFeignFallBack ERROR: updateUserCommentInfo" + rts );
        return null;
    }

    @Override
    public DeleteUserCommentInfoVRU deleteUserCommentInfo(DeleteUserCommentInfoRTS rts) {
        log.error("UserFeignFallBack ERROR: deleteUserCommentInfo" + rts );
        return null;
    }

    @Override
    public UpdateComplaintInfoVRU updateComplaintInfo(UpdateComplaintInfoRTS request) {
        log.error("OrderFeignFallBack ERROR: updateComplaintInfo"+ request);
        log.error("请求订单服务发生错误 ERROR: updateComplaintInfo"+ request);
        return null;
    }


    @Override
    public UpdateCompanyStatusByIdVRU updateCompanyStatusById(@RequestBody UpdateCompanyStatusByIdRTS var1) {
        log.error("UserFeignFallBack ERROR: updateCompanyStatusById"+ var1);
        log.error("请求user服务发生错误 ERROR: updateCompanyStatusById"+ var1);
        return null;
    }
}

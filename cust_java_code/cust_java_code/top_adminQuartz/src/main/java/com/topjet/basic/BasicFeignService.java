package com.topjet.basic;

import com.topjet.basic.fallback.BasicFeignFallBack;
import com.topjet.cloud.manage.constant.ManageServiceConstant;
import com.topjet.cloud.manage.service.basic.bean.*;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * Created by zhangn on 2017/8/7.
 */
@FeignClient(name = ManageServiceConstant.BASIC_SERVICE_NAME,fallback = BasicFeignFallBack.class)
public interface BasicFeignService {


    /**
     * 发送短信
     * @param
     * @return
     */
    @PostMapping("/feign-basic/sendSms")
    SendSmsVRU sendSms(@RequestBody SendSmsRTS sendSmsRTS);


    /**
     * 发送验证码
     * @param
     * @return
     */
    @PostMapping("/feign-basic/sendSmsCheckCode")
    SendSmsCheckCodeVRU sendSmsCheckCode(@RequestBody SendSmsCheckCodeRTS sendSmsCheckCodeRTS);


    /**
     * 发送语音验证码
     * @param
     * @return
     */
    @PostMapping("/feign-basic/sendVoiceSmsCheckCode")
    SendVoiceSmsCheckCodeVRU sendVoiceSmsCheckCode(@RequestBody SendVoiceSmsCheckCodeRTS sendVoiceSmsCheckCodeRTS);

    /**
     * 验证验证码
     * @param
     * @return
     */
    @PostMapping("/feign-basic/verifyCheckCode")
    VerifyCheckCodeVRU verifyCheckCode(@RequestBody VerifyCheckCodeRTS verifyCheckCodeRTS);


    /**
     * 上传图片
     * @param
     * @return
     */
    @PostMapping("/feign-basic/upload")
    UploadVRU upload(@RequestBody UploadRTS uploadRTS);


    /**
     * 获取图片URL
     * @param
     * @return
     */
    @PostMapping("/feign-basic/geturl")
    GeturlVRU getUrl(@RequestBody GeturlRTS geturlRTS);

}

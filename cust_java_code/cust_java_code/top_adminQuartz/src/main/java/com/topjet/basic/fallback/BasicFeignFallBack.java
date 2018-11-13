package com.topjet.basic.fallback;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.service.basic.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * basic熔断器
 * Created by zhangn on 2017/8/7.
 */
@Component
public class BasicFeignFallBack implements BasicFeignService {

    private static Logger log = LoggerFactory.getLogger(BasicFeignFallBack.class);


    @Override
    public SendSmsVRU sendSms(@RequestBody SendSmsRTS sendSmsRTS) {
        log.error("BasicFeignFallBack ERROR: sendSms" + sendSmsRTS );
        return null;
    }

    @Override
    public SendSmsCheckCodeVRU sendSmsCheckCode(@RequestBody SendSmsCheckCodeRTS sendSmsCheckCodeRTS) {
        log.error("BasicFeignFallBack ERROR: sendSmsCheckCode" + sendSmsCheckCodeRTS );
        return null;
    }

    @Override
    public SendVoiceSmsCheckCodeVRU sendVoiceSmsCheckCode(@RequestBody SendVoiceSmsCheckCodeRTS sendVoiceSmsCheckCodeRTS) {
        log.error("BasicFeignFallBack ERROR: sendSmsCheckCode" + sendVoiceSmsCheckCodeRTS );
        return null;
    }

    @Override
    public VerifyCheckCodeVRU verifyCheckCode(@RequestBody VerifyCheckCodeRTS verifyCheckCodeRTS) {
        log.error("BasicFeignFallBack ERROR: verifyCheckCode" + verifyCheckCodeRTS );
        return null;
    }

    @Override
    public UploadVRU upload(@RequestBody UploadRTS uploadRTS) {
        log.error("BasicFeignFallBack ERROR: uploadRTS");
        return null;
    }

    @Override
    public GeturlVRU getUrl(@RequestBody GeturlRTS geturlRTS) {
        log.error("BasicFeignFallBack ERROR: getUrl" + geturlRTS );
        return null;
    }
}

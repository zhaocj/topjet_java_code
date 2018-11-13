package com.topjet.common.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AppkeyConstant {
    /**
     * tms AES加密key AES_偏移值
     */
    public static String tmsAesKey;
    public static String tmsAesIvcode;


    public static String getTmsAesKey() {
        return tmsAesKey;
    }

    @Value("${tmsRequest.tmsAesKey}")
    public  void setTmsAesKey(String tmsAesKey) {
        AppkeyConstant.tmsAesKey = tmsAesKey;
    }

    public static String getTmsAesIvcode() {
        return tmsAesIvcode;
    }

    @Value("${tmsRequest.tmsAesIvcode}")
    public  void setTmsAesIvcode(String tmsAesIvcode) {
        AppkeyConstant.tmsAesIvcode = tmsAesIvcode;
    }



}

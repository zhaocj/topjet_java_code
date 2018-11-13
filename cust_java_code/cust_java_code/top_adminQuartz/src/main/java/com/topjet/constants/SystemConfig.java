package com.topjet.constants;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

/**
 * 系统开关配置
 * @author zhaocj
 * @create 2017-08-17 19:41
 **/
@Configuration
public class SystemConfig {

    @Value("${birthday.switch}")
    public String birthdaySwitch;

    @Value("${birthday.message.owner}")
    public String ownerBirthdayMessage;

    @Value("${birthday.message.driver}")
    public String driverBirthdayMessage;

    @Value("${systemConfiguration.recommendBonusSwitch}")
    public String recommendBonusSwitch;


    public String getBirthdaySwitch() {
        return birthdaySwitch;
    }

    public String getOwnerBirthdayMessage() {
        return ownerBirthdayMessage;
    }

    public String getDriverBirthdayMessage() {
        return driverBirthdayMessage;
    }


    public void setBirthdaySwitch(String birthdaySwitch) {
        this.birthdaySwitch = birthdaySwitch;
    }

    public void setOwnerBirthdayMessage(String ownerBirthdayMessage) {
        this.ownerBirthdayMessage = ownerBirthdayMessage;
    }

    public void setDriverBirthdayMessage(String driverBirthdayMessage) {
        this.driverBirthdayMessage = driverBirthdayMessage;
    }

    public String getRecommendBonusSwitch() {
        return recommendBonusSwitch;
    }

    public void setRecommendBonusSwitch(String recommendBonusSwitch) {
        this.recommendBonusSwitch = recommendBonusSwitch;
    }
}

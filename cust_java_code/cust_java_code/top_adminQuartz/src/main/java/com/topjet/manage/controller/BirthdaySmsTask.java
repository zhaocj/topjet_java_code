package com.topjet.manage.controller;

import com.topjet.basic.BasicFeignService;
import com.topjet.cloud.manage.service.basic.bean.SendSmsRTS;
import com.topjet.constants.CommonConstant;
import com.topjet.constants.SystemConfig;
import com.topjet.manage.domain.model.UserInfoModel;
import com.topjet.manage.mapper.readMapper.UserInfoModelEMapper;
import com.topjet.tool.redis.redisuser.domain.RedisBirthdayMessage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import javax.annotation.Resource;
import java.text.MessageFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

/**
 * @Author: zhaocj
 * @Description:用户生日祝福定时任务
 * @Date: 2017-11-17 14:43
 */

@Component
@EnableScheduling
public class BirthdaySmsTask {

    private static final Logger log = LoggerFactory.getLogger(BirthdaySmsTask.class);

    @Autowired
    private SystemConfig systemConfig;

    @Autowired
    private UserInfoModelEMapper userInfoModelEMapper;

    @Autowired
    private BasicFeignService basicFeignService;


    @Resource(name = "birthdayRedisTemplate")
    private RedisTemplate<String,RedisBirthdayMessage> birthdayRedisTemplate;


    @Scheduled(cron = "0 0 3 * * ?")
    public void getBirthdayUser(){

        if(!"1".equals(systemConfig.getBirthdaySwitch())){
            log.info("生日祝福短信定时功能已关闭");
            return;
        }

        log.info("获取当天生日的用户");
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("MMdd");
        String birthday = formatter.format(calendar.getTime());

        List<UserInfoModel> userInfoModels = userInfoModelEMapper.selectBirthdayUser(birthday, 0);
        int n = 0;
        while (userInfoModels != null && userInfoModels.size()>0){
            log.info("生日短信条数。。。。"+userInfoModels.size());
            for (UserInfoModel userInfoModel : userInfoModels) {
                n++;
                String message = userInfoModel.getUserType()==1?systemConfig.getDriverBirthdayMessage():systemConfig.getOwnerBirthdayMessage();
                    String sex = userInfoModel.getSex()==1?"女士":"先生";
                String username = userInfoModel.getUsername();
                message = MessageFormat.format(message,username,sex);
                RedisBirthdayMessage redisBirthdayMessage = new RedisBirthdayMessage();
                redisBirthdayMessage.setUserId(userInfoModel.getId()+"");
                redisBirthdayMessage.setMobile(userInfoModel.getMobile());
                redisBirthdayMessage.setContent(message);
                ValueOperations<String, RedisBirthdayMessage> opsForValue = birthdayRedisTemplate.opsForValue();
                try{
                    opsForValue.set(CommonConstant.BIRTHDAY_USER+n,redisBirthdayMessage);
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            userInfoModels = userInfoModelEMapper.selectBirthdayUser(birthday,userInfoModels.get(userInfoModels.size()-1).getId());
        }
        log.info("获取生日祝福短信结束。。。");

    }

    @Scheduled(cron = "0 0 8 * * ? ")
    public void sendBirthdayMessage(){

        log.info("开始发送生日祝福短信。。。");
        int n = 1;
        ValueOperations<String, RedisBirthdayMessage> opsForValue = birthdayRedisTemplate.opsForValue();
        RedisBirthdayMessage redisBirthdayMessage = opsForValue.get(CommonConstant.BIRTHDAY_USER + n);
        SendSmsRTS rts = new SendSmsRTS();
        while (redisBirthdayMessage!=null){
            rts.setMobileNum(redisBirthdayMessage.getMobile());
            rts.setContent(redisBirthdayMessage.getContent());
            try{
                basicFeignService.sendSms(rts);
            }catch (Exception e){
                log.error("发送生日祝福短信失败，手机号："+redisBirthdayMessage.getMobile());
            }
            //从redis中删除已经发送过祝福短信的用户信息
            birthdayRedisTemplate.delete(CommonConstant.BIRTHDAY_USER + n);
            n++;
            redisBirthdayMessage = opsForValue.get(CommonConstant.BIRTHDAY_USER+n);
        }
        log.info("结束发送生日祝福短信。。。");
    }


}

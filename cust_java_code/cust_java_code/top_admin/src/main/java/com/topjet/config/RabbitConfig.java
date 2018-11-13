package com.topjet.config;

import com.topjet.manage.constants.CommonConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by liyj on 2017/10/31.
 */
@Configuration
public class RabbitConfig {

    /** 积分发放消息 */
    @Bean
    public Queue scoreQueue(){
        Queue queue = new Queue(CommonConstant.SCORE_MQ_ARTIFICAL,false,false,false,null);
        return queue;
    }

}

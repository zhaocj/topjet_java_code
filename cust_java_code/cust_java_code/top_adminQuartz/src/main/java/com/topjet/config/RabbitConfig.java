package com.topjet.config;

import com.topjet.constants.CommonConstant;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue scoreQueue(){
        Queue queue = new Queue(CommonConstant.BONUS_MQ_ARTIFICAL,false,false,false,null);
        return queue;
    }

}

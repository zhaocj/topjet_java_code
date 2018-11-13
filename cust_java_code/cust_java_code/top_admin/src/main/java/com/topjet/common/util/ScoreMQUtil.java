package com.topjet.common.util;

import com.topjet.cloud.manage.mq.score.ScoreGainMQBean;
import com.topjet.manage.constants.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * Created by liyj on 2017/11/3.
 */
@Component
public class ScoreMQUtil {

    private static final Logger LOG = LoggerFactory.getLogger(ScoreMQUtil.class);

    @Autowired
    private  AmqpTemplate rabbitTemplate;
    private static ScoreMQUtil scoreMQUtil;

    @PostConstruct
    public void init() {
        scoreMQUtil = this;
        scoreMQUtil.rabbitTemplate = this.rabbitTemplate;
    }

    public static ScoreGainMQBean scoreGainMQBean(ScoreGainMQBean scoreGainMQBean){
        LOG.debug(" 发送MQ人工发放积分请求信息: topjet.score.gain " + scoreGainMQBean );
        scoreMQUtil.rabbitTemplate.convertAndSend(CommonConstant.SCORE_MQ_ARTIFICAL,scoreGainMQBean);
        return scoreGainMQBean;
    }

}

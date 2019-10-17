package com.lvmoney.rabbitmq.ack.customer;/**
 * 描述:
 * 包名:com.lvmoney.jwt.annotation
 * 版本信息: 版本1.0
 * 日期:2019/1/21
 * Copyright xxxx科技有限公司
 */

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.lvmoney.common.exception.BusinessException;
import com.lvmoney.common.exception.CommonException;
import com.lvmoney.rabbitmq.ack.constant.RabbitmqConstant;
import com.lvmoney.rabbitmq.ack.factory.HandMqServiceContext;
import com.lvmoney.rabbitmq.ack.util.ContextBeanUtil;
import com.lvmoney.rabbitmq.ack.vo.MessageVo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;

/**
 * @describe：
 * @author: lvmoney /xxxx科技有限公司
 * @version:v1.0 2018年10月30日 下午3:29:38
 */


@Component
@RabbitListener(queues = RabbitmqConstant.MESSAGE_TOPIC)
public class TopicReceiver {
    private static final Logger LOGGER = LoggerFactory.getLogger(TopicReceiver.class);
    @Autowired
    HandMqServiceContext handMqServiceContext;

    @RabbitHandler
    public void process(String msg, Channel channel, Message message) {
        MessageVo mqDataVo = JSONObject.parseObject(msg, MessageVo.class);
        String mqType = mqDataVo.getMsgType();
        String beanName = ContextBeanUtil.getBeanName(handMqServiceContext, mqType);
        if (StringUtils.isBlank(beanName)) {
            throw new BusinessException(CommonException.Proxy.RABBITMQ_DYNAMIC_NOT_EXSIT);
        }
        handMqServiceContext.getData(beanName, mqDataVo);
        try {
            //告诉服务器收到这条消息 已经被我消费了 可以在队列删掉 这样以后就不会再发了 否则消息服务器以为这条消息没处理掉 后续还会在发
            channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
        } catch (IOException e) {
            LOGGER.error("消费消息失败:{}", e.getMessage());
            throw new BusinessException(CommonException.Proxy.RABBIT_MESSAGE_RECEIVER_TOPIC_ERROR);
            //丢弃这条消息
            //channel.basicNack(message.getMessageProperties().getDeliveryTag(), false,false);
        }
    }

}

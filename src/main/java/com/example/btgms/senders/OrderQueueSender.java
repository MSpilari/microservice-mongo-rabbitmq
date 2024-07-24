package com.example.btgms.senders;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.btgms.configs.RabbitMQConfig;
import com.example.btgms.controllers.dto.OrderBodyDTO;

@Component
public class OrderQueueSender {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void sendMessage(OrderBodyDTO message) {
        rabbitTemplate.convertAndSend(RabbitMQConfig.ORDER_QUEUE, message);
    }
}

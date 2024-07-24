package com.example.btgms.listeners;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import com.example.btgms.configs.RabbitMQConfig;
import com.example.btgms.controllers.dto.OrderBodyDTO;
import com.example.btgms.services.OrderQueueService;

@Component
public class OrderQueueListener {

    @Autowired
    private OrderQueueService orderQueueService;

    @RabbitListener(queues = RabbitMQConfig.ORDER_QUEUE)
    public void listen(Message<OrderBodyDTO> message) {
        orderQueueService.save(message.getPayload());
    }
}

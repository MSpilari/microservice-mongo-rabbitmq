package com.example.btgms.controllers;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.btgms.controllers.dto.OrderBodyDTO;
import com.example.btgms.entities.OrderEntity;
import com.example.btgms.senders.OrderQueueSender;
import com.example.btgms.services.OrderQueueService;

@RestController
public class OrderController {

    @Autowired
    private OrderQueueService orderQueueService;

    @Autowired
    private OrderQueueSender orderQueueSender;

    @PostMapping("/order")
    public void sendMessageToQueue(@RequestBody OrderBodyDTO message) {
        orderQueueSender.sendMessage(message);
    }

    @GetMapping("/orders")
    public ResponseEntity<List<OrderEntity>> allOrders() {
        return ResponseEntity.ok(orderQueueService.allOrders());
    }

    @GetMapping("/{customerId}/orders")
    public ResponseEntity<Map<String, Object>> listOrders(@PathVariable("customerId") Long customerId,
            @RequestParam(name = "page", defaultValue = "0") Integer page,
            @RequestParam(name = "pageSize", defaultValue = "10") Integer pageSize) {

        var pageResponse = orderQueueService.findByCustomerId(customerId, PageRequest.of(page, pageSize));
        return ResponseEntity.ok(pageResponse);
    }
}

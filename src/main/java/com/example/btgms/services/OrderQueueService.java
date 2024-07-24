package com.example.btgms.services;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.example.btgms.controllers.dto.OrderBodyDTO;
import com.example.btgms.controllers.dto.OrderBodyItemDTO;
import com.example.btgms.entities.OrderEntity;
import com.example.btgms.entities.OrderItemEntity;
import com.example.btgms.repositories.OrderRepository;

@Service
public class OrderQueueService {

    @Autowired
    private OrderRepository orderRepository;

    public void save(OrderBodyDTO order) {
        var newOrder = new OrderEntity();

        List<OrderItemEntity> entityList = OrderBodyItemDTO.convert2OrderItemEntity(order.items());

        newOrder.setOrderId(order.orderId());
        newOrder.setCustomerId(order.customerId());
        newOrder.setItems(entityList);

        orderRepository.save(newOrder);
    }

    public List<OrderEntity> allOrders() {
        return orderRepository.findAll();
    }

    public Map<String, Object> findByCustomerId(Long customerId, PageRequest pageRequest) {
        var orderById = orderRepository.findByCustomerId(customerId, pageRequest).getContent();

        var total = orderById.stream()
                .map(order -> order.getItems()
                        .stream()
                        .map(item -> item.getPrice().multiply(BigDecimal.valueOf(item.getQuantity())))
                        .reduce(BigDecimal.ZERO, BigDecimal::add))
                // Reference Method - Big Decimal :: add === Lambda Function - (a,b) -> a.add(b)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        var totalOfOrders = orderById.size();

        return Map.of("orders", orderById, "total", total, "totalOfOrders", totalOfOrders);
    }
}

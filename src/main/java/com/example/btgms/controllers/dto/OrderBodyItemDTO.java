package com.example.btgms.controllers.dto;

import java.math.BigDecimal;
import java.util.List;

import com.example.btgms.entities.OrderItemEntity;

public record OrderBodyItemDTO(String product, Integer quantity, BigDecimal price) {
    public static List<OrderItemEntity> convert2OrderItemEntity(List<OrderBodyItemDTO> order) {
        return order.stream()
                .map(item -> new OrderItemEntity(item.product(), item.quantity(), item.price()))
                .toList();
    }
}

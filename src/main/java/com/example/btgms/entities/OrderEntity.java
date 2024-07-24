package com.example.btgms.entities;

import java.util.List;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Document(collection = "tb_orders")
@Getter
@Setter
@NoArgsConstructor
public class OrderEntity {

    @Id
    private String id;

    @Field("order_id")
    private Long orderId;

    @Field("customer_id")
    private Long customerId;

    private List<OrderItemEntity> items;

}
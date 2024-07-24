package com.example.btgms.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.example.btgms.entities.OrderEntity;

public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    public Page<OrderEntity> findByCustomerId(Long customerId, PageRequest pageRequest);
}

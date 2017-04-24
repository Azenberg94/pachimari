package com.pachimari.order.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface OrderRepository extends OrderOperations, MongoRepository<OrderEntity, String> {
    OrderEntity findById(long id);
}
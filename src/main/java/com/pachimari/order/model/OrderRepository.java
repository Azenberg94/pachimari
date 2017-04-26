package com.pachimari.order.model;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface OrderRepository extends MongoRepository<OrderEntity, String> {
    List<OrderEntity> findAll();
    OrderEntity findById(Integer id);

    @Query(value="{'user.$id' : ?0 }")
    List<OrderEntity> findByUser(String id);
}
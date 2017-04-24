package com.pachimari.order.model;

import com.pachimari.user.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

public class OrderRepositoryImpl implements OrderOperations{
    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<OrderEntity> findByUser(User user) {
        String userId = user.getId();
        List<OrderEntity> orderEntityList = mongoTemplate.find(new Query(Criteria.where("order.user.id").is(userId)), OrderEntity.class);

        return orderEntityList;
    }
}

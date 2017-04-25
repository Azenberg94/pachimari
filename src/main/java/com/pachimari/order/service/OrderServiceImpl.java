package com.pachimari.order.service;

import com.pachimari.item.model.ItemEntity;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.model.OrderRepository;
import com.pachimari.user.UserAdapter;
import com.pachimari.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final MongoTemplate mongoTemplate;
    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, MongoTemplate mongoTemplate){
        this.orderRepository = orderRepository;
        this.mongoTemplate = mongoTemplate;
    }

    @Override
    public OrderEntity addOrder(OrderEntity order) {
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<OrderEntity> getOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<OrderEntity> getUserOrders(UserDTO userDTO) {
        return orderRepository.findByUser(UserAdapter.toUserEntity(userDTO).getId());
    }

    @Override
    public OrderEntity getIdOrder(Integer id) {
        return orderRepository.findById(id);
    }

    @Override
    public void calculateAmountOrder(OrderEntity orderEntity) {
        float amount = 0;

        for(ItemEntity item : orderEntity.getItems()){
            amount += item.getItemPrice();
        }

        orderEntity.setAmount(amount);
        mongoTemplate.save(orderEntity);
    }
}
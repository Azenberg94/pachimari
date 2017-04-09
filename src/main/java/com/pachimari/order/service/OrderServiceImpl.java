package com.pachimari.order.service;

import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public OrderEntity addOrder(OrderEntity order) {
        orderRepository.save(order);

        return order;
    }

    @Override
    public List<OrderEntity> getOrders() {
        return orderRepository.findAll();
    }

}
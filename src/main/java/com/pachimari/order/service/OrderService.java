package com.pachimari.order.service;

import com.pachimari.order.model.OrderEntity;

import java.util.List;

public interface OrderService {
    OrderEntity addOrder(OrderEntity order);
    List<OrderEntity> getOrders();
}

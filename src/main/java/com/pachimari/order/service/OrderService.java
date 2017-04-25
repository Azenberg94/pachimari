package com.pachimari.order.service;

import com.pachimari.order.model.OrderEntity;
import com.pachimari.user.UserDTO;

import java.util.List;

public interface OrderService {
    OrderEntity addOrder(OrderEntity order);
    List<OrderEntity> getOrders();
    List<OrderEntity> getUserOrders(UserDTO userDTO);
    OrderEntity getIdOrder(Integer id);
    void calculateAmountOrder(OrderEntity orderEntity);
}
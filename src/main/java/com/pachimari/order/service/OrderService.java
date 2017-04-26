package com.pachimari.order.service;

import com.pachimari.order.model.OrderDTO;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.user.UserDTO;

import java.util.List;

public interface OrderService {
    OrderDTO addOrder(OrderDTO order);
    List<OrderDTO> getOrders();
    List<OrderDTO> getUserOrders(UserDTO userDTO);
    OrderDTO getIdOrder(Integer id);
    void calculateAmountOrder(OrderDTO orderDTO);
}
package com.pachimari.order.model;

import com.pachimari.user.User;

import java.util.List;

public interface OrderOperations {
    List<OrderEntity> findByUser(User user);
}

package com.pachimari.order.service;

import com.pachimari.item.model.ItemEntity;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.model.OrderRepository;
import com.pachimari.order.model.OrderRepositoryImpl;
import com.pachimari.user.User;
import com.pachimari.user.UserAdapter;
import com.pachimari.user.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final OrderRepositoryImpl orderRepositoryImpl;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, OrderRepositoryImpl orderRepositoryImpl){
        this.orderRepository = orderRepository;
        this.orderRepositoryImpl = orderRepositoryImpl;
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
        return orderRepositoryImpl.findByUser(UserAdapter.toUserEntity(userDTO));
    }

    @Override
    public OrderEntity getIdOrder(long id) {
        return orderRepository.findById(id);
    }

    @Override
    public void calculateAmountOrder(OrderEntity orderEntity) {
        float amount = 0;

        for(ItemEntity item : orderEntity.getItems()){
            amount += item.getItemPrice();
        }

        orderEntity.setAmount(amount);
    }
}
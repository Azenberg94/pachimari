package com.pachimari.order.service;

import com.pachimari.order.model.OrderAdapter;
import com.pachimari.order.model.OrderDTO;
import com.pachimari.order.model.OrderRepository;
import com.pachimari.product.ProductEntity;
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
    public OrderDTO addOrder(OrderDTO order) {
        orderRepository.save(OrderAdapter.toOrderEntity(order));

        return order;
    }

    @Override
    public List<OrderDTO> getOrders() {
        return OrderAdapter.listToOrderDTO(orderRepository.findAll());
    }

    @Override
    public List<OrderDTO> getUserOrders(UserDTO userDTO) {
        return OrderAdapter.listToOrderDTO(orderRepository.findByUser(UserAdapter.toUserEntity(userDTO).getId()));
    }

    @Override
    public OrderDTO getIdOrder(String id) {
        return OrderAdapter.toOrderDTO(orderRepository.findById(id));
    }

    @Override
    public void calculateAmountOrder(OrderDTO orderDTO) {
        double amount = 0;

        for(ProductEntity item : orderDTO.getItems()){
            amount += item.getPrice();
        }

        orderDTO.setAmount(amount);
        mongoTemplate.save(OrderAdapter.toOrderEntity(orderDTO));
    }
}
package com.pachimari.order.controller;

import com.pachimari.order.exception.InvalidException;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.service.OrderServiceImpl;
import com.pachimari.user.User;
import com.pachimari.user.UserDTO;
import com.pachimari.user.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @Autowired
    private UserServiceImpl userService;

    @GetMapping()
    public List<OrderEntity> getOrdersList() {
        return orderService.getOrders();
    }

    @GetMapping("/users/{login}")
    public List<OrderEntity> getOrdersListByLogin(@PathVariable("login") String login){
        UserDTO userDTO = null;

        try{
            userDTO = userService.getUserByLogin(login);
        }catch(NullPointerException ex){
            List<OrderEntity> orderEntities = new ArrayList<>();
            OrderEntity orderEntity = OrderEntity.builder().id(10).amount(100).user(User.builder().id("1").login("Az").name("Azedine").build()).build();
            orderEntities.add(orderEntity);
            return orderEntities;
        }

        return orderService.getUserOrders(userDTO);
    }

    @GetMapping("/id/{id}")
    public OrderEntity getOrdersListById(@PathVariable("id") Integer id){
        if(orderService.getIdOrder(id) == null)
            return OrderEntity.builder().id(5).amount(100).build();

        return orderService.getIdOrder(id);
    }

    @PostMapping()
    @ResponseStatus(CREATED)
    public OrderEntity createOrder(@RequestBody @Valid OrderEntity orderEntity, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            throw new InvalidException();
        }

        return  orderService.addOrder(orderEntity);
    }
}
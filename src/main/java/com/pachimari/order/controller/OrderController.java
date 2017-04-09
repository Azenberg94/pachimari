package com.pachimari.order.controller;

import com.pachimari.order.exception.InvalidException;
import com.pachimari.order.model.OrderEntity;
import com.pachimari.order.service.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/orders")
public class OrderController {
    @Autowired
    private OrderServiceImpl orderService;

    @GetMapping()
    public List<OrderEntity> getAccountList()
    {
        return orderService.getOrders();
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

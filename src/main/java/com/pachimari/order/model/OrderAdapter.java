package com.pachimari.order.model;

public class OrderAdapter {
    public static OrderEntity toOrderEntity(OrderDTO orderDTO){
        return OrderEntity.builder()
                .orderId(orderDTO.getOrderId())
                .user(orderDTO.getUser())
                .items(orderDTO.getItems())
                .amount(orderDTO.getAmount())
                .build();
    }

    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        return OrderDTO.builder()
                .orderId(orderEntity.getOrderId())
                .user(orderEntity.getUser())
                .items(orderEntity.getItems())
                .amount(orderEntity.getAmount())
                .build();
    }
}

package com.pachimari.order.model;

import java.util.ArrayList;
import java.util.List;

public class OrderAdapter {
    public static OrderEntity toOrderEntity(OrderDTO orderDTO){
        return OrderEntity.builder()
                .id(orderDTO.getId())
                .user(orderDTO.getUser())
                .items(orderDTO.getItems())
                .amount(orderDTO.getAmount())
                .build();
    }

    public static OrderDTO toOrderDTO(OrderEntity orderEntity){
        return OrderDTO.builder()
                .id(orderEntity.getId())
                .user(orderEntity.getUser())
                .items(orderEntity.getItems())
                .amount(orderEntity.getAmount())
                .build();
    }

    public static List<OrderDTO> listToOrderDTO(List<OrderEntity> list){
        List<OrderDTO> orderDTOList = new ArrayList<>();

        for(OrderEntity orderEntity : list){
            orderDTOList.add(toOrderDTO(orderEntity));
        }

        return  orderDTOList;
    }
}

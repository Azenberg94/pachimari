package com.pachimari.item.model;


import com.pachimari.order.model.OrderDTO;
import com.pachimari.order.model.OrderEntity;

public class ItemAdapter {
    public static ItemEntity toItemEntity(ItemDTO itemDTO){
        return ItemEntity.builder()
                .itemId(itemDTO.getItemId())
                .itemName(itemDTO.getItemName())
                .itemPrice(itemDTO.getItemPrice())
                .orders(itemDTO.getOrders())
                .build();
    }

    public static ItemDTO toItemDTO(ItemEntity itemEntity){
        return ItemDTO.builder()
                .itemId(itemEntity.getItemId())
                .itemName(itemEntity.getItemName())
                .itemPrice(itemEntity.getItemPrice())
                .orders(itemEntity.getOrders())
                .build();
    }
}

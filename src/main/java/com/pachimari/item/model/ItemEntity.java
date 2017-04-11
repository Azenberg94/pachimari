package com.pachimari.item.model;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection="item")
public class ItemEntity {
    @org.springframework.data.annotation.Id
    @GeneratedValue
    private int itemId;

    private String itemName;

    private Float itemPrice;

    @DBRef
    private Set<OrderEntity> orders = new HashSet<>();
}

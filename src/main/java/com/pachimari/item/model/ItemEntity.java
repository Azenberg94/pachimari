package com.pachimari.item.model;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orders"})
@ToString(exclude = "orders")
@Document(collection="item")
public class ItemEntity {
    @Id
    @GeneratedValue
    private int itemId;

    private String itemName;

    private Float itemPrice;

    @DBRef
    private List<OrderEntity> orders = new ArrayList<>();
}

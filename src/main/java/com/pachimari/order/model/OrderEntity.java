package com.pachimari.order.model;

import com.pachimari.item.model.ItemEntity;
import com.pachimari.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
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
@Document(collection = "order")
public class OrderEntity {
    @Id
    @GeneratedValue
    private long orderId;

    @DBRef
    private User user;

    @DBRef
    private Set<ItemEntity> items = new HashSet<>(0);
}

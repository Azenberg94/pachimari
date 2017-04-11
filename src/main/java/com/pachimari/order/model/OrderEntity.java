package com.pachimari.order.model;

import com.pachimari.item.model.ItemEntity;
import com.pachimari.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "items"})
@ToString(exclude = "user")
@Document(collection = "order")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private long orderId;

    @DBRef
    @Indexed
    private User user;

    @DBRef
    private Set<ItemEntity> items = new HashSet<>(0);

    private Float amount;
}

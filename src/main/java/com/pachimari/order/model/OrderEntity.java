package com.pachimari.order.model;


import com.pachimari.product.ProductEntity;
import com.pachimari.user.User;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private String id;

    @DBRef
    @Indexed
    private User user;

    @DBRef
    private List<ProductEntity> items = new ArrayList<>(0);

    private double amount;
}

package com.pachimari.item.model;

import com.pachimari.order.model.OrderEntity;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

@Builder
@Entity
@Table(name = "items")
@Getter
@Setter
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private int itemId;

    @NotNull
    @Column(name = "item_name", nullable = false, length = 255)
    private String itemName;

    @NotNull
    @Column(name = "item_price", nullable = false)
    private Float itemPrice;

    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "items")
    private Set<OrderEntity> orders = new HashSet<>();
}

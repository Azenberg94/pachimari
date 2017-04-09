package com.pachimari.order.model;

import com.pachimari.item.model.Item;
import com.pachimari.user.User;
import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
@ToString(exclude = "user")
@EqualsAndHashCode(exclude = {"user", "items"})
@Table(name = "orders")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private long orderId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id", nullable = false)
    private User user;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "orders_items",
            joinColumns = { @JoinColumn(name = "order_id", nullable = false, updatable = false)},
            inverseJoinColumns = { @JoinColumn(name = "item_id", nullable = false, updatable = false)}
    )
    private Set<Item> items = new HashSet<>(0);
}

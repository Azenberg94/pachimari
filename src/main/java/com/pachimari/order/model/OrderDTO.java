package com.pachimari.order.model;

import com.pachimari.item.model.ItemEntity;
import com.pachimari.user.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "items"})
@ToString(exclude = "user")
@Getter
@Setter
public class OrderDTO {
    @NotNull
    @Min(0)
    @GeneratedValue(strategy = IDENTITY)
    private long orderId;

    @NotNull
    private User user;

    @NotNull
    private Set<ItemEntity> items = new HashSet<>(0);

    @NotNull
    @Min(0)
    private Float amount;
}

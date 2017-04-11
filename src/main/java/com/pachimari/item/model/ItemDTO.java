package com.pachimari.item.model;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orders"})
@ToString(exclude = "orders")
@Getter
@Setter
public class ItemDTO {
    @GeneratedValue(strategy = IDENTITY)
    @NotNull
    @Min(0)
    private int itemId;

    @NotNull
    @NotBlank
    private String itemName;

    @NotNull
    @Min(0)
    private Float itemPrice;

    @NotNull
    private Set<OrderEntity> orders = new HashSet<>();
}

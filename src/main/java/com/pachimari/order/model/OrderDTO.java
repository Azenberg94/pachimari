package com.pachimari.order.model;

import com.pachimari.product.ProductEntity;
import com.pachimari.user.User;
import lombok.*;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"user", "items"})
@ToString(exclude = "user")
@Getter
@Setter
public class OrderDTO {
    private String id;

    @NotNull
    private User user;

    @NotNull
    private List<ProductEntity> items = new ArrayList<>(0);

    private double amount;
}

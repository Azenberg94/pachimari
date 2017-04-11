package com.pachimari.user;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.hibernate.validator.constraints.NotBlank;

import javax.persistence.GeneratedValue;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by Pierre on 28/02/2017.
 */
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = {"orders"})
@ToString(exclude = "orders")
@Getter
@Setter
public class UserDTO {

    @NotNull
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    @NotBlank
    @NotNull
    @Size(min = 3, max = 40)
    private String name;

    @NotNull
    @NotBlank
    private String login;

    @NotNull
    @NotBlank
    private String email;

    @NotNull
    @NotBlank
    private String type;

    @NotNull
    private Set<OrderEntity> orders = new HashSet<>(0);
}

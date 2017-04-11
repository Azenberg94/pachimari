package com.pachimari.user;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


/**
 * Created by Pierre on 11/03/2017.
 */

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "user")
public class User {

    @Id
    @GeneratedValue
    private String id;

    private String name;

    private String email;

    private String login;

    private String type;

    @DBRef
    private Set<OrderEntity> orders = new HashSet<>(0);
}


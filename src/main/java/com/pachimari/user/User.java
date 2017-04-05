package com.pachimari.user;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

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
@Entity
@Table(name = "users")
@ToString(exclude = "orders")
@EqualsAndHashCode(exclude = "orders")
/*@Document(collection="user")*/
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String name;

    private String email;

    private String login;

    private String type;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private Set<OrderEntity> orders = new HashSet<>(0);

}


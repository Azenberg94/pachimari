package com.pachimari.product;

import com.pachimari.order.model.OrderEntity;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created by andrem on 23/03/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode
@ToString
@Document(collection = "product")
public class ProductEntity {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private String id;

    private String name;

    private String brand;

    private String typeId;

    private double price;

    private String imageURL;

    /*@Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', brand='%s', typeId='%s', price='%f%n', imageURL='%s']",
                id, name, brand, typeId, price, imageURL);
    }*/
}

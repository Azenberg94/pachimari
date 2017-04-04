package com.pachimari.product;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * Created by andrem on 23/03/2017.
 */
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductEntity {

    @Id
    private String id;

    private String name;

    private String brand;

    private Integer typeId;

    private double price;

    private String imageURL;

    @Override
    public String toString() {
        return String.format(
                "Product[id=%s, name='%s', brand='%s', typeId='%d', price='%f%n', imageURL='%s']",
                id, name, brand, typeId, price, imageURL);
    }

}

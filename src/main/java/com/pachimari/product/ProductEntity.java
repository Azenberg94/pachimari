package com.pachimari.product;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.GeneratedValue;

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
}
